package com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "DTO de respuesta tras un intento de actividad")
public record ActivityAttemptResponseDto(
        @Schema(description = "Indica si la respuesta fue correcta", example = "true")
        Boolean isCorrect,
        
        @Schema(description = "Número de intento actual", example = "1")
        Integer attemptNumber,
        
        @Schema(description = "Puntos de experiencia obtenidos en el intento", example = "50")
        Integer xpAwarded,
        
        @Schema(description = "Puntos de experiencia totales acumulados", example = "1500")
        Integer totalXp,
        
        @Schema(description = "Nivel actual del usuario", example = "5")
        Long currentLevel,
        
        @Schema(description = "Indica si el usuario subió de nivel", example = "false")
        Boolean levelUp,
        
        @Schema(description = "Nivel anterior del usuario antes del intento", example = "5")
        Long previousLevel,
        
        @Schema(description = "Indica si la actividad fue completada exitosamente", example = "true")
        Boolean activityCompleted,
        
        @Schema(description = "Indica si la lección fue completada exitosamente", example = "false")
        Boolean lessonCompleted,
        
        @Schema(description = "Indica si el ecosistema fue completado", example = "false")
        Boolean ecosystemCompleted,
        
        @Schema(description = "Racha actual de días consecutivos", example = "3")
        Integer streakCount,
        
        @Schema(description = "Lista de eventos que otorgaron puntos de experiencia", example = "[\"Respuesta correcta\", \"Primera vez\"]")
        List<String> xpEvents,
        
        @Schema(description = "Lista de logros desbloqueados en este intento")
        List<AchievementResponseDto> unlockedAchievements
) {
}

