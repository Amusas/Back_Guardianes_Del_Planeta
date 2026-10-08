package com.guardianesdelplaneta.application.service;

import com.guardianesdelplaneta.domain.model.Achievement;
import com.guardianesdelplaneta.domain.model.ChildProfile;
import com.guardianesdelplaneta.domain.model.enums.AchievementConditionType;
import com.guardianesdelplaneta.domain.port.out.AchievementPersistencePort;
import com.guardianesdelplaneta.domain.port.out.ChildAchievementPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AchievementEvaluatorTest {

    @Mock private AchievementPersistencePort achievementPort;
    @Mock private ChildAchievementPersistencePort childAchievementPort;

    private AchievementEvaluator evaluator;

    @BeforeEach
    void setUp() {
        evaluator = new AchievementEvaluator(achievementPort, childAchievementPort);
    }

    private void runTestForCondition(AchievementConditionType type, int condVal, int activities, int lessons, int ecosystems, int specials, int xp, long level, int streak, boolean shouldUnlock) {
        Achievement a = new Achievement();
        a.setId(1L);
        a.setConditionType(type);
        a.setConditionValue(condVal);

        ChildProfile c = new ChildProfile();
        c.setId(10L);
        c.setTotalXp(xp);
        c.setLevelId(level);
        c.setStreakCount(streak);

        when(achievementPort.findAllActive()).thenReturn(Collections.singletonList(a));
        when(childAchievementPort.existsByChildIdAndAchievementId(10L, 1L)).thenReturn(false);

        List<Achievement> unlocked = evaluator.evaluateAchievements(c, activities, lessons, ecosystems, specials);

        if (shouldUnlock) {
            assertEquals(1, unlocked.size());
            verify(childAchievementPort, times(1)).save(any());
        } else {
            assertEquals(0, unlocked.size());
            verify(childAchievementPort, never()).save(any());
        }
    }

    @Test
    void testFirstActivity() {
        runTestForCondition(AchievementConditionType.FIRST_ACTIVITY, 1, 1, 0, 0, 0, 0, 1L, 0, true);
    }
    
    @Test
    void testActivityCount() {
        runTestForCondition(AchievementConditionType.ACTIVITY_COUNT, 10, 10, 0, 0, 0, 0, 1L, 0, true);
    }

    @Test
    void testActivityCount_NotMet() {
        runTestForCondition(AchievementConditionType.ACTIVITY_COUNT, 10, 9, 0, 0, 0, 0, 1L, 0, false);
    }

    @Test
    void testFirstLesson() {
        runTestForCondition(AchievementConditionType.FIRST_LESSON, 1, 0, 1, 0, 0, 0, 1L, 0, true);
    }

    @Test
    void testLessonCount() {
        runTestForCondition(AchievementConditionType.LESSON_COUNT, 5, 0, 5, 0, 0, 0, 1L, 0, true);
    }

    @Test
    void testEcosystemCount() {
        runTestForCondition(AchievementConditionType.ECOSYSTEM_COUNT, 3, 0, 0, 3, 0, 0, 1L, 0, true);
    }

    @Test
    void testXpReached() {
        runTestForCondition(AchievementConditionType.XP_REACHED, 100, 0, 0, 0, 0, 100, 1L, 0, true);
    }

    @Test
    void testLevelReached() {
        runTestForCondition(AchievementConditionType.LEVEL_REACHED, 5, 0, 0, 0, 0, 0, 5L, 0, true);
    }

    @Test
    void testStreakReached() {
        runTestForCondition(AchievementConditionType.STREAK_REACHED, 7, 0, 0, 0, 0, 0, 1L, 7, true);
    }

    @Test
    void testSpecialChallenge() {
        runTestForCondition(AchievementConditionType.SPECIAL_CHALLENGE_COMPLETED, 1, 0, 0, 0, 1, 0, 1L, 0, true);
    }
}
