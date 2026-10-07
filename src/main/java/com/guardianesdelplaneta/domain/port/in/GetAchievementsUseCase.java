package com.guardianesdelplaneta.domain.port.in;

import com.guardianesdelplaneta.domain.model.Achievement;
import com.guardianesdelplaneta.domain.model.ChildAchievement;
import java.util.List;

public interface GetAchievementsUseCase {
    List<Achievement> getAllActiveAchievements();
    List<Achievement> getUnlockedAchievements(Long userId);
    ChildAchievement getAchievementStatus(Long userId, Long achievementId);
}
