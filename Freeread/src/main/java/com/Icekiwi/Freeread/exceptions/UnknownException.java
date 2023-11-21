package com.Icekiwi.Freeread.exceptions;

import com.Icekiwi.Freeread.rest.dto.responses.error.ErrorsResponse;

public class UnknownException extends DomainException {

  public UnknownException(final String message) {
    super(message);
  }

  public UnknownException(final String message, final ErrorsResponse errorsResponse) {
    super(message, errorsResponse);
  }
}
