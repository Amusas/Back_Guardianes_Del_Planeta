package com.guardianesdelplaneta.infrastructure.adapter.in.rest.controller;

import com.guardianesdelplaneta.domain.model.Achievement;
import com.guardianesdelplaneta.domain.model.ChildAchievement;
import com.guardianesdelplaneta.domain.port.in.GetAchievementsUseCase;
import com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto.AchievementResponseDto;
import com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto.ChildAchievementResponseDto;
import com.guardianesdelplaneta.infrastructure.adapter.in.rest.mapper.AchievementRestMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "Logros", description = "Operaciones relacionadas con los logros del juego")
public class AchievementController {

    private final GetAchievementsUseCase getAchievementsUseCase;
    private final AchievementRestMapper achievementRestMapper;

    public AchievementController(GetAchievementsUseCase getAchievementsUseCase, AchievementRestMapper achievementRestMapper) {
        this.getAchievementsUseCase = getAchievementsUseCase;
        this.achievementRestMapper = achievementRestMapper;
    }

    @Operation(summary = "Obtener todos los logros activos", description = "Recupera una lista de todos los logros activos en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista recuperada exitosamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
    })
    @GetMapping("/achievements")
    public ResponseEntity<List<AchievementResponseDto>> getAllAchievements() {
        List<Achievement> activeAchievements = getAchievementsUseCase.getAllActiveAchievements();
        return ResponseEntity.ok(activeAchievements.stream()
                .map(achievementRestMapper::toResponseDto)
                .collect(Collectors.toList()));
    }

    @Operation(summary = "Obtener mis logros", description = "Recupera los logros desbloqueados por el niño autenticado actual")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Logros recuperados exitosamente"),
            @ApiResponse(responseCode = "401", description = "No autorizado", content = @Content),
            @ApiResponse(responseCode = "403", description = "Prohibido", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
    })
    @PreAuthorize("hasRole('CHILD')")
    @GetMapping("/children/me/achievements")
    public ResponseEntity<List<AchievementResponseDto>> getMyAchievements() {
        Long userId = getCurrentUserId();
        List<Achievement> achievements = getAchievementsUseCase.getUnlockedAchievements(userId);
        return ResponseEntity.ok(achievements.stream()
                .map(achievementRestMapper::toResponseDto)
                .collect(Collectors.toList()));
    }

    @Operation(summary = "Obtener estado del logro", description = "Recupera el estado de un logro específico para el niño actual")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estado del logro recuperado exitosamente"),
            @ApiResponse(responseCode = "401", description = "No autorizado", content = @Content),
            @ApiResponse(responseCode = "403", description = "Prohibido", content = @Content),
            @ApiResponse(responseCode = "404", description = "Logro no encontrado", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
    })
    @PreAuthorize("hasRole('CHILD')")
    @GetMapping("/children/me/achievements/{achievementId}")
    public ResponseEntity<ChildAchievementResponseDto> getMyAchievementStatus(@PathVariable Long achievementId) {
        Long userId = getCurrentUserId();
        ChildAchievement childAchievement = getAchievementsUseCase.getAchievementStatus(userId, achievementId);
        return ResponseEntity.ok(achievementRestMapper.toResponseDto(childAchievement));
    }

    private Long getCurrentUserId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Object credentials = auth != null ? auth.getCredentials() : null;
        if (credentials instanceof Number) {
            return ((Number) credentials).longValue();
        }
        if (credentials instanceof String && !((String) credentials).isBlank()) {
            try {
                return Long.parseLong((String) credentials);
            } catch (NumberFormatException ignored) {}
        }
        if (auth != null && auth.getName() != null) {
            try {
                return Long.parseLong(auth.getName());
            } catch (NumberFormatException ignored) {}
        }
        return null;
    }
}

