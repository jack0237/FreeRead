package com.sklfgroup.auxillium.rest.exceptionhandler;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.sklfgroup.auxillium.exceptions.*;
import com.sklfgroup.auxillium.rest.dto.responses.error.ErrorResponse;
import com.sklfgroup.auxillium.rest.dto.responses.error.ErrorsResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.actuate.endpoint.invoke.MissingParametersException;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@ControllerAdvice
public class AccountExceptionHandler extends AbstractExceptionHandler {

  public static final String BAD_REQUEST = "BAD_REQUEST";
  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @ExceptionHandler(
      value = {
          MissingParametersException.class,
          AlreadyExistsException.class,
          NotActivatedException.class,
          BadRequestException.class,
          ConstraintViolationException.class,
          IllegalArgumentException.class
      })
  protected ResponseEntity<Object> handleBadRequestException(
      final Exception exception, final WebRequest request) {
    return handleException(
        exception,
        new HttpHeaders(),
        HttpStatus.BAD_REQUEST,
        ExceptionCode.getCodeFromException(exception).name(),
        request);
  }

  @ExceptionHandler(
      value = {
          MissingRequestHeaderException.class,
          ConversionFailedException.class,
          UnauthorizedException.class
      })
  protected ResponseEntity<Object> handleUnauthorizedException(
      final Exception exception, final WebRequest request) {
    return handleException(
        exception,
        new HttpHeaders(),
        HttpStatus.UNAUTHORIZED,
        HttpStatus.UNAUTHORIZED.name(),
        request);
  }

  @ExceptionHandler(
      value = {
          MissingRequiredParameterException.class,
          MissingHeaderParameterException.class,
          DateFormatException.class
      })
  protected ResponseEntity<Object> handleBadRequestDomainException(
          final DomainException exception, final WebRequest request) {
    return handleDomainException(
        exception,
        new HttpHeaders(),
        HttpStatus.BAD_REQUEST,
        exception.getCode(),
        request,
        exception.getErrorsResponse());
  }

  @ExceptionHandler(
      value = {
          MissingHeaderAuthorizationParameterException.class,
          InvalidHeaderParameterException.class,
          TokenExpirationException.class
      })
  protected ResponseEntity<Object> handleUnauthorizedDomainException(
      final DomainException exception, final WebRequest request) {
    return handleDomainException(
        exception,
        new HttpHeaders(),
        HttpStatus.UNAUTHORIZED,
        HttpStatus.UNAUTHORIZED.name(),
        request,
        exception.getErrorsResponse());
  }

  @ExceptionHandler(
      value = {
          ForbiddenException.class,
          WrongCredentialsException.class,
          MethodArgumentTypeMismatchException.class,
          InvalidTokenException.class
      })
  protected ResponseEntity<Object> handleForbiddenException(
      final Exception exception, final WebRequest request) {
    return handleException(
        exception, new HttpHeaders(), HttpStatus.FORBIDDEN, HttpStatus.FORBIDDEN.name(), request);
  }

  @ExceptionHandler(
      value = {
          NotFoundException.class
      })
  protected ResponseEntity<Object> handleNotFoundException(
      final Exception exception, final WebRequest request) {
    return handleException(
        exception,
        new HttpHeaders(),
        HttpStatus.NOT_FOUND,
        ExceptionCode.NOT_FOUND.name(),
        request);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      final MethodArgumentNotValidException exception,
      final HttpHeaders headers,
      final HttpStatus status,
      final WebRequest request) {
    final List<ErrorResponse> errors =
        exception.getBindingResult().getAllErrors().stream()
            .map(
                error -> {
                  var fieldName = ((FieldError) error).getField();
                  var errorMessage = error.getDefaultMessage();
                  return new ErrorResponse(
                      getCorrelationId(request),
                      String.valueOf(HttpStatus.BAD_REQUEST.value()),
                      "Bad request",
                      BAD_REQUEST,
                      fieldName + " " + errorMessage);
                })
            .toList();
    return super.handleExceptionInternal(
        exception, new ErrorsResponse(errors), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
  }

  private static final Pattern ENUM_MSG =
      Pattern.compile("values accepted for Enum class: \\[([^\\]]\\D+)\\]");

  @Override
  protected ResponseEntity<Object> handleHttpMessageNotReadable(
      final HttpMessageNotReadableException exception,
      final HttpHeaders headers,
      final HttpStatus status,
      final WebRequest request) {
    final List<ErrorResponse> errors = new ArrayList<>();

    if (exception.getCause() instanceof InvalidFormatException) {
      final Matcher match = ENUM_MSG.matcher(exception.getCause().getMessage());
      if (match.find()) {
        errors.add(
            new ErrorResponse(
                getCorrelationId(request),
                String.valueOf(status.value()),
                status.getReasonPhrase(),
                BAD_REQUEST,
                "Value should be: " + match.group(1)));
      } else {
        errors.add(
            new ErrorResponse(
                getCorrelationId(request),
                String.valueOf(status.value()),
                status.getReasonPhrase(),
                BAD_REQUEST,
                "Invalid Enum type"));
      }
    } else {
      errors.add(
          new ErrorResponse(
              getCorrelationId(request),
              String.valueOf(status.value()),
              status.getReasonPhrase(),
              BAD_REQUEST,
              "Required request body is missing"));
    }
    return super.handleExceptionInternal(
        exception, new ErrorsResponse(errors), headers, status, request);
  }

  @ExceptionHandler(value = {Exception.class})
  protected ResponseEntity<Object> handleGeneralException(
      final Exception exception, final WebRequest request) {
    return handleException(
        exception,
        new HttpHeaders(),
        HttpStatus.INTERNAL_SERVER_ERROR,
        ExceptionCode.UNKNOWN_ERROR.name(),
        request);
  }

  @Override
  protected ResponseEntity<Object> handleExceptionInternal(
      final Exception exception,
      final Object body,
      final HttpHeaders headers,
      final HttpStatus status,
      final WebRequest request) {
    return handleException(exception, headers, status, ExceptionCode.UNKNOWN_ERROR.name(), request);
  }

  protected ResponseEntity<Object> handleException(
      final Exception exception,
      final HttpHeaders headers,
      final HttpStatus status,
      final String code,
      final WebRequest request) {
    final List<ErrorResponse> errors = findErrorResponses(exception, status, code, request);
    if (status.equals(HttpStatus.INTERNAL_SERVER_ERROR)) {
      logger.error(exception.getMessage(), exception);
    } else {
      logger.info(exception.getMessage(), exception);
    }
    return super.handleExceptionInternal(
        exception, new ErrorsResponse(errors), headers, status, request);
  }

  protected ResponseEntity<Object> handleDomainException(
      final DomainException exception,
      final HttpHeaders headers,
      final HttpStatus status,
      final String code,
      final WebRequest request,
      final ErrorsResponse errorsResponse) {
    if (status.equals(HttpStatus.INTERNAL_SERVER_ERROR)) {
      logger.error(exception.getMessage(), exception);
    } else {
      logger.info(exception.getMessage(), exception);
    }
    return super.handleExceptionInternal(
        exception,
        errorsResponse != null
            ? errorsResponse
            : getErrorsResponse(exception, status, code, request),
        headers,
        status,
        request);
  }

  private ErrorsResponse getErrorsResponse(
      final DomainException exception,
      final HttpStatus status,
      final String code,
      final WebRequest request) {
    return new ErrorsResponse(findErrorDomainResponses(exception, status, code, request));
  }
}
