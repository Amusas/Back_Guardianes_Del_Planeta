package com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record LessonResponseDto(
        @Schema(description = "Identificador único de la lección", example = "1")
        Long id,
        @Schema(description = "Identificador del ecosistema asociado", example = "1")
        Long ecosystemId,
        @Schema(description = "Título de la lección", example = "Introducción al Reciclaje")
        String title,
        @Schema(description = "Contenido de la lección", example = "En esta lección aprenderemos sobre...")
        String content,
        @Schema(description = "Orden de la lección", example = "1")
        Integer orderIndex,
        @Schema(description = "Experiencia (XP) otorgada", example = "50")
        Integer xpReward,
        @Schema(description = "Indica si la lección está activa", example = "true")
        Boolean isActive
) {
}
