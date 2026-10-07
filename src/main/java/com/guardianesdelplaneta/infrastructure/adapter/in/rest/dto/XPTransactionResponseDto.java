package com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto;

import com.guardianesdelplaneta.domain.model.enums.XPTransactionType;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public record XPTransactionResponseDto(
        @Schema(description = "Identificador único de la transacción de XP", example = "1")
        Long id,
        @Schema(description = "Identificador del perfil infantil", example = "10")
        Long childId,
        @Schema(description = "Cantidad de XP", example = "50")
        Integer amount,
        @Schema(description = "Tipo de transacción de XP", example = "EARNED")
        XPTransactionType type,
        @Schema(description = "Identificador de referencia (ej. lección o recompensa)", example = "5")
        Long referenceId,
        @Schema(description = "Descripción de la transacción", example = "XP ganada por completar lección")
        String description,
        @Schema(description = "Fecha y hora de creación", example = "2023-10-07T15:00:00")
        LocalDateTime createdAt
) {
}
