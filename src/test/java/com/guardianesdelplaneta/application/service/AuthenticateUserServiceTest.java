package com.guardianesdelplaneta.application.service;

import com.guardianesdelplaneta.domain.exception.InvalidCredentialsException;
import com.guardianesdelplaneta.domain.exception.UserBlockedException;
import com.guardianesdelplaneta.domain.model.User;
import com.guardianesdelplaneta.domain.model.enums.UserRole;
import com.guardianesdelplaneta.domain.model.enums.UserStatus;
import com.guardianesdelplaneta.domain.port.out.PasswordEncoderPort;
import com.guardianesdelplaneta.domain.port.out.UserPersistencePort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthenticateUserServiceTest {

    @Mock
    private UserPersistencePort userPersistencePort;

    @Mock
    private PasswordEncoderPort passwordEncoderPort;

    @InjectMocks
    private AuthenticateUserService authenticateUserService;

    @Test
    @DisplayName("Debe autenticar correctamente cuando las credenciales son válidas y el usuario está ACTIVO")
    void shouldAuthenticateSuccessfully() {
        // Arrange
        User user = new User();
        user.setId(1L);
        user.setUsername("nino_guardian");
        user.setPasswordHash("hashed_secret");
        user.setRole(UserRole.CHILD);
        user.setStatus(UserStatus.ACTIVE);

        when(userPersistencePort.findByUsername("nino_guardian")).thenReturn(Optional.of(user));
        when(passwordEncoderPort.matches("secret123", "hashed_secret")).thenReturn(true);

        // Act
        User authenticated = authenticateUserService.authenticate("nino_guardian", "secret123");

        // Assert
        assertNotNull(authenticated);
        assertEquals("nino_guardian", authenticated.getUsername());
        verify(userPersistencePort).findByUsername("nino_guardian");
        verify(passwordEncoderPort).matches("secret123", "hashed_secret");
    }

    @Test
    @DisplayName("Debe lanzar InvalidCredentialsException si el usuario no existe")
    void shouldThrowExceptionWhenUserNotFound() {
        // Arrange
        when(userPersistencePort.findByUsername("fantasma")).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(InvalidCredentialsException.class, () ->
                authenticateUserService.authenticate("fantasma", "secret")
        );
    }

    @Test
    @DisplayName("Debe lanzar InvalidCredentialsException si la contraseña no coincide")
    void shouldThrowExceptionWhenPasswordDoesNotMatch() {
        // Arrange
        User user = new User();
        user.setUsername("nino_guardian");
        user.setPasswordHash("hashed_secret");
        user.setStatus(UserStatus.ACTIVE);

        when(userPersistencePort.findByUsername("nino_guardian")).thenReturn(Optional.of(user));
        when(passwordEncoderPort.matches("incorrecta", "hashed_secret")).thenReturn(false);

        // Act & Assert
        assertThrows(InvalidCredentialsException.class, () ->
                authenticateUserService.authenticate("nino_guardian", "incorrecta")
        );
    }

    @Test
    @DisplayName("Debe lanzar UserBlockedException si el usuario está bloqueado")
    void shouldThrowExceptionWhenUserIsBlocked() {
        // Arrange
        User user = new User();
        user.setUsername("nino_bloqueado");
        user.setPasswordHash("hashed_secret");
        user.setStatus(UserStatus.BLOCKED);

        when(userPersistencePort.findByUsername("nino_bloqueado")).thenReturn(Optional.of(user));

        // Act & Assert
        assertThrows(UserBlockedException.class, () ->
                authenticateUserService.authenticate("nino_bloqueado", "secret123")
        );
    }
}
