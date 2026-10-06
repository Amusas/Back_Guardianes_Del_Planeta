package com.guardianesdelplaneta.application.service;

import com.guardianesdelplaneta.domain.exception.UserAlreadyExistsException;
import com.guardianesdelplaneta.domain.model.User;
import com.guardianesdelplaneta.domain.model.enums.UserStatus;
import com.guardianesdelplaneta.domain.port.in.CreateUserUseCase;
import com.guardianesdelplaneta.domain.port.out.PasswordEncoderPort;
import com.guardianesdelplaneta.domain.port.out.UserPersistencePort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * Servicio de aplicación que implementa el caso de uso de registro de usuarios.
 */
@Service
public class CreateUserService implements CreateUserUseCase {

    private static final Logger log = LoggerFactory.getLogger(CreateUserService.class);

    private final UserPersistencePort userPersistencePort;
    private final PasswordEncoderPort passwordEncoderPort;

    public CreateUserService(UserPersistencePort userPersistencePort, PasswordEncoderPort passwordEncoderPort) {
        this.userPersistencePort = userPersistencePort;
        this.passwordEncoderPort = passwordEncoderPort;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public User createUser(User user, String rawPassword) {
        log.info("Iniciando proceso de registro para el usuario: '{}' con rol: '{}'", user.getUsername(), user.getRole());

        if (userPersistencePort.existsByUsername(user.getUsername())) {
            log.warn("Intento de registro fallido: el nombre de usuario '{}' ya existe.", user.getUsername());
            throw new UserAlreadyExistsException(user.getUsername());
        }

        log.debug("Cifrando contraseña de forma segura para el usuario '{}'", user.getUsername());
        String passwordHash = passwordEncoderPort.encode(rawPassword);
        user.setPasswordHash(passwordHash);

        if (user.getStatus() == null) {
            user.setStatus(UserStatus.ACTIVE);
        }

        LocalDateTime now = LocalDateTime.now();
        user.setCreatedAt(now);
        user.setUpdatedAt(now);

        User savedUser = userPersistencePort.save(user);
        log.info("Usuario '{}' registrado exitosamente con ID: {}", savedUser.getUsername(), savedUser.getId());

        return savedUser;
    }
}
