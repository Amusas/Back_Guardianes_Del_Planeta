package com.guardianesdelplaneta.domain.port.in;

import com.guardianesdelplaneta.domain.model.User;

/**
 * Puerto de entrada para el caso de uso de registro y creación de usuarios.
 */
public interface CreateUserUseCase {

    /**
     * Registra un nuevo usuario en la plataforma aplicando las reglas de negocio.
     *
     * @param user        Entidad de dominio con los datos básicos del usuario.
     * @param rawPassword Contraseña en texto plano a ser codificada de forma segura.
     * @return El usuario creado con su ID y fechas asignadas.
     */
    User createUser(User user, String rawPassword);
}
