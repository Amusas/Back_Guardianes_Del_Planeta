package com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Schema(description = "DTO de respuesta con la información del perfil infantil")
public record ChildProfileResponseDto(
        @Schema(description = "Identificador único del perfil", example = "1")
        Long id,
        
        @Schema(description = "Identificador del usuario asociado", example = "1")
        Long userId,
        
        @Schema(description = "Nombre del niño", example = "Juanito")
        String name,
        
        @Schema(description = "Fecha de nacimiento", example = "2015-05-10")
        LocalDate dateOfBirth,
        
        @Schema(description = "Edad calculada en años", example = "8")
        Integer age,
        
        @Schema(description = "Puntos de experiencia totales acumulados", example = "5000")
        Integer totalXp,
        
        @Schema(description = "Identificador del nivel actual", example = "5")
        Long levelId,
        
        @Schema(description = "Racha actual de días consecutivos de actividad", example = "3")
        Integer streakCount,
        
        @Schema(description = "Fecha y hora de creación del perfil", example = "2023-01-01T10:00:00")
        LocalDateTime createdAt,
        
        @Schema(description = "Fecha y hora de la última actualización del perfil", example = "2023-10-01T12:00:00")
        LocalDateTime updatedAt
) {
}
