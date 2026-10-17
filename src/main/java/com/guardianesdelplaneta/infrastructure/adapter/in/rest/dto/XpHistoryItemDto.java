package com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;

@Schema(description = "Registro del historial de puntos de experiencia")
public record XpHistoryItemDto(
        @Schema(description = "Identificador de la transacción", example = "10")
        Long id,

        @Schema(description = "Cantidad de XP otorgada", example = "50")
        Integer amount,

        @Schema(description = "Tipo de transacción", example = "ACTIVITY_COMPLETED")
        String type,

        @Schema(description = "Descripción de la causa de otorgamiento", example = "Completó la actividad Clasificación de Residuos")
        String description,

        @Schema(description = "Fecha y hora del registro", example = "2026-10-15T10:30:00")
        LocalDateTime createdAt
) {
}
