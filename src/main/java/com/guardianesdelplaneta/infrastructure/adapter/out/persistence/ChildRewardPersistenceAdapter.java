package com.guardianesdelplaneta.infrastructure.adapter.out.persistence;

import com.guardianesdelplaneta.domain.model.ChildReward;
import com.guardianesdelplaneta.domain.port.out.ChildRewardPersistencePort;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.mapper.ChildRewardPersistenceMapper;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.repository.ChildRewardRepository;
import org.springframework.stereotype.Component;

@Component
public class ChildRewardPersistenceAdapter implements ChildRewardPersistencePort {
    private final ChildRewardRepository repository;
    private final ChildRewardPersistenceMapper mapper;

    public ChildRewardPersistenceAdapter(ChildRewardRepository repository, ChildRewardPersistenceMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public ChildReward save(ChildReward childReward) {
        return mapper.toDomain(repository.save(mapper.toEntity(childReward)));
    }

    @Override
    public boolean existsByChildIdAndRewardId(Long childId, Long rewardId) {
        return repository.existsByChildIdAndRewardId(childId, rewardId);
    }
}
