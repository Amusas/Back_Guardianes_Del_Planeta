package com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto;

import com.guardianesdelplaneta.domain.model.enums.ActivityType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(description = "DTO para la solicitud de creación o actualización de una actividad")
public record ActivityRequestDto(
        @Schema(description = "Identificador de la lección asociada", example = "10")
        @NotNull(message = "El identificador de la lección es obligatorio")
        Long lessonId,

        @Schema(description = "Tipo de actividad", example = "MULTIPLE_CHOICE")
        @NotNull(message = "El tipo de actividad es obligatorio")
        ActivityType type,

        @Schema(description = "Título de la actividad", example = "Identifica el animal")
        @NotBlank(message = "El título de la actividad es obligatorio")
        String title,

        @Schema(description = "Configuración de la actividad en formato JSON", example = "{\"options\":[\"Perro\",\"Gato\"],\"correct\":\"Perro\"}")
        @NotBlank(message = "La configuración JSON es obligatoria")
        String configurationJson,

        @Schema(description = "Puntos de experiencia de recompensa al completar la actividad", example = "100")
        Integer xpReward,

        @Schema(description = "Índice de orden de la actividad dentro de la lección", example = "1")
        @NotNull(message = "El índice de orden es obligatorio")
        Integer orderIndex
) {
}
