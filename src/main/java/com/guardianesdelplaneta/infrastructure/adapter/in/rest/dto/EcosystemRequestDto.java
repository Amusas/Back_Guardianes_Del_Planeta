package com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto;

import jakarta.validation.constraints.NotBlank;

public record EcosystemRequestDto(
        @NotBlank(message = "El código del ecosistema es obligatorio")
        String code,

        @NotBlank(message = "El nombre del ecosistema es obligatorio")
        String name,

        String description,
        Long requiredLevelId,
        String mascotKey,
        Boolean isActive
) {
}
