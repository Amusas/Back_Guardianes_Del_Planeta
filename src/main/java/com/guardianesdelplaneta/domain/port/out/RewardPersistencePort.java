package com.guardianesdelplaneta.domain.port.out;

import com.guardianesdelplaneta.domain.model.Reward;
import java.util.List;

public interface RewardPersistencePort {
    List<Reward> findByRequiredLevelIdLessThanEqual(Long levelId);
}
