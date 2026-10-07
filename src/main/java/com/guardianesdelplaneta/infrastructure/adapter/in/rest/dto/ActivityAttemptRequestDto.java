package com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto;

import com.fasterxml.jackson.databind.JsonNode;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

@Schema(description = "DTO para la solicitud de un intento de actividad")
public record ActivityAttemptRequestDto(
        @Schema(description = "Respuesta del usuario en formato JSON", example = "{\"answer\":\"Opción A\"}")
        @NotNull(message = "La respuesta es obligatoria")
        JsonNode response
) {
}
