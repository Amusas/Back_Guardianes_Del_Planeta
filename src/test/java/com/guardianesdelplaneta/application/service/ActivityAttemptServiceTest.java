package com.guardianesdelplaneta.application.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.guardianesdelplaneta.domain.model.Activity;
import com.guardianesdelplaneta.domain.model.ChildProfile;
import com.guardianesdelplaneta.domain.model.enums.ActivityType;
import com.guardianesdelplaneta.domain.port.out.*;
import com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto.ActivityAttemptRequestDto;
import com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto.ActivityAttemptResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
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
        java.util.List<com.guardianesdelplaneta.domain.model.Achievement> emptyAchievements = java.util.Collections.emptyList();
        org.mockito.Mockito.lenient().when(achievementEvaluator.evaluateAchievements(any(), anyInt(), anyInt(), anyInt(), anyInt())).thenReturn(emptyAchievements);
    }

    @Test
    void testSubmitCorrectAttempt() throws Exception {
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
}

