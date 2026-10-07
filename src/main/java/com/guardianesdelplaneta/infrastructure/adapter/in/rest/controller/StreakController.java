package com.guardianesdelplaneta.infrastructure.adapter.in.rest.controller;

import com.guardianesdelplaneta.domain.model.Streak;
import com.guardianesdelplaneta.domain.port.in.GetStreakUseCase;
import com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto.StreakResponseDto;
import com.guardianesdelplaneta.infrastructure.adapter.in.rest.mapper.StreakRestMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/children/me/streak")
@Tag(name = "Rachas", description = "Operaciones relacionadas con las rachas")
public class StreakController {

    private final GetStreakUseCase getStreakUseCase;
    private final StreakRestMapper streakRestMapper;

    public StreakController(GetStreakUseCase getStreakUseCase, StreakRestMapper streakRestMapper) {
        this.getStreakUseCase = getStreakUseCase;
        this.streakRestMapper = streakRestMapper;
    }

    @Operation(summary = "Obtener racha actual")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa"),
            @ApiResponse(responseCode = "201", description = "Creado"),
            @ApiResponse(responseCode = "400", description = "Petición incorrecta"),
            @ApiResponse(responseCode = "401", description = "No autorizado"),
            @ApiResponse(responseCode = "403", description = "Prohibido"),
            @ApiResponse(responseCode = "404", description = "No encontrado"),
            @ApiResponse(responseCode = "409", description = "Conflicto"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasRole('CHILD')")
    @GetMapping
    public ResponseEntity<StreakResponseDto> getCurrentStreak() {
        Long userId = getCurrentUserId();
        Optional<Streak> streak = getStreakUseCase.getCurrentStreak(userId);
        return streak.map(s -> ResponseEntity.ok(streakRestMapper.toResponseDto(s)))
                .orElse(ResponseEntity.noContent().build());
    }

    @Operation(summary = "Obtener historial de rachas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa"),
            @ApiResponse(responseCode = "201", description = "Creado"),
            @ApiResponse(responseCode = "400", description = "Petición incorrecta"),
            @ApiResponse(responseCode = "401", description = "No autorizado"),
            @ApiResponse(responseCode = "403", description = "Prohibido"),
            @ApiResponse(responseCode = "404", description = "No encontrado"),
            @ApiResponse(responseCode = "409", description = "Conflicto"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasRole('CHILD')")
    @GetMapping("/history")
    public ResponseEntity<List<StreakResponseDto>> getStreakHistory() {
        Long userId = getCurrentUserId();
        List<Streak> history = getStreakUseCase.getStreakHistory(userId);
        return ResponseEntity.ok(history.stream()
                .map(streakRestMapper::toResponseDto)
                .collect(Collectors.toList()));
    }

    private Long getCurrentUserId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return Long.parseLong(auth.getName());
    }
}
