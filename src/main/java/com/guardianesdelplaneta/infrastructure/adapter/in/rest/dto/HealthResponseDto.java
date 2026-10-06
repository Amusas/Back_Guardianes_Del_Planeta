package com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto;

import java.time.LocalDateTime;

public record HealthResponseDto(
        String application,
        String status,
        boolean databaseConnected,
        String databaseEngine,
        String databaseVersion,
        String message,
        LocalDateTime timestamp
) {
}
