package com.webservice.microservice.test.gateway.gateway.exception;


import org.springframework.http.HttpStatus;

public enum ErrorInfo {

    REFERENCE_RESSOURCE_REQUIRED("#REFERENCE_RESSOURCE_REQUIRED", HttpStatus.BAD_REQUEST),
    RESSOURCE_NOT_FOUND("#RESSOURCE_NOT_FOUND", HttpStatus.NOT_FOUND),
    REFERENCE_RESSOURCE_ALREADY_USED("#REFERENCE_RESSOURCE_ALREADY_USED", HttpStatus.BAD_REQUEST);

    private final String message;
    private final HttpStatus httpStatus;

    ErrorInfo(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
