package com.sklfgroup.auxillium.exceptions;

import com.sklfgroup.auxillium.rest.dto.responses.error.ErrorsResponse;

import static java.lang.String.format;

public class MissingHeaderParameterException extends DomainException {

  public static final String ERROR_MISSING_HEADER =
      "Required request header '%s' for method parameter is not present";
  public static final String MISSING_HEADER_FIELD = "MISSING_HEADER_FIELD";
  public static final String MISSING = "Missing ";

  public MissingHeaderParameterException(final String field) {
    super(MISSING + field, format(ERROR_MISSING_HEADER, field), MISSING_HEADER_FIELD);
  }

  public MissingHeaderParameterException(String message, ErrorsResponse errorsResponse) {
    super(message, errorsResponse);
  }
}
