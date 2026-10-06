package com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto;

import com.guardianesdelplaneta.domain.model.enums.ActivityType;

public record ActivityResponseDto(
        Long id,
        Long lessonId,
        ActivityType type,
        String title,
        String configurationJson,
        Integer xpReward,
        Integer orderIndex
) {
}
