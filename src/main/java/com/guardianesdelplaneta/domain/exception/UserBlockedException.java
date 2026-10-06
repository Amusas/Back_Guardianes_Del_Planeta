package com.guardianesdelplaneta.domain.exception;

public class UserBlockedException extends DomainException {

    public UserBlockedException(String username) {
        super("La cuenta del usuario '" + username + "' se encuentra bloqueada o inactiva.");
    }
}
