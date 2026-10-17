package com.guardianesdelplaneta.infrastructure.adapter.out.persistence.repository;

import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.entity.ActivityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ActivityRepository extends JpaRepository<ActivityEntity, Long> {

    List<ActivityEntity> findByLessonIdOrderByOrderIndexAsc(Long lessonId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM activities WHERE type = 'IDENTIFICATION'", nativeQuery = true)
    void deleteIdentificationActivities();
}
