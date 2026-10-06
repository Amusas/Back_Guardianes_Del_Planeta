package com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto;

import java.time.LocalDateTime;

public record ChildAchievementResponseDto(
        Long id,
        Long childId,
        Long achievementId,
        LocalDateTime unlockedAt
) {
}
