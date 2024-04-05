package com.sklfgroup.auxillium.rest.exceptionhandler;

import com.sklfgroup.auxillium.helpers.CustomHeaders;
import com.sklfgroup.auxillium.rest.dto.responses.error.ErrorResponse;
import com.sklfgroup.auxillium.exceptions.DomainException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static java.util.Optional.ofNullable;

public abstract class AbstractExceptionHandler extends ResponseEntityExceptionHandler {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  public List<ErrorResponse> findErrorResponses(
      final Exception exception,
      final HttpStatus status,
      final String code,
      final WebRequest request) {
    final List<ErrorResponse> errors = new ArrayList<>();
    errors.add(


        new ErrorResponse(
            getCorrelationId(request),
            String.valueOf(status.value()),
            status.getReasonPhrase(),
            code,
            exception.getMessage()));
    return errors;
  }

  public List<ErrorResponse> findErrorDomainResponses(
      final DomainException exception,
      final HttpStatus status,
      final String code,
      final WebRequest request) {
    final List<ErrorResponse> errors = new ArrayList<>();
    errors.add(
        new ErrorResponse(
            getCorrelationId(request),
            String.valueOf(status.value()),
            exception.getTitle(),
            code,
            exception.getMessage()));
    return errors;
  }

  public String getCorrelationId(final WebRequest request) {
    return ofNullable(
            (String)
                request.getAttribute(CustomHeaders.CORRELATION_ID, RequestAttributes.SCOPE_REQUEST))
        .orElse(UUID.randomUUID().toString());
  }
}
