package com.guardianesdelplaneta.infrastructure.adapter.out.persistence.repository;

import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.entity.StreakEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface StreakRepository extends JpaRepository<StreakEntity, Long> {

    Optional<StreakEntity> findByChildIdAndActivityDate(Long childId, LocalDate activityDate);
    java.util.List<StreakEntity> findByChildId(Long childId);

    List<StreakEntity> findByChildIdOrderByActivityDateDesc(Long childId);

    boolean existsByChildIdAndActivityDate(Long childId, LocalDate activityDate);
}

