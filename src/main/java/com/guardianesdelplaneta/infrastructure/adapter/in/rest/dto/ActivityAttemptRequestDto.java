package com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.validation.constraints.NotNull;

public record ActivityAttemptRequestDto(
        @NotNull(message = "La respuesta es obligatoria")
        JsonNode response
) {
}
