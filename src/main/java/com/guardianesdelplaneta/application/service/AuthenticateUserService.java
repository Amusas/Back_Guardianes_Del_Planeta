package com.guardianesdelplaneta.application.service;

import com.guardianesdelplaneta.domain.exception.InvalidCredentialsException;
import com.guardianesdelplaneta.domain.exception.UserBlockedException;
import com.guardianesdelplaneta.domain.model.User;
import com.guardianesdelplaneta.domain.model.enums.UserStatus;
import com.guardianesdelplaneta.domain.port.in.AuthenticateUserUseCase;
import com.guardianesdelplaneta.domain.port.out.PasswordEncoderPort;
import com.guardianesdelplaneta.domain.port.out.UserPersistencePort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio de aplicación que implementa el caso de uso de autenticación de usuarios.
 */
@Service
public class AuthenticateUserService implements AuthenticateUserUseCase {

    private static final Logger log = LoggerFactory.getLogger(AuthenticateUserService.class);

    private final UserPersistencePort userPersistencePort;
    private final PasswordEncoderPort passwordEncoderPort;

    public AuthenticateUserService(UserPersistencePort userPersistencePort, PasswordEncoderPort passwordEncoderPort) {
        this.userPersistencePort = userPersistencePort;
        this.passwordEncoderPort = passwordEncoderPort;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public User authenticate(String username, String rawPassword) {
        log.info("Intento de inicio de sesión para el usuario: '{}'", username);

        User user = userPersistencePort.findByUsername(username)
                .orElseThrow(() -> {
                    log.warn("Inicio de sesión fallido: usuario '{}' no encontrado en el sistema.", username);
                    return new InvalidCredentialsException();
                });

        if (user.getStatus() == UserStatus.BLOCKED || user.getStatus() == UserStatus.INACTIVE) {
            log.warn("Inicio de sesión rechazado: usuario '{}' tiene estado '{}'", username, user.getStatus());
            throw new UserBlockedException(username);
        }

        log.debug("Verificando coincidencia de contraseña cifrada para el usuario '{}'", username);
        boolean matches = passwordEncoderPort.matches(rawPassword, user.getPasswordHash());

        if (!matches) {
            log.warn("Inicio de sesión fallido: contraseña incorrecta para el usuario '{}'", username);
            throw new InvalidCredentialsException();
        }

        log.info("Autenticación exitosa para el usuario '{}' con rol: '{}'", user.getUsername(), user.getRole());
        return user;
    }
}
