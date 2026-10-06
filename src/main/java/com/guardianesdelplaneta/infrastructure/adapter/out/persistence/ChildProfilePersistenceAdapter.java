package com.guardianesdelplaneta.infrastructure.adapter.out.persistence;

import com.guardianesdelplaneta.domain.model.ChildProfile;
import com.guardianesdelplaneta.domain.port.out.ChildProfilePersistencePort;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.mapper.ChildProfilePersistenceMapper;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.repository.ChildProfileRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ChildProfilePersistenceAdapter implements ChildProfilePersistencePort {
    private final ChildProfileRepository repository;
    private final ChildProfilePersistenceMapper mapper;

    public ChildProfilePersistenceAdapter(ChildProfileRepository repository, ChildProfilePersistenceMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Optional<ChildProfile> findByUserId(Long userId) {
        return repository.findByUserId(userId).map(mapper::toDomain);
    }
}
