package com.guardianesdelplaneta.infrastructure.adapter.out.persistence;

import com.guardianesdelplaneta.domain.model.ActivityAttempt;
import com.guardianesdelplaneta.domain.port.out.ActivityAttemptPersistencePort;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.mapper.ActivityAttemptPersistenceMapper;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.repository.ActivityAttemptRepository;
import org.springframework.stereotype.Component;

@Component
public class ActivityAttemptPersistenceAdapter implements ActivityAttemptPersistencePort {
    private final ActivityAttemptRepository repository;
    private final ActivityAttemptPersistenceMapper mapper;

    public ActivityAttemptPersistenceAdapter(ActivityAttemptRepository repository, ActivityAttemptPersistenceMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public ActivityAttempt save(ActivityAttempt attempt) {
        return mapper.toDomain(repository.save(mapper.toEntity(attempt)));
    }

    @Override
    public long countByChildIdAndActivityId(Long childId, Long activityId) {
        return repository.countByChildIdAndActivityId(childId, activityId);
    }
}
