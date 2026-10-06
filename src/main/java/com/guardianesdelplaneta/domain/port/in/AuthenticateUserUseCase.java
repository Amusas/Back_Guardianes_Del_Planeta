package com.guardianesdelplaneta.domain.port.in;

import com.guardianesdelplaneta.domain.model.User;

/**
 * Puerto de entrada para el caso de uso de autenticación e inicio de sesión.
 */
public interface AuthenticateUserUseCase {

    /**
     * Valida las credenciales proporcionadas y el estado de la cuenta.
     *
     * @param username    Nombre de usuario único.
     * @param rawPassword Contraseña en texto plano a verificar.
     * @return El usuario autenticado si las credenciales y el estado son válidos.
     */
    User authenticate(String username, String rawPassword);
}
