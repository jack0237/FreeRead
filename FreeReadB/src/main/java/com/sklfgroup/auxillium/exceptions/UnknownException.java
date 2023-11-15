package com.sklfgroup.auxillium.exceptions;

import com.sklfgroup.auxillium.rest.dto.responses.error.ErrorsResponse;

public class UnknownException extends DomainException {

  public UnknownException(final String message) {
    super(message);
  }

  public UnknownException(final String message, final ErrorsResponse errorsResponse) {
    super(message, errorsResponse);
  }
}
