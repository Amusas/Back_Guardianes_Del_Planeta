package com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto;

public record LessonResponseDto(
        Long id,
        Long ecosystemId,
        String title,
        String content,
        Integer orderIndex,
        Integer xpReward,
        Boolean isActive
) {
}
