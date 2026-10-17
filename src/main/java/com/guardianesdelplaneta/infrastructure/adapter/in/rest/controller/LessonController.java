package com.guardianesdelplaneta.infrastructure.adapter.in.rest.controller;

import com.guardianesdelplaneta.domain.port.in.CompleteLessonUseCase;
import com.guardianesdelplaneta.domain.port.in.GetActivitiesUseCase;
import com.guardianesdelplaneta.domain.port.in.GetChildProgressUseCase;
import com.guardianesdelplaneta.domain.port.in.GetLessonsUseCase;
import com.guardianesdelplaneta.domain.model.Progress;
import com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto.ActivityResponseDto;
import com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto.LessonResponseDto;
import com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto.ProgressResponseDto;
import com.guardianesdelplaneta.infrastructure.adapter.in.rest.mapper.ActivityRestMapper;
import com.guardianesdelplaneta.infrastructure.adapter.in.rest.mapper.LessonRestMapper;
import com.guardianesdelplaneta.infrastructure.adapter.in.rest.mapper.ProgressRestMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "Lecciones", description = "Operaciones relacionadas con las lecciones")
public class LessonController {
    private final GetLessonsUseCase getLessonsUseCase;
    private final LessonRestMapper lessonRestMapper;
    private final GetActivitiesUseCase getActivitiesUseCase;
    private final ActivityRestMapper activityRestMapper;
    private final CompleteLessonUseCase completeLessonUseCase;
    private final GetChildProgressUseCase getChildProgressUseCase;
    private final ProgressRestMapper progressRestMapper;

    public LessonController(GetLessonsUseCase getLessonsUseCase,
                            LessonRestMapper lessonRestMapper,
                            GetActivitiesUseCase getActivitiesUseCase,
                            ActivityRestMapper activityRestMapper,
                            CompleteLessonUseCase completeLessonUseCase,
                            GetChildProgressUseCase getChildProgressUseCase,
                            ProgressRestMapper progressRestMapper) {
        this.getLessonsUseCase = getLessonsUseCase;
        this.lessonRestMapper = lessonRestMapper;
        this.getActivitiesUseCase = getActivitiesUseCase;
        this.activityRestMapper = activityRestMapper;
        this.completeLessonUseCase = completeLessonUseCase;
        this.getChildProgressUseCase = getChildProgressUseCase;
        this.progressRestMapper = progressRestMapper;
    }

    @Operation(summary = "Obtener lecciones activas para un ecosistema")
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
    @GetMapping("/ecosystems/{ecosystemId}/lessons")
    public ResponseEntity<List<LessonResponseDto>> getActiveLessons(@PathVariable Long ecosystemId) {
        List<LessonResponseDto> response = getLessonsUseCase.getActiveLessonsByEcosystemId(ecosystemId).stream()
                .map(lessonRestMapper::toResponseDto).toList();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Obtener lección por ID")
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
    @GetMapping("/lessons/{lessonId}")
    public ResponseEntity<LessonResponseDto> getLessonById(@PathVariable Long lessonId) {
        return ResponseEntity.ok(lessonRestMapper.toResponseDto(getLessonsUseCase.getLessonById(lessonId)));
    }

    @Operation(summary = "Obtener actividades de una lección", description = "Devuelve la lista ordenada de actividades asociadas a una lección específica")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa"),
            @ApiResponse(responseCode = "401", description = "No autorizado"),
            @ApiResponse(responseCode = "404", description = "Lección no encontrada"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/lessons/{lessonId}/activities")
    public ResponseEntity<List<ActivityResponseDto>> getActivitiesByLessonId(@PathVariable Long lessonId) {
        List<ActivityResponseDto> response = 
                getActivitiesUseCase.getActivitiesByLessonId(lessonId).stream()
                        .map(activityRestMapper::toResponseDto)
                        .toList();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Completar una lección", description = "Marca la lección como completada al 100% para el niño autenticado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lección completada exitosamente"),
            @ApiResponse(responseCode = "401", description = "No autorizado"),
            @ApiResponse(responseCode = "403", description = "Prohibido"),
            @ApiResponse(responseCode = "404", description = "Lección o perfil no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("/lessons/{lessonId}/complete")
    @PreAuthorize("hasRole('CHILD')")
    public ResponseEntity<ProgressResponseDto> completeLesson(@PathVariable Long lessonId, Authentication authentication) {
        Object credentials = authentication.getCredentials();
        Long userId = credentials instanceof Number ? ((Number) credentials).longValue() : (Long) credentials;
        Progress progress = completeLessonUseCase.completeLesson(userId, lessonId);
        return ResponseEntity.ok(progressRestMapper.toResponseDto(progress));
    }

    @Operation(summary = "Obtener IDs de lecciones completadas", description = "Devuelve la lista de IDs de lecciones completadas por el niño autenticado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa"),
            @ApiResponse(responseCode = "401", description = "No autorizado"),
            @ApiResponse(responseCode = "403", description = "Prohibido"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/lessons/progress/me")
    @PreAuthorize("hasRole('CHILD')")
    public ResponseEntity<List<Long>> getMyCompletedLessons(Authentication authentication) {
        Object credentials = authentication.getCredentials();
        Long userId = credentials instanceof Number ? ((Number) credentials).longValue() : (Long) credentials;
        return ResponseEntity.ok(getChildProgressUseCase.getCompletedLessonIdsByUserId(userId));
    }
}

