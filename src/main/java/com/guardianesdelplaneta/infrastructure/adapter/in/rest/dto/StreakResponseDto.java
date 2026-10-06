package com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record StreakResponseDto(
        Long id,
        Long childId,
        LocalDate activityDate,
        Integer consecutiveDay,
        LocalDateTime createdAt
) {
}
