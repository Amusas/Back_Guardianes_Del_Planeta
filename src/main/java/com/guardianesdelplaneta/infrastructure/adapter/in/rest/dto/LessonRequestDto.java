package com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LessonRequestDto(
        @NotNull(message = "El identificador del ecosistema es obligatorio")
        Long ecosystemId,

        @NotBlank(message = "El título de la lección es obligatorio")
        String title,

        String content,

        @NotNull(message = "El índice de orden es obligatorio")
        Integer orderIndex,

        Integer xpReward,
        Boolean isActive
) {
}
