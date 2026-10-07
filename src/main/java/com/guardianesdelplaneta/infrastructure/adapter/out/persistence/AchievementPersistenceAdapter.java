package com.guardianesdelplaneta.infrastructure.adapter.out.persistence;

import com.guardianesdelplaneta.domain.model.Achievement;
import com.guardianesdelplaneta.domain.port.out.AchievementPersistencePort;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.mapper.AchievementPersistenceMapper;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.repository.AchievementRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class AchievementPersistenceAdapter implements AchievementPersistencePort {

    private final AchievementRepository achievementRepository;
    private final AchievementPersistenceMapper achievementPersistenceMapper;

    public AchievementPersistenceAdapter(AchievementRepository achievementRepository, AchievementPersistenceMapper achievementPersistenceMapper) {
        this.achievementRepository = achievementRepository;
        this.achievementPersistenceMapper = achievementPersistenceMapper;
    }

    @Override
    public List<Achievement> findAllActive() {
        return achievementRepository.findByIsActiveTrue().stream()
                .map(achievementPersistenceMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Achievement> findById(Long id) {
        return achievementRepository.findById(id).map(achievementPersistenceMapper::toDomain);
    }

    @Override
    public Optional<Achievement> findByCode(String code) {
        return achievementRepository.findByCode(code).map(achievementPersistenceMapper::toDomain);
    }
}
