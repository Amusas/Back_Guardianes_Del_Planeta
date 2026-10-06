package com.guardianesdelplaneta.domain.exception;

public class UserAlreadyExistsException extends DomainException {

    public UserAlreadyExistsException(String username) {
        super("El usuario '" + username + "' ya se encuentra registrado en el sistema.");
    }
}
