package com.guardianesdelplaneta.infrastructure.adapter.out.persistence.repository;

import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.entity.ChildAchievementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChildAchievementRepository extends JpaRepository<ChildAchievementEntity, Long> {

    List<ChildAchievementEntity> findByChildId(Long childId);

    Optional<ChildAchievementEntity> findByChildIdAndAchievementId(Long childId, Long achievementId);

    boolean existsByChildIdAndAchievementId(Long childId, Long achievementId);
}
