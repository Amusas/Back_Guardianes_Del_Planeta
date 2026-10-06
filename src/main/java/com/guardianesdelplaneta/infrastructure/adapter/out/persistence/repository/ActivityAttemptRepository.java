package com.guardianesdelplaneta.infrastructure.adapter.out.persistence.repository;

import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.entity.ActivityAttemptEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityAttemptRepository extends JpaRepository<ActivityAttemptEntity, Long> {

    List<ActivityAttemptEntity> findByChildIdAndActivityId(Long childId, Long activityId);

    List<ActivityAttemptEntity> findByChildId(Long childId);

    long countByChildIdAndActivityId(Long childId, Long activityId);
}
