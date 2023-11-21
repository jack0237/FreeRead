package com.Icekiwi.Freeread.exceptions;
import com.Icekiwi.Freeread.rest.dto.responses.error.ErrorsResponse;

public class ForbiddenException extends DomainException {

  public static final String NO_ACCESS = "Not allowed to access";

  public ForbiddenException(String message) {
    super(message);
  }

  public ForbiddenException(String message, ErrorsResponse errorsResponse) {
    super(message, errorsResponse);
  }

  public ForbiddenException(final String title, final String detail) {
    super(title, detail);
  }
}
