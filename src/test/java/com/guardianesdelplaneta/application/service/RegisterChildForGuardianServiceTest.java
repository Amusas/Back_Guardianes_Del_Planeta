package com.guardianesdelplaneta.application.service;

import com.guardianesdelplaneta.domain.exception.ForbiddenAccessException;
import com.guardianesdelplaneta.domain.exception.UserAlreadyExistsException;
import com.guardianesdelplaneta.domain.model.ChildProfile;
import com.guardianesdelplaneta.domain.model.enums.UserRole;
import com.guardianesdelplaneta.domain.model.enums.UserStatus;
import com.guardianesdelplaneta.domain.port.out.PasswordEncoderPort;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.entity.ChildProfileEntity;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.entity.GuardianChildEntity;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.entity.LevelEntity;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.entity.UserEntity;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.mapper.ChildProfilePersistenceMapper;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.repository.ChildProfileRepository;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.repository.GuardianChildRepository;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.repository.LevelRepository;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RegisterChildForGuardianServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoderPort passwordEncoderPort;

    @Mock
    private ChildProfileRepository childProfileRepository;

    @Mock
    private LevelRepository levelRepository;

    @Mock
    private GuardianChildRepository guardianChildRepository;

    @Mock
    private ChildProfilePersistenceMapper childProfilePersistenceMapper;

    private RegisterChildForGuardianService service;

    @BeforeEach
    void setUp() {
        service = new RegisterChildForGuardianService(
                userRepository,
                passwordEncoderPort,
                childProfileRepository,
                levelRepository,
                guardianChildRepository,
                childProfilePersistenceMapper
        );
    }

    @Test
    void registerChildForGuardian_Success() {
        Long guardianId = 1L;
        UserEntity guardianUser = UserEntity.builder().id(guardianId).username("mama123").role(UserRole.GUARDIAN).build();
        when(userRepository.findById(guardianId)).thenReturn(Optional.of(guardianUser));
        when(userRepository.existsByUsername("hijo123")).thenReturn(false);
        when(passwordEncoderPort.encode("secret123")).thenReturn("hashedPassword");

        UserEntity savedUser = UserEntity.builder()
                .id(2L)
                .username("hijo123")
                .passwordHash("hashedPassword")
                .role(UserRole.CHILD)
                .status(UserStatus.ACTIVE)
                .build();
        when(userRepository.save(any(UserEntity.class))).thenReturn(savedUser);

        LevelEntity level1 = LevelEntity.builder().id(1L).levelNumber(1).name("Semilla Guardiana").build();
        when(levelRepository.findByLevelNumber(1)).thenReturn(Optional.of(level1));

        ChildProfileEntity savedProfile = ChildProfileEntity.builder()
                .id(10L)
                .user(savedUser)
                .name("Pedrito")
                .dateOfBirth(LocalDate.of(2017, 3, 1))
                .totalXp(0)
                .streakCount(0)
                .level(level1)
                .build();
        when(childProfileRepository.save(any(ChildProfileEntity.class))).thenReturn(savedProfile);

        ChildProfile domainMock = new ChildProfile(10L, 2L, "Pedrito", LocalDate.of(2017, 3, 1), 0, 1L, 0, null, null);
        when(childProfilePersistenceMapper.toDomain(savedProfile)).thenReturn(domainMock);

        ChildProfile result = service.registerChildForGuardian(
                guardianId, "hijo123", "secret123", "Pedrito", LocalDate.of(2017, 3, 1)
        );

        assertNotNull(result);
        assertEquals("Pedrito", result.getName());
        assertEquals(10L, result.getId());

        // Verify child user creation has role CHILD
        ArgumentCaptor<UserEntity> userCaptor = ArgumentCaptor.forClass(UserEntity.class);
        verify(userRepository).save(userCaptor.capture());
        assertEquals(UserRole.CHILD, userCaptor.getValue().getRole());
        assertEquals("hijo123", userCaptor.getValue().getUsername());

        // Verify guardian child association persisted
        ArgumentCaptor<GuardianChildEntity> linkCaptor = ArgumentCaptor.forClass(GuardianChildEntity.class);
        verify(guardianChildRepository).save(linkCaptor.capture());
        assertEquals(guardianUser, linkCaptor.getValue().getGuardian());
        assertEquals(savedProfile, linkCaptor.getValue().getChild());
    }

    @Test
    void registerChildForGuardian_ThrowsWhenGuardianNotFound() {
        when(userRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ForbiddenAccessException.class, () ->
                service.registerChildForGuardian(99L, "hijo", "123456", "Pedrito", LocalDate.of(2017, 1, 1))
        );
    }

    @Test
    void registerChildForGuardian_ThrowsWhenUsernameAlreadyExists() {
        UserEntity guardianUser = UserEntity.builder().id(1L).username("mama").role(UserRole.GUARDIAN).build();
        when(userRepository.findById(1L)).thenReturn(Optional.of(guardianUser));
        when(userRepository.existsByUsername("yaExiste")).thenReturn(true);

        assertThrows(UserAlreadyExistsException.class, () ->
                service.registerChildForGuardian(1L, "yaExiste", "123456", "Pedrito", LocalDate.of(2017, 1, 1))
        );
    }
}
