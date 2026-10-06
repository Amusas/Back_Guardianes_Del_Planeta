package com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO general estándar para todas las respuestas de error de la API.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ErrorResponseDto(
        int statusCode,
        String error,
        String message,
        String path,
        LocalDateTime timestamp,
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
