package com.guardianesdelplaneta.infrastructure.adapter.in.rest.controller;

import com.guardianesdelplaneta.domain.model.ChildProfile;
import com.guardianesdelplaneta.domain.port.in.GetGuardianChildDashboardUseCase;
import com.guardianesdelplaneta.domain.port.in.GetGuardianChildrenUseCase;
import com.guardianesdelplaneta.domain.port.in.RegisterChildForGuardianUseCase;
import com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto.ChildProfileResponseDto;
import com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto.GuardianChildDashboardDto;
import com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto.RegisterChildForGuardianRequestDto;
import com.guardianesdelplaneta.infrastructure.adapter.in.rest.mapper.ChildProfileRestMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/guardians")
@Tag(name = "Guardianes", description = "Operaciones relacionadas con los guardianes")
public class GuardianController {

    private final GetGuardianChildrenUseCase getGuardianChildrenUseCase;
    private final RegisterChildForGuardianUseCase registerChildForGuardianUseCase;
    private final GetGuardianChildDashboardUseCase getGuardianChildDashboardUseCase;
    private final ChildProfileRestMapper childProfileRestMapper;
    private final com.guardianesdelplaneta.infrastructure.adapter.out.persistence.repository.UserRepository userRepository;

    public GuardianController(
            GetGuardianChildrenUseCase getGuardianChildrenUseCase,
            RegisterChildForGuardianUseCase registerChildForGuardianUseCase,
            GetGuardianChildDashboardUseCase getGuardianChildDashboardUseCase,
            ChildProfileRestMapper childProfileRestMapper,
            com.guardianesdelplaneta.infrastructure.adapter.out.persistence.repository.UserRepository userRepository) {
        this.getGuardianChildrenUseCase = getGuardianChildrenUseCase;
        this.registerChildForGuardianUseCase = registerChildForGuardianUseCase;
        this.getGuardianChildDashboardUseCase = getGuardianChildDashboardUseCase;
        this.childProfileRestMapper = childProfileRestMapper;
        this.userRepository = userRepository;
    }

    @Operation(summary = "Obtener hijos del guardián")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa"),
            @ApiResponse(responseCode = "401", description = "No autorizado"),
            @ApiResponse(responseCode = "403", description = "Prohibido"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/me/children")
    @PreAuthorize("hasRole('GUARDIAN')")
    public ResponseEntity<List<ChildProfileResponseDto>> getMyChildren() {
        Long userId = getAuthenticatedGuardianId();
        List<ChildProfileResponseDto> response = getGuardianChildrenUseCase.getChildrenByGuardianId(userId).stream()
                .map(childProfileRestMapper::toResponseDto)
                .toList();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Registrar y asociar un perfil infantil al acudiente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cuenta infantil creada y asociada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos"),
            @ApiResponse(responseCode = "401", description = "No autorizado"),
            @ApiResponse(responseCode = "403", description = "Prohibido"),
            @ApiResponse(responseCode = "409", description = "El nombre de usuario infantil ya existe"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("/me/children")
    @PreAuthorize("hasRole('GUARDIAN')")
    public ResponseEntity<ChildProfileResponseDto> registerChild(
            @Valid @RequestBody RegisterChildForGuardianRequestDto request) {
        Long guardianUserId = getAuthenticatedGuardianId();
        ChildProfile createdProfile = registerChildForGuardianUseCase.registerChildForGuardian(
                guardianUserId,
                request.username(),
                request.password(),
                request.name(),
                request.dateOfBirth()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(childProfileRestMapper.toResponseDto(createdProfile));
    }

    @Operation(summary = "Consultar el dashboard y métricas del niño")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dashboard recuperado exitosamente"),
            @ApiResponse(responseCode = "401", description = "No autorizado"),
            @ApiResponse(responseCode = "403", description = "Prohibido - el niño no pertenece a este acudiente"),
            @ApiResponse(responseCode = "404", description = "Perfil infantil no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/me/children/{childId}/dashboard")
    @PreAuthorize("hasRole('GUARDIAN')")
    public ResponseEntity<GuardianChildDashboardDto> getChildDashboard(@PathVariable Long childId) {
        Long guardianUserId = getAuthenticatedGuardianId();
        GuardianChildDashboardDto dashboard = getGuardianChildDashboardUseCase.getChildDashboard(guardianUserId, childId);
        return ResponseEntity.ok(dashboard);
    }

    private Long getAuthenticatedGuardianId() {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            throw new com.guardianesdelplaneta.domain.exception.ForbiddenAccessException("No se encontró sesión activa");
        }
        Object credentials = auth.getCredentials();
        if (credentials instanceof Number) {
            return ((Number) credentials).longValue();
        }
        if (credentials instanceof String && !((String) credentials).isBlank()) {
            try {
                return Long.parseLong((String) credentials);
            } catch (NumberFormatException ignored) {}
        }
        String username = auth.getName();
        if (username != null && !username.isBlank()) {
            return userRepository.findByUsername(username)
                    .map(com.guardianesdelplaneta.infrastructure.adapter.out.persistence.entity.UserEntity::getId)
                    .orElseThrow(() -> new com.guardianesdelplaneta.domain.exception.ForbiddenAccessException("Usuario guardián no encontrado"));
        }
        throw new com.guardianesdelplaneta.domain.exception.ForbiddenAccessException("No se pudo identificar al acudiente");
    }
}
