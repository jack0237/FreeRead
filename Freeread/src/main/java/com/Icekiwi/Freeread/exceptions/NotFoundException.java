package com.Icekiwi.Freeread.exceptions;

import com.Icekiwi.Freeread.rest.dto.responses.error.ErrorsResponse;

public class NotFoundException extends DomainException {

  public NotFoundException(String message) {
    super(message);
  }

  public NotFoundException(String message, ErrorsResponse errorsResponse) {
    super(message, errorsResponse);
  }
}
