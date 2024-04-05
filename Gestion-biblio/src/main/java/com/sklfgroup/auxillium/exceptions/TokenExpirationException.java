package com.sklfgroup.auxillium.exceptions;

import com.sklfgroup.auxillium.rest.dto.responses.error.ErrorsResponse;

public class TokenExpirationException extends DomainException {

    public static final String TOKEN_EXPIRED = "Token expired";

    public TokenExpirationException(final String message) {
        super(message);
    }

    public TokenExpirationException(String message, ErrorsResponse errorsResponse) {
        super(message, errorsResponse);
    }
}