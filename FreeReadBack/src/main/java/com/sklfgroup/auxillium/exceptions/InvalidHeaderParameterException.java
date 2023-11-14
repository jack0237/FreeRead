package com.sklfgroup.auxillium.exceptions;

import static java.lang.String.format;

public class InvalidHeaderParameterException extends DomainException {

  public static final String ERROR_MISSING_HEADER = "Required request header '%s' for method parameter is invalid";
  public static final String INVALID_HEADER_FIELD = "INVALID_HEADER_FIELD";
  public static final String INVALID = "Invalid ";

  public InvalidHeaderParameterException(final String field) {
    super(INVALID + field, format(ERROR_MISSING_HEADER, field), INVALID_HEADER_FIELD);
  }
}
