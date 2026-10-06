package com.guardianesdelplaneta.domain.exception;

public class InvalidCredentialsException extends DomainException {

    public InvalidCredentialsException() {
        super("Credenciales inválidas. Por favor verifique el usuario y la contraseña.");
    }
}
