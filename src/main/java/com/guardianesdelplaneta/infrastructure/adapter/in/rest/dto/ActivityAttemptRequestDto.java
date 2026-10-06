package com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ActivityAttemptRequestDto(
        @NotNull(message = "El identificador del perfil infantil es obligatorio")
        Long childId,

        @NotNull(message = "El identificador de la actividad es obligatorio")
        Long activityId,

        @NotNull(message = "El número de intento es obligatorio")
        Integer attemptNumber,

        @NotBlank(message = "La respuesta JSON es obligatoria")
        String responseJson,

        @NotNull(message = "El indicador de acierto es obligatorio")
        Boolean isCorrect,

        Integer xpAwarded
) {
}
