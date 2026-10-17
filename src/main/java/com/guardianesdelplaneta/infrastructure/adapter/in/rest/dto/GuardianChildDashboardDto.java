package com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import java.util.List;

@Schema(description = "Métricas consolidadas del aprendizaje del niño para el panel del acudiente")
public record GuardianChildDashboardDto(
        @Schema(description = "Identificador del perfil infantil", example = "1")
        Long childId,

        @Schema(description = "Nombre del niño", example = "Juanito")
        String name,

        @Schema(description = "Nombre de usuario único del niño", example = "juanito_eco")
        String username,

        @Schema(description = "Fecha de nacimiento", example = "2016-05-15")
        LocalDate dateOfBirth,

        @Schema(description = "Edad en años", example = "8")
        Integer age,

        @Schema(description = "Puntos de experiencia totales acumulados", example = "1250")
        Integer totalXp,

        @Schema(description = "Número del nivel actual", example = "3")
        Integer levelNumber,

        @Schema(description = "Nombre del nivel actual", example = "Guardián de los Mares")
        String levelName,

        @Schema(description = "Puntos requeridos para alcanzar el nivel actual", example = "1000")
        Integer currentLevelRequiredXp,

        @Schema(description = "Puntos requeridos para alcanzar el siguiente nivel (null si es nivel máximo)", example = "1500")
        Integer nextLevelRequiredXp,

        @Schema(description = "Nombre del siguiente nivel (null si es nivel máximo)", example = "Protector de la Selva")
        String nextLevelName,

        @Schema(description = "Porcentaje de avance hacia el siguiente nivel (0 a 100)", example = "50.0")
        Double levelProgressPercentage,

        @Schema(description = "Cantidad total de lecciones completadas", example = "6")
        Integer completedLessonsCount,

        @Schema(description = "Cantidad total de lecciones disponibles en la plataforma", example = "15")
        Integer totalLessonsCount,

        @Schema(description = "Total de intentos de actividades realizados", example = "20")
        Integer totalAttemptsCount,

        @Schema(description = "Total de intentos con respuesta correcta", example = "18")
        Integer correctAttemptsCount,

        @Schema(description = "Porcentaje de precisión en actividades resueltas (0 a 100)", example = "90.0")
        Double accuracyPercentage,

        @Schema(description = "Racha actual en días consecutivos de actividad", example = "4")
        Integer currentStreak,

        @Schema(description = "Cantidad de logros desbloqueados", example = "3")
        Integer unlockedAchievementsCount,

        @Schema(description = "Cantidad total de logros existentes", example = "8")
        Integer totalAchievementsCount,

        @Schema(description = "Lista detallada de logros desbloqueados")
        List<ChildAchievementSummaryDto> achievements,

        @Schema(description = "Progreso por cada ecosistema disponible")
        List<EcosystemProgressDto> ecosystemProgress,

        @Schema(description = "Historial cronológico de transacciones de puntos XP")
        List<XpHistoryItemDto> xpHistory,

        @Schema(description = "Línea temporal de actividades recientes realizadas por el niño")
        List<RecentActivityDto> recentActivities
) {
}
