package com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;

public record HealthResponseDto(
        @Schema(description = "Nombre de la aplicación", example = "Guardianes del Planeta API")
        String application,
        @Schema(description = "Estado de salud de la aplicación", example = "UP")
        String status,
        @Schema(description = "Indica si la base de datos está conectada", example = "true")
        boolean databaseConnected,
        @Schema(description = "Motor de base de datos utilizado", example = "PostgreSQL")
        String databaseEngine,
        @Schema(description = "Versión de la base de datos", example = "15.3")
        String databaseVersion,
        @Schema(description = "Mensaje adicional de estado", example = "Sistema operando normalmente")
        String message,
        @Schema(description = "Fecha y hora de la verificación", example = "2023-10-27T10:00:00")
        LocalDateTime timestamp
) {
}
