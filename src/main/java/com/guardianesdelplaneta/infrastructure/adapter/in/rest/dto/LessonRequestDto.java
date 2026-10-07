package com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LessonRequestDto(
        @NotNull(message = "El identificador del ecosistema es obligatorio")
        @Schema(description = "Identificador del ecosistema asociado a la lección", example = "1")
        Long ecosystemId,

        @NotBlank(message = "El título de la lección es obligatorio")
        @Schema(description = "Título de la lección", example = "Introducción al Reciclaje")
        String title,

        @Schema(description = "Contenido de la lección", example = "En esta lección aprenderemos sobre...")
        String content,

        @NotNull(message = "El índice de orden es obligatorio")
        @Schema(description = "Orden de la lección dentro del ecosistema", example = "1")
        Integer orderIndex,

        @Schema(description = "Cantidad de experiencia (XP) otorgada al completar", example = "50")
        Integer xpReward,
        @Schema(description = "Indica si la lección está activa", example = "true")
        Boolean isActive
) {
}
