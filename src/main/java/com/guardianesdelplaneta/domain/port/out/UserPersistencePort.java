package com.guardianesdelplaneta.domain.port.out;

import com.guardianesdelplaneta.domain.model.User;

import java.util.Optional;

/**
 * Puerto de salida para las operaciones de persistencia del usuario.
 */
public interface UserPersistencePort {

    /**
     * Guarda o actualiza un usuario en la base de datos.
     *
     * @param user Usuario a persistir.
     * @return Usuario persistido.
     */
    User save(User user);

    /**
     * Busca un usuario por su nombre de usuario único.
     *
     * @param username Nombre de usuario.
     * @return Optional con el usuario si existe.
     */
    Optional<User> findByUsername(String username);

    /**
     * Verifica la existencia de un usuario por su nombre de usuario.
     *
     * @param username Nombre de usuario.
     * @return true si ya existe, false en caso contrario.
     */
    boolean existsByUsername(String username);
}
