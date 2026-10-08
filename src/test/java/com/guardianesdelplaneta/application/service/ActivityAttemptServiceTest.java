package com.guardianesdelplaneta.application.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.guardianesdelplaneta.domain.model.Activity;
import com.guardianesdelplaneta.domain.model.ChildProfile;
import com.guardianesdelplaneta.domain.model.Level;
import com.guardianesdelplaneta.domain.model.Reward;
import com.guardianesdelplaneta.domain.model.Streak;
import com.guardianesdelplaneta.domain.model.enums.ActivityType;
import com.guardianesdelplaneta.domain.model.enums.XPTransactionType;
import com.guardianesdelplaneta.domain.port.out.*;
import com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto.ActivityAttemptRequestDto;
import com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto.ActivityAttemptResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ActivityAttemptServiceTest {

    @Mock private ChildProfilePersistencePort childProfilePort;
    @Mock private ActivityPersistencePort activityPort;
    @Mock private ActivityAttemptPersistencePort attemptPort;
    @Mock private XPTransactionPersistencePort xpTransactionPort;
    @Mock private LevelPersistencePort levelPort;
    @Mock private ProgressPersistencePort progressPort;
    @Mock private StreakPersistencePort streakPort;
    @Mock private RewardPersistencePort rewardPort;
    @Mock private ChildRewardPersistencePort childRewardPort;
    @Mock private LessonPersistencePort lessonPort;
    @Mock private AchievementEvaluator achievementEvaluator;
    @Mock private com.guardianesdelplaneta.infrastructure.adapter.in.rest.mapper.AchievementRestMapper achievementRestMapper;
    
    private ActivityEvaluator evaluator;
    private ActivityAttemptService service;
    private ObjectMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new ObjectMapper();
        evaluator = new ActivityEvaluator(mapper);
        service = new ActivityAttemptService(
                childProfilePort, activityPort, attemptPort, xpTransactionPort,
                levelPort, progressPort, streakPort, rewardPort, childRewardPort, lessonPort, evaluator, achievementEvaluator, achievementRestMapper
        );
        org.mockito.Mockito.lenient().when(achievementEvaluator.evaluateAchievements(any(), anyInt(), anyInt(), anyInt(), anyInt())).thenReturn(Collections.emptyList());
    }

    @Test
    void testSubmitCorrectAttempt_10XPCorrect_20XPBonus_25XPStreak() throws Exception {
        ChildProfile child = new ChildProfile();
        child.setId(1L);
        child.setTotalXp(0);
        child.setLevelId(1L);
        
        Activity activity = new Activity();
        activity.setId(10L);
        activity.setType(ActivityType.MULTIPLE_CHOICE);
        activity.setConfigurationJson("{\"correctAnswer\":\"A\"}");

        when(childProfilePort.findByUserId(100L)).thenReturn(Optional.of(child));
        when(activityPort.findById(10L)).thenReturn(Optional.of(activity));
        when(attemptPort.countByChildIdAndActivityId(1L, 10L)).thenReturn(0L);
        when(streakPort.findByChildIdAndActivityDate(anyLong(), any())).thenReturn(Optional.empty());

        ObjectNode responseJson = mapper.createObjectNode();
        responseJson.put("answer", "A");
        ActivityAttemptRequestDto request = new ActivityAttemptRequestDto(responseJson);

        ActivityAttemptResponseDto response = service.submitAttempt(100L, 10L, request);

        assertTrue(response.isCorrect());
        assertEquals(55, response.totalXp()); // 10 correct + 20 bonus + 25 daily
        verify(attemptPort, times(1)).save(any());
        verify(xpTransactionPort, times(3)).save(any());
        verify(childProfilePort, times(1)).save(any());
    }

    @Test
    void testSubmitIdempotency_repeatedRequestsDontDuplicateXP() throws Exception {
        ChildProfile child = new ChildProfile();
        child.setId(1L);
        child.setTotalXp(55);
        child.setLevelId(1L);
        
        Activity activity = new Activity();
        activity.setId(10L);
        activity.setType(ActivityType.MULTIPLE_CHOICE);
        activity.setConfigurationJson("{\"correctAnswer\":\"A\"}");

        when(childProfilePort.findByUserId(100L)).thenReturn(Optional.of(child));
        when(activityPort.findById(10L)).thenReturn(Optional.of(activity));
        when(attemptPort.countByChildIdAndActivityId(1L, 10L)).thenReturn(1L); // Second attempt
        when(streakPort.findByChildIdAndActivityDate(anyLong(), any())).thenReturn(Optional.of(new Streak()));

        when(xpTransactionPort.existsByChildIdAndTypeAndReferenceId(1L, XPTransactionType.ACTIVITY, 10L)).thenReturn(true);

        ObjectNode responseJson = mapper.createObjectNode();
        responseJson.put("answer", "A");
        ActivityAttemptRequestDto request = new ActivityAttemptRequestDto(responseJson);

        ActivityAttemptResponseDto response = service.submitAttempt(100L, 10L, request);

        assertTrue(response.isCorrect());
        assertEquals(55, response.totalXp()); // XP should not increase
        verify(xpTransactionPort, never()).save(any());
    }

    @Test
    void testLevelUpLogic() throws Exception {
        ChildProfile child = new ChildProfile();
        child.setId(1L);
        child.setTotalXp(90);
        child.setLevelId(1L);

        Activity activity = new Activity();
        activity.setId(10L);
        activity.setType(ActivityType.MULTIPLE_CHOICE);
        activity.setConfigurationJson("{\"correctAnswer\":\"A\"}");

        when(childProfilePort.findByUserId(100L)).thenReturn(Optional.of(child));
        when(activityPort.findById(10L)).thenReturn(Optional.of(activity));
        
        Level curLevel = new Level();
        curLevel.setLevelNumber(1);
        when(levelPort.findById(1L)).thenReturn(Optional.of(curLevel));

        Level nextLevel = new Level();
        nextLevel.setId(2L);
        nextLevel.setLevelNumber(2);
        nextLevel.setRequiredXp(100);
        when(levelPort.findByLevelNumber(2)).thenReturn(Optional.of(nextLevel));
        when(levelPort.findByLevelNumber(3)).thenReturn(Optional.empty());
        
        Reward reward = new Reward();
        reward.setId(5L);
        when(rewardPort.findByRequiredLevelIdLessThanEqual(2L)).thenReturn(Collections.singletonList(reward));
        when(childRewardPort.existsByChildIdAndRewardId(1L, 5L)).thenReturn(false);

        ObjectNode responseJson = mapper.createObjectNode();
        responseJson.put("answer", "A");
        
        ActivityAttemptResponseDto response = service.submitAttempt(100L, 10L, new ActivityAttemptRequestDto(responseJson));

        assertTrue(response.levelUp());
        assertEquals(2L, response.currentLevel());
        verify(childRewardPort, times(1)).save(any());
    }

    @Test
    @Disabled("Not implemented yet")
    void testLessonCompletion_50XP() {
    }

    @Test
    @Disabled("Not implemented yet")
    void testEcosystemCompletion_150XP() {
    }

    @Test
    @Disabled("Not implemented yet")
    void testSpecialCompletion_75XP() {
    }

    @Test
    void testSubmitIncorrectAttempt() throws Exception {
        ChildProfile child = new ChildProfile();
        child.setId(1L);
        child.setTotalXp(0);
        
        Activity activity = new Activity();
        activity.setId(10L);
        activity.setType(ActivityType.MULTIPLE_CHOICE);
        activity.setConfigurationJson("{\"correctAnswer\":\"B\"}");

        when(childProfilePort.findByUserId(100L)).thenReturn(Optional.of(child));
        when(activityPort.findById(10L)).thenReturn(Optional.of(activity));
        when(streakPort.findByChildIdAndActivityDate(anyLong(), any())).thenReturn(Optional.of(new Streak()));

        ObjectNode responseJson = mapper.createObjectNode();
        responseJson.put("answer", "A");
        ActivityAttemptRequestDto request = new ActivityAttemptRequestDto(responseJson);

        ActivityAttemptResponseDto response = service.submitAttempt(100L, 10L, request);

        assertFalse(response.isCorrect());
        assertEquals(0, response.totalXp());
    }
}
