package com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Progreso del niño en un ecosistema específico")
public record EcosystemProgressDto(
        @Schema(description = "Identificador del ecosistema", example = "1")
        Long ecosystemId,

        @Schema(description = "Código del ecosistema", example = "BOSQUE_ANDINO")
        String code,

        @Schema(description = "Nombre del ecosistema", example = "Bosque Andino")
        String name,

        @Schema(description = "Cantidad de lecciones completadas", example = "2")
        Integer completedLessons,

        @Schema(description = "Cantidad total de lecciones en el ecosistema", example = "5")
        Integer totalLessons,

        @Schema(description = "Porcentaje completado del ecosistema (0 a 100)", example = "40.0")
        Double progressPercentage
) {
}
