package com.guardianesdelplaneta.application.service;

import com.guardianesdelplaneta.domain.exception.UserAlreadyExistsException;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateUserServiceTest {

    @Mock
    private UserPersistencePort userPersistencePort;

    @Mock
    private PasswordEncoderPort passwordEncoderPort;

    @InjectMocks
    private CreateUserService createUserService;

    @Test
    @DisplayName("Debe registrar un nuevo usuario exitosamente con contraseña cifrada y estado ACTIVO")
    void shouldCreateUserSuccessfully() {
        // Arrange
        User user = new User();
        user.setUsername("guardian1");
        user.setRole(UserRole.GUARDIAN);

        when(userPersistencePort.existsByUsername("guardian1")).thenReturn(false);
        when(passwordEncoderPort.encode("pass123")).thenReturn("hashedPass123");
        when(userPersistencePort.save(any(User.class))).thenAnswer(invocation -> {
            User u = invocation.getArgument(0);
            u.setId(10L);
            return u;
        });

        // Act
        User createdUser = createUserService.createUser(user, "pass123");

        // Assert
        assertNotNull(createdUser);
        assertEquals(10L, createdUser.getId());
        assertEquals("guardian1", createdUser.getUsername());
        assertEquals("hashedPass123", createdUser.getPasswordHash());
        assertEquals(UserStatus.ACTIVE, createdUser.getStatus());
        assertNotNull(createdUser.getCreatedAt());

        verify(userPersistencePort).existsByUsername("guardian1");
        verify(passwordEncoderPort).encode("pass123");
        verify(userPersistencePort).save(any(User.class));
    }

    @Test
    @DisplayName("Debe lanzar UserAlreadyExistsException cuando el username ya existe")
    void shouldThrowExceptionWhenUsernameAlreadyExists() {
        // Arrange
        User user = new User();
        user.setUsername("guardian_existente");

        when(userPersistencePort.existsByUsername("guardian_existente")).thenReturn(true);

        // Act & Assert
        assertThrows(UserAlreadyExistsException.class, () ->
                createUserService.createUser(user, "pass123")
        );

        verify(passwordEncoderPort, never()).encode(any());
        verify(userPersistencePort, never()).save(any());
    }
}
