package com.guardianesdelplaneta.infrastructure.adapter.out.persistence.repository;

import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.entity.RewardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RewardRepository extends JpaRepository<RewardEntity, Long> {

    Optional<RewardEntity> findByVisualKey(String visualKey);

    List<RewardEntity> findByIsActiveTrue();
    List<RewardEntity> findByRequiredLevelIdLessThanEqual(Long levelId);
}

