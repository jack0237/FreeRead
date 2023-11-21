package com.Icekiwi.Freeread.exceptions;

import com.Icekiwi.Freeread.rest.dto.responses.error.ErrorsResponse;

public class MissingRequiredParameterException extends DomainException {

  public static final String ERROR_MISSING_PARAMETER = "Service missing the following parameter : ";
  public static final String MISSING_REQUIRED_FIELD = "MISSING_REQUIRED_FIELD";
  public static final String MISSING = "Missing ";

  public MissingRequiredParameterException(final String field) {
    super(MISSING + field, ERROR_MISSING_PARAMETER + field, MISSING_REQUIRED_FIELD);
  }

  public MissingRequiredParameterException(String message, ErrorsResponse errorsResponse) {
    super(message, errorsResponse);
  }
}
