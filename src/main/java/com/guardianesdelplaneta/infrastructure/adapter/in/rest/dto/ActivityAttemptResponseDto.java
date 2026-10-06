package com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto;

import java.time.LocalDateTime;

public record ActivityAttemptResponseDto(
        Long id,
        Long childId,
        Long activityId,
        Integer attemptNumber,
        String responseJson,
        Boolean isCorrect,
        Integer xpAwarded,
        LocalDateTime attemptedAt
) {
}
