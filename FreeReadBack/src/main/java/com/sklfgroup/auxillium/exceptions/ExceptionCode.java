package com.sklfgroup.auxillium.exceptions;

public enum ExceptionCode {
    BAD_REQUEST,
    TOO_LONG,
    REQUIRED,
    ALREADY_EXISTS,
    NOT_FOUND,
    UNKNOWN_ERROR,
    MAX_USAGE_REACHED,
    EXPIRED,
    NOT_ACTIVATED;

    public static ExceptionCode getCodeFromException(Exception exception) {

        if (exception instanceof MissingRequiredParameterException || exception instanceof MissingHeaderParameterException) {
            return REQUIRED;
        }

        if (exception instanceof SessionExpiredException) {
            return EXPIRED;
        }

        if (exception instanceof NotFoundException) {
            return NOT_FOUND;
        }

        if (exception instanceof AlreadyExistsException) {
            return ALREADY_EXISTS;
        }

        return BAD_REQUEST;
    }
}
