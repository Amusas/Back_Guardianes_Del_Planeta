package com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record ChildProfileResponseDto(
        Long id,
        Long userId,
        String name,
        LocalDate dateOfBirth,
        Integer age,
        Integer totalXp,
        Long levelId,
        Integer streakCount,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
