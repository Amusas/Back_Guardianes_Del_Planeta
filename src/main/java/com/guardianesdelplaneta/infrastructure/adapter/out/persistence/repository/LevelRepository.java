package com.guardianesdelplaneta.infrastructure.adapter.out.persistence.repository;

import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.entity.LevelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LevelRepository extends JpaRepository<LevelEntity, Long> {

    Optional<LevelEntity> findByLevelNumber(Integer levelNumber);

    boolean existsByLevelNumber(Integer levelNumber);
}
