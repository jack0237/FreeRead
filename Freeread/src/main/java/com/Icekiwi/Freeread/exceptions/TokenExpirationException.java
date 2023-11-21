package com.Icekiwi.Freeread.exceptions;

import com.Icekiwi.Freeread.rest.dto.responses.error.ErrorsResponse;

public class TokenExpirationException extends DomainException {

    public static final String TOKEN_EXPIRED = "Token expired";

    public TokenExpirationException(final String message) {
        super(message);
    }

    public TokenExpirationException(String message, ErrorsResponse errorsResponse) {
        super(message, errorsResponse);
    }
}