package com.guardianesdelplaneta.infrastructure.adapter.out.persistence;

import com.guardianesdelplaneta.domain.model.Activity;
import com.guardianesdelplaneta.domain.port.out.ActivityPersistencePort;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.mapper.ActivityPersistenceMapper;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.repository.ActivityRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ActivityPersistenceAdapter implements ActivityPersistencePort {
    private final ActivityRepository repository;
    private final ActivityPersistenceMapper mapper;

    public ActivityPersistenceAdapter(ActivityRepository repository, ActivityPersistenceMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Optional<Activity> findById(Long id) {
        return repository.findById(id).map(mapper::toDomain);
    }
}
