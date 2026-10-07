package com.guardianesdelplaneta.domain.port.out;

import com.guardianesdelplaneta.domain.model.ActivityAttempt;

public interface ActivityAttemptPersistencePort {
    ActivityAttempt save(ActivityAttempt attempt);
    long countByChildIdAndActivityId(Long childId, Long activityId);
}
