package com.guardianesdelplaneta.infrastructure.adapter.in.rest.controller;

import com.guardianesdelplaneta.domain.port.in.SubmitActivityAttemptUseCase;
import com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto.ActivityAttemptRequestDto;
import com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto.ActivityAttemptResponseDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/activities")
public class ActivityAttemptController {

    private final SubmitActivityAttemptUseCase submitActivityAttemptUseCase;

    public ActivityAttemptController(SubmitActivityAttemptUseCase submitActivityAttemptUseCase) {
        this.submitActivityAttemptUseCase = submitActivityAttemptUseCase;
    }

    @PostMapping("/{activityId}/attempts")
    @PreAuthorize("hasRole('CHILD')")
    public ResponseEntity<ActivityAttemptResponseDto> submitAttempt(
            @PathVariable Long activityId,
            @Valid @RequestBody ActivityAttemptRequestDto request,
            Authentication authentication) {
        
        Long userId = (Long) authentication.getCredentials();
        ActivityAttemptResponseDto response = submitActivityAttemptUseCase.submitAttempt(userId, activityId, request);
        return ResponseEntity.ok(response);
    }
}
