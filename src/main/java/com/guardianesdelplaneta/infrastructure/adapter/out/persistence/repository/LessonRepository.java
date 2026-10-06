package com.guardianesdelplaneta.infrastructure.adapter.out.persistence.repository;

import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.entity.LessonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessonRepository extends JpaRepository<LessonEntity, Long> {

    List<LessonEntity> findByEcosystemIdOrderByOrderIndexAsc(Long ecosystemId);

    List<LessonEntity> findByEcosystemIdAndIsActiveTrueOrderByOrderIndexAsc(Long ecosystemId);
}
