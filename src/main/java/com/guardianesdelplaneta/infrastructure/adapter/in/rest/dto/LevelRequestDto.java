package com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record LevelRequestDto(
        @NotNull(message = "El número de nivel es obligatorio")
        @Positive(message = "El número de nivel debe ser positivo")
        Integer levelNumber,

        @NotBlank(message = "El nombre del nivel es obligatorio")
        String name,

        @NotNull(message = "La experiencia requerida es obligatoria")
        Integer requiredXp,

        String description,
        String visualKey
) {
}
