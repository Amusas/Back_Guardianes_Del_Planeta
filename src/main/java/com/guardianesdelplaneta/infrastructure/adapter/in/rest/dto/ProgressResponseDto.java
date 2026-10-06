package com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto;

import com.guardianesdelplaneta.domain.model.enums.ProgressStatus;

import java.time.LocalDateTime;

public record ProgressResponseDto(
        Long id,
        Long childId,
        Long lessonId,
        ProgressStatus status,
        Integer progressPercentage,
        LocalDateTime completedAt,
        LocalDateTime updatedAt
) {
}
