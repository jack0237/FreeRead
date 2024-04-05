package com.sklfgroup.auxillium.exceptions;

import com.sklfgroup.auxillium.rest.dto.responses.error.ErrorsResponse;

public class NotFoundException extends DomainException {

  public NotFoundException(String message) {
    super(message);
  }

  public NotFoundException(String message, ErrorsResponse errorsResponse) {
    super(message, errorsResponse);
  }
}
