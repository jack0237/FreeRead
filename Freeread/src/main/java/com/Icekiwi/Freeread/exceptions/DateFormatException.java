package com.Icekiwi.Freeread.exceptions;

import com.Icekiwi.Freeread.rest.dto.responses.error.ErrorsResponse;

public class DateFormatException extends DomainException {

  public DateFormatException(String message) {
    super(message);
  }

  public DateFormatException(String message, ErrorsResponse errorsResponse) {
    super(message, errorsResponse);
  }
}
