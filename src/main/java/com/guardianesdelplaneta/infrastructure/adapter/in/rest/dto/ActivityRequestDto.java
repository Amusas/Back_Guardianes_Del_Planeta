package com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto;

import com.guardianesdelplaneta.domain.model.enums.ActivityType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ActivityRequestDto(
        @NotNull(message = "El identificador de la lección es obligatorio")
        Long lessonId,

        @NotNull(message = "El tipo de actividad es obligatorio")
        ActivityType type,

        @NotBlank(message = "El título de la actividad es obligatorio")
        String title,

        @NotBlank(message = "La configuración JSON es obligatoria")
        String configurationJson,

        Integer xpReward,

        @NotNull(message = "El índice de orden es obligatorio")
        Integer orderIndex
) {
}
