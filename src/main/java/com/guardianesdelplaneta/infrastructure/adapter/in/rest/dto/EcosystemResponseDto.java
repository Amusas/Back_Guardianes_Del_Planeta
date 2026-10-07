package com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record EcosystemResponseDto(
        @Schema(description = "Identificador único del ecosistema", example = "1")
        Long id,
        @Schema(description = "Código único del ecosistema", example = "BOSQUE_01")
        String code,
        @Schema(description = "Nombre del ecosistema", example = "Bosque Mágico")
        String name,
        @Schema(description = "Descripción del ecosistema", example = "Un bosque lleno de maravillas naturales.")
        String description,
        @Schema(description = "Identificador del nivel requerido", example = "2")
        Long requiredLevelId,
        @Schema(description = "Clave visual de la mascota del ecosistema", example = "oso_anteojos")
        String mascotKey,
        @Schema(description = "Indica si el ecosistema está activo", example = "true")
        Boolean isActive
) {
}
