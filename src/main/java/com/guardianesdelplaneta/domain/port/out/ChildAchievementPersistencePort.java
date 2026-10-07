package com.guardianesdelplaneta.domain.port.out;

import com.guardianesdelplaneta.domain.model.ChildAchievement;
import java.util.List;
import java.util.Optional;

public interface ChildAchievementPersistencePort {
    List<ChildAchievement> findByChildId(Long childId);
    Optional<ChildAchievement> findByChildIdAndAchievementId(Long childId, Long achievementId);
    boolean existsByChildIdAndAchievementId(Long childId, Long achievementId);
    ChildAchievement save(ChildAchievement childAchievement);
}
