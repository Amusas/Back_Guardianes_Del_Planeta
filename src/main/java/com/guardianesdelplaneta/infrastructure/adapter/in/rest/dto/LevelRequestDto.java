package com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record LevelRequestDto(
        @NotNull(message = "El número de nivel es obligatorio")
        @Positive(message = "El número de nivel debe ser positivo")
        @Schema(description = "Número del nivel", example = "1")
        Integer levelNumber,

        @NotBlank(message = "El nombre del nivel es obligatorio")
        @Schema(description = "Nombre del nivel", example = "Principiante")
        String name,

        @NotNull(message = "La experiencia requerida es obligatoria")
        @Schema(description = "Experiencia (XP) requerida para alcanzar el nivel", example = "100")
        Integer requiredXp,

        @Schema(description = "Descripción del nivel", example = "Nivel inicial para nuevos guardianes.")
        String description,
        @Schema(description = "Clave visual asociada al nivel", example = "medalla_bronce")
        String visualKey
) {
}
