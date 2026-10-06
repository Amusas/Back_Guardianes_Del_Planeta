package com.guardianesdelplaneta.infrastructure.adapter.out.persistence;

import com.guardianesdelplaneta.domain.model.User;
import com.guardianesdelplaneta.domain.port.out.UserPersistencePort;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.entity.UserEntity;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.mapper.UserPersistenceMapper;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Adaptador de salida que implementa UserPersistencePort contra la base de datos MySQL mediante Spring Data JPA.
 */
@Component
public class UserPersistenceAdapter implements UserPersistencePort {

    private static final Logger log = LoggerFactory.getLogger(UserPersistenceAdapter.class);

    private final UserRepository userRepository;
    private final UserPersistenceMapper userPersistenceMapper;

    public UserPersistenceAdapter(UserRepository userRepository, UserPersistenceMapper userPersistenceMapper) {
        this.userRepository = userRepository;
        this.userPersistenceMapper = userPersistenceMapper;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User save(User user) {
        log.debug("Persistiendo usuario '{}' en MySQL...", user.getUsername());
        UserEntity entity = userPersistenceMapper.toEntity(user);
        UserEntity savedEntity = userRepository.save(entity);
        return userPersistenceMapper.toDomain(savedEntity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<User> findByUsername(String username) {
        log.debug("Consultando usuario por username: '{}'", username);
        return userRepository.findByUsername(username)
                .map(userPersistenceMapper::toDomain);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean existsByUsername(String username) {
        log.debug("Comprobando existencia del usuario: '{}'", username);
        return userRepository.existsByUsername(username);
    }
}
