package com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record LevelResponseDto(
        @Schema(description = "Identificador único del nivel", example = "1")
        Long id,
        @Schema(description = "Número del nivel", example = "1")
        Integer levelNumber,
        @Schema(description = "Nombre del nivel", example = "Principiante")
        String name,
        @Schema(description = "Puntos de experiencia requeridos", example = "100")
        Integer requiredXp,
        @Schema(description = "Descripción del nivel", example = "Nivel inicial para nuevos guardianes")
        String description,
        @Schema(description = "Clave visual del nivel", example = "level_1_icon")
        String visualKey
) {
}
