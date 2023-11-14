package com.sklfgroup.auxillium.exceptions;

import com.sklfgroup.auxillium.rest.dto.responses.error.ErrorsResponse;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DomainException extends RuntimeException {
  private final ErrorsResponse errorsResponse;
  private final String title;
  private final String code;

  public DomainException(final String message) {
    super(message);
    this.errorsResponse = null;
    this.title = null;
    this.code = null;
  }

  public DomainException(final String message, final ErrorsResponse errorsResponse) {
    super(message);
    this.errorsResponse = errorsResponse;
    this.title = null;
    this.code = null;
  }

  public DomainException(final String title, final String message) {
    super(message);
    this.errorsResponse = null;
    this.title = title;
    this.code = null;
  }

  public DomainException(final String title, final String message, final String code) {
    super(message);
    this.errorsResponse = null;
    this.title = title;
    this.code = code;
  }
}
