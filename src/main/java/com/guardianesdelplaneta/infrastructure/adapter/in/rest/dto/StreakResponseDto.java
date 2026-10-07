package com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record StreakResponseDto(
        @Schema(description = "Identificador único de la racha", example = "1")
        Long id,
        @Schema(description = "Identificador del perfil infantil", example = "10")
        Long childId,
        @Schema(description = "Fecha de la actividad", example = "2023-10-07")
        LocalDate activityDate,
        @Schema(description = "Días consecutivos de actividad", example = "5")
        Integer consecutiveDay,
        @Schema(description = "Fecha y hora de creación", example = "2023-10-07T15:00:00")
        LocalDateTime createdAt
) {
}
