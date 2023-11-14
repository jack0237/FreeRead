package com.sklfgroup.auxillium.exceptions;

public class WrongCredentialsException extends DomainException {

  public WrongCredentialsException(String message) {
    super(message);
  }

  public WrongCredentialsException(String message, String detail) {
    super(message, detail);
  }
}
