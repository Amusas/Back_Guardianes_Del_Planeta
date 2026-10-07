package com.guardianesdelplaneta.infrastructure.adapter.in.rest.controller;

import com.guardianesdelplaneta.domain.port.in.GetChildProfileUseCase;
import com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto.ChildProfileResponseDto;
import com.guardianesdelplaneta.infrastructure.adapter.in.rest.mapper.ChildProfileRestMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/children")
@Tag(name = "Perfiles de Niños", description = "Operaciones relacionadas con los perfiles de niños")
public class ChildProfileController {
    private final GetChildProfileUseCase getChildProfileUseCase;
    private final ChildProfileRestMapper childProfileRestMapper;

    public ChildProfileController(GetChildProfileUseCase getChildProfileUseCase, ChildProfileRestMapper childProfileRestMapper) {
        this.getChildProfileUseCase = getChildProfileUseCase;
        this.childProfileRestMapper = childProfileRestMapper;
    }

    @Operation(summary = "Obtener mi perfil", description = "Recupera el perfil del niño autenticado actual")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Perfil recuperado exitosamente"),
            @ApiResponse(responseCode = "401", description = "No autorizado", content = @Content),
            @ApiResponse(responseCode = "403", description = "Prohibido", content = @Content),
            @ApiResponse(responseCode = "404", description = "Perfil no encontrado", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
    })
    @GetMapping("/me")
    @PreAuthorize("hasRole('CHILD')")
    public ResponseEntity<ChildProfileResponseDto> getMyProfile() {
        Object credentials = SecurityContextHolder.getContext().getAuthentication().getCredentials();
        Long userId = credentials instanceof Number ? ((Number) credentials).longValue() : (Long) credentials;
        return ResponseEntity.ok(childProfileRestMapper.toResponseDto(getChildProfileUseCase.getChildProfileByUserId(userId)));
    }
}


