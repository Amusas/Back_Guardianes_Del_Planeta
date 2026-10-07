package com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO general estándar para todas las respuestas de error de la API.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ErrorResponseDto(
        @Schema(description = "Código de estado HTTP", example = "404")
        int statusCode,
        @Schema(description = "Tipo de error HTTP", example = "Not Found")
        String error,
        @Schema(description = "Mensaje detallado del error", example = "Recurso no encontrado")
        String message,
        @Schema(description = "Ruta de la API donde ocurrió el error", example = "/api/v1/ecosistemas/1")
        String path,
        @Schema(description = "Fecha y hora en que ocurrió el error", example = "2023-10-27T10:00:00")
        LocalDateTime timestamp,
        @Schema(description = "Lista de detalles adicionales del error", example = "[\"El ID debe ser mayor a 0\"]")
        List<String> details
) {

    public ErrorResponseDto(int statusCode, String error, String message, String path, LocalDateTime timestamp) {
        this(statusCode, error, message, path, timestamp, null);
    }

    public static ErrorResponseDto of(int statusCode, String error, String message, String path) {
        return new ErrorResponseDto(statusCode, error, message, path, LocalDateTime.now(), null);
    }

    public static ErrorResponseDto of(int statusCode, String error, String message, String path, List<String> details) {
        return new ErrorResponseDto(statusCode, error, message, path, LocalDateTime.now(), details);
    }
}
