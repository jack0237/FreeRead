package com.Icekiwi.Freeread.exceptions;

public class BadRequestException extends Exception {

  private final ExceptionTypes type;
  private final ExceptionCode code;

  public BadRequestException(String message, ExceptionTypes type) {
    super(message);
    this.type = type;
    this.code = ExceptionCode.BAD_REQUEST;
  }

  public BadRequestException(String message, ExceptionTypes type, ExceptionCode code) {
    super(message);
    this.type = type;
    this.code = code;
  }

  public ExceptionTypes getType() {
    return type;
  }

  public ExceptionCode getCode() {
    return code;
  }
}
