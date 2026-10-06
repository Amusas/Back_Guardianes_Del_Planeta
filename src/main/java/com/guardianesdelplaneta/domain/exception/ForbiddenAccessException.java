package com.guardianesdelplaneta.domain.exception;

public class ForbiddenAccessException extends DomainException {
    public ForbiddenAccessException(String message) {
        super(message);
    }
}
