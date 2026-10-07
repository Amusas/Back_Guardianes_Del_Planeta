package com.guardianesdelplaneta.infrastructure.adapter.out.persistence;

import com.guardianesdelplaneta.domain.model.Reward;
import com.guardianesdelplaneta.domain.port.out.RewardPersistencePort;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.mapper.RewardPersistenceMapper;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.repository.RewardRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RewardPersistenceAdapter implements RewardPersistencePort {
    private final RewardRepository repository;
    private final RewardPersistenceMapper mapper;

    public RewardPersistenceAdapter(RewardRepository repository, RewardPersistenceMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<Reward> findByRequiredLevelIdLessThanEqual(Long levelId) {
        return repository.findByRequiredLevelIdLessThanEqual(levelId).stream().map(mapper::toDomain).toList();
    }
}


