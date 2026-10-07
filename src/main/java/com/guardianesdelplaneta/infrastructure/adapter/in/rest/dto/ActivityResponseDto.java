package com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto;

import com.guardianesdelplaneta.domain.model.enums.ActivityType;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO de respuesta con la información de una actividad")
public record ActivityResponseDto(
        @Schema(description = "Identificador único de la actividad", example = "1")
        Long id,
        
        @Schema(description = "Identificador de la lección asociada", example = "10")
        Long lessonId,
        
        @Schema(description = "Tipo de actividad", example = "MULTIPLE_CHOICE")
        ActivityType type,
        
        @Schema(description = "Título de la actividad", example = "Identifica el animal")
        String title,
        
        @Schema(description = "Configuración de la actividad en formato JSON", example = "{\"options\":[\"Perro\",\"Gato\"],\"correct\":\"Perro\"}")
        String configurationJson,
        
        @Schema(description = "Puntos de experiencia de recompensa al completar la actividad", example = "100")
        Integer xpReward,
        
        @Schema(description = "Índice de orden de la actividad dentro de la lección", example = "1")
        Integer orderIndex
) {
}
