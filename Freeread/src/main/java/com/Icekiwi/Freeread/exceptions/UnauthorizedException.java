package com.Icekiwi.Freeread.exceptions;
import com.Icekiwi.Freeread.rest.dto.responses.error.ErrorsResponse;

public class UnauthorizedException extends DomainException {

  public static final String NO_ACCESS = "Not allowed to access";

  public UnauthorizedException(final String message) {
    super(message);
  }

  public UnauthorizedException(final String message, final ErrorsResponse errorsResponse) {
    super(message, errorsResponse);
  }

  public UnauthorizedException(final String title, final String detail) {
    super(title, detail);
  }

  public UnauthorizedException(final DomainException exception) {
    super(exception.getTitle(), exception.getMessage());
  }
}
