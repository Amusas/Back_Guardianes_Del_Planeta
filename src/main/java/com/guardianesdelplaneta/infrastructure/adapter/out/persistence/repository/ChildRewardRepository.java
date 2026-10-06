package com.guardianesdelplaneta.infrastructure.adapter.out.persistence.repository;

import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.entity.ChildRewardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChildRewardRepository extends JpaRepository<ChildRewardEntity, Long> {

    List<ChildRewardEntity> findByChildId(Long childId);

    List<ChildRewardEntity> findByChildIdAndIsEquippedTrue(Long childId);

    Optional<ChildRewardEntity> findByChildIdAndRewardId(Long childId, Long rewardId);

    boolean existsByChildIdAndRewardId(Long childId, Long rewardId);
}
