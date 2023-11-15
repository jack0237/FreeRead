package com.sklfgroup.auxillium.rest.exceptionhandler;

import com.sklfgroup.auxillium.rest.dto.responses.error.ErrorResponse;
import com.sklfgroup.auxillium.rest.dto.responses.error.ErrorsResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

//@ControllerAdvice(assignableTypes = AuthorizationController.class)
public class AuthorizationControllerExceptionHandler extends AbstractExceptionHandler {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @ExceptionHandler(
      value = {MissingRequestHeaderException.class})
  protected ResponseEntity<Object> handleMissingHeaderException(
      final Exception exception, final WebRequest request) {
    return handleException(
        exception, new HttpHeaders(), HttpStatus.BAD_REQUEST, "INVALID_FIELD", request);
  }

  public ResponseEntity<Object> handleException(
      final Exception exception,
      final HttpHeaders headers,
      final HttpStatus status,
      final String code,
      final WebRequest request) {
    final List<ErrorResponse> errors =
        findErrorResponses(exception, status, code, request);
    if (status.equals(HttpStatus.INTERNAL_SERVER_ERROR)) {
      logger.error(exception.getMessage(), exception);
    } else {
      logger.info(exception.getMessage(), exception);
    }
    return super.handleExceptionInternal(
        exception, new ErrorsResponse(errors), headers, status, request);
  }
}
