package com.guardianesdelplaneta.application.service;

import com.guardianesdelplaneta.domain.model.Achievement;
import com.guardianesdelplaneta.domain.model.ChildAchievement;
import com.guardianesdelplaneta.domain.model.ChildProfile;
import com.guardianesdelplaneta.domain.port.out.AchievementPersistencePort;
import com.guardianesdelplaneta.domain.port.out.ChildAchievementPersistencePort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AchievementEvaluator {
    private static final Logger logger = LoggerFactory.getLogger(AchievementEvaluator.class);

    private final AchievementPersistencePort achievementPort;
    private final ChildAchievementPersistencePort childAchievementPort;

    public AchievementEvaluator(AchievementPersistencePort achievementPort, ChildAchievementPersistencePort childAchievementPort) {
        this.achievementPort = achievementPort;
        this.childAchievementPort = childAchievementPort;
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public List<Achievement> evaluateAchievements(ChildProfile child, int activitiesCompleted, int lessonsCompleted, int ecosystemsCompleted, int specialChallengesCompleted) {
        List<Achievement> unlockedAchievements = new ArrayList<>();
        List<Achievement> activeAchievements = achievementPort.findAllActive();

        for (Achievement achievement : activeAchievements) {
            if (childAchievementPort.existsByChildIdAndAchievementId(child.getId(), achievement.getId())) {
                continue;
            }

            boolean conditionMet = false;
            Integer condVal = achievement.getConditionValue() != null ? achievement.getConditionValue() : 1;

            switch (achievement.getConditionType()) {
                case FIRST_ACTIVITY:
                case ACTIVITY_COUNT:
                    if (activitiesCompleted >= condVal) conditionMet = true;
                    break;
                case FIRST_LESSON:
                case LESSON_COUNT:
                    if (lessonsCompleted >= condVal) conditionMet = true;
                    break;
                case ECOSYSTEM_COUNT:
                case ECOSYSTEM_COMPLETED:
                    if (ecosystemsCompleted >= condVal) conditionMet = true;
                    break;
                case XP_REACHED:
                    if (child.getTotalXp() >= condVal) conditionMet = true;
                    break;
                case LEVEL_REACHED:
                    if (child.getLevelId() != null && child.getLevelId() >= condVal) conditionMet = true; 
                    break;
                case STREAK_REACHED:
                    if (child.getStreakCount() >= condVal) conditionMet = true;
                    break;
                case SPECIAL_CHALLENGE_COMPLETED:
                    if (specialChallengesCompleted >= condVal) conditionMet = true;
                    break;
                default:
                    logger.warn("Tipo de condicion no manejado: {}", achievement.getConditionType());
            }

            if (conditionMet) {
                logger.info("Logro desbloqueado: {} para nino {}", achievement.getTitle(), child.getId());
                ChildAchievement ca = new ChildAchievement(null, child.getId(), achievement.getId(), LocalDateTime.now());
                childAchievementPort.save(ca);
                unlockedAchievements.add(achievement);
            }
        }
        return unlockedAchievements;
    }
}
