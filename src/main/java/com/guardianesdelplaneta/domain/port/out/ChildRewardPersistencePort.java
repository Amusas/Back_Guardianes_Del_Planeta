package com.guardianesdelplaneta.domain.port.out;

import com.guardianesdelplaneta.domain.model.ChildReward;

public interface ChildRewardPersistencePort {
    ChildReward save(ChildReward childReward);
    boolean existsByChildIdAndRewardId(Long childId, Long rewardId);
}
