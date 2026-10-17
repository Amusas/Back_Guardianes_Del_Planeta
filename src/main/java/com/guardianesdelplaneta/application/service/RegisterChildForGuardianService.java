package com.guardianesdelplaneta.application.service;

import com.guardianesdelplaneta.domain.exception.ForbiddenAccessException;
import com.guardianesdelplaneta.domain.exception.UserAlreadyExistsException;
import com.guardianesdelplaneta.domain.model.ChildProfile;
import com.guardianesdelplaneta.domain.model.enums.RelationshipType;
import com.guardianesdelplaneta.domain.model.enums.UserRole;
import com.guardianesdelplaneta.domain.model.enums.UserStatus;
import com.guardianesdelplaneta.domain.port.in.RegisterChildForGuardianUseCase;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class RegisterChildForGuardianService implements RegisterChildForGuardianUseCase {

    private static final Logger log = LoggerFactory.getLogger(RegisterChildForGuardianService.class);

    private final UserRepository userRepository;
    private final PasswordEncoderPort passwordEncoderPort;
    private final ChildProfileRepository childProfileRepository;
    private final LevelRepository levelRepository;
    private final GuardianChildRepository guardianChildRepository;
    private final ChildProfilePersistenceMapper childProfilePersistenceMapper;

    public RegisterChildForGuardianService(
            UserRepository userRepository,
            PasswordEncoderPort passwordEncoderPort,
            ChildProfileRepository childProfileRepository,
            LevelRepository levelRepository,
            GuardianChildRepository guardianChildRepository,
            ChildProfilePersistenceMapper childProfilePersistenceMapper) {
        this.userRepository = userRepository;
        this.passwordEncoderPort = passwordEncoderPort;
        this.childProfileRepository = childProfileRepository;
        this.levelRepository = levelRepository;
        this.guardianChildRepository = guardianChildRepository;
        this.childProfilePersistenceMapper = childProfilePersistenceMapper;
    }

    @Override
    @Transactional
    public ChildProfile registerChildForGuardian(Long guardianUserId, String username, String password, String name, LocalDate dateOfBirth) {
        log.info("Iniciando registro de niño '{}' por parte del acudiente ID {}", username, guardianUserId);

        // 1. Validar que el acudiente exista
        UserEntity guardianUser = userRepository.findById(guardianUserId)
                .orElseThrow(() -> {
                    log.error("Acudiente con ID {} no encontrado para vincular niño", guardianUserId);
                    return new ForbiddenAccessException("Acudiente no encontrado");
                });

        // 2. Validar que el username no esté en uso
        if (userRepository.existsByUsername(username)) {
            log.warn("El nombre de usuario infantil '{}' ya se encuentra registrado", username);
            throw new UserAlreadyExistsException(username);
        }

        // 3. Crear el usuario infantil con rol CHILD
        String passwordHash = passwordEncoderPort.encode(password);
        UserEntity childUser = UserEntity.builder()
                .username(username)
                .passwordHash(passwordHash)
                .role(UserRole.CHILD)
                .status(UserStatus.ACTIVE)
                .build();
        UserEntity savedChildUser = userRepository.save(childUser);
        log.debug("Usuario infantil '{}' creado con ID {}", savedChildUser.getUsername(), savedChildUser.getId());

        // 4. Obtener nivel inicial (Nivel 1: Semilla Guardiana)
        LevelEntity initialLevel = levelRepository.findByLevelNumber(1)
                .orElseGet(() -> levelRepository.findAll().stream().findFirst().orElse(null));

        // 5. Crear el perfil infantil
        ChildProfileEntity profileEntity = ChildProfileEntity.builder()
                .user(savedChildUser)
                .name(name)
                .dateOfBirth(dateOfBirth)
                .totalXp(0)
                .streakCount(0)
                .level(initialLevel)
                .build();
        ChildProfileEntity savedProfile = childProfileRepository.save(profileEntity);
        log.debug("Perfil infantil creado con ID {}", savedProfile.getId());

        // 6. Crear la vinculación GuardianChild
        GuardianChildEntity link = GuardianChildEntity.builder()
                .guardian(guardianUser)
                .child(savedProfile)
                .relationshipType(RelationshipType.LEGAL_GUARDIAN)
                .isPrimary(true)
                .build();
        guardianChildRepository.save(link);
        log.info("Vinculación de acudiente {} con niño {} completada exitosamente", guardianUserId, savedProfile.getId());

        return childProfilePersistenceMapper.toDomain(savedProfile);
    }
}
