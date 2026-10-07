package com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto;

import java.time.LocalDateTime;
import java.util.List;

public record ActivityAttemptResponseDto(
        Boolean isCorrect,
        Integer attemptNumber,
        Integer xpAwarded,
        Integer totalXp,
        Long currentLevel,
        Boolean levelUp,
        Long previousLevel,
        Boolean activityCompleted,
        Boolean lessonCompleted,
        Boolean ecosystemCompleted,
        Integer streakCount,
        List<String> xpEvents
) {
}
