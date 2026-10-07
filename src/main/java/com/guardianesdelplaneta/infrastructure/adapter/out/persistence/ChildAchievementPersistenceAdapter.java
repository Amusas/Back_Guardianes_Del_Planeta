package com.guardianesdelplaneta.infrastructure.adapter.out.persistence;

import com.guardianesdelplaneta.domain.model.ChildAchievement;
import com.guardianesdelplaneta.domain.port.out.ChildAchievementPersistencePort;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.entity.ChildAchievementEntity;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.mapper.ChildAchievementPersistenceMapper;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.repository.ChildAchievementRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ChildAchievementPersistenceAdapter implements ChildAchievementPersistencePort {

    private final ChildAchievementRepository childAchievementRepository;
    private final ChildAchievementPersistenceMapper childAchievementPersistenceMapper;

    public ChildAchievementPersistenceAdapter(ChildAchievementRepository childAchievementRepository, ChildAchievementPersistenceMapper childAchievementPersistenceMapper) {
        this.childAchievementRepository = childAchievementRepository;
        this.childAchievementPersistenceMapper = childAchievementPersistenceMapper;
    }

    @Override
    public List<ChildAchievement> findByChildId(Long childId) {
        return childAchievementRepository.findByChildId(childId).stream()
                .map(childAchievementPersistenceMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ChildAchievement> findByChildIdAndAchievementId(Long childId, Long achievementId) {
        return childAchievementRepository.findByChildIdAndAchievementId(childId, achievementId)
                .map(childAchievementPersistenceMapper::toDomain);
    }

    @Override
    public boolean existsByChildIdAndAchievementId(Long childId, Long achievementId) {
        return childAchievementRepository.existsByChildIdAndAchievementId(childId, achievementId);
    }

    @Override
    public ChildAchievement save(ChildAchievement childAchievement) {
        ChildAchievementEntity entity = childAchievementPersistenceMapper.toEntity(childAchievement);
        return childAchievementPersistenceMapper.toDomain(childAchievementRepository.save(entity));
    }
}
