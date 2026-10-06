package com.guardianesdelplaneta.infrastructure.adapter.out.persistence.repository;

import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.entity.ProgressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProgressRepository extends JpaRepository<ProgressEntity, Long> {

    List<ProgressEntity> findByChildId(Long childId);

    Optional<ProgressEntity> findByChildIdAndLessonId(Long childId, Long lessonId);

    boolean existsByChildIdAndLessonId(Long childId, Long lessonId);
}
