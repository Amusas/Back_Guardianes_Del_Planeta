package com.guardianesdelplaneta.infrastructure.adapter.in.rest.controller;

import com.guardianesdelplaneta.domain.port.in.GetGuardianChildrenUseCase;
import com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto.ChildProfileResponseDto;
import com.guardianesdelplaneta.infrastructure.adapter.in.rest.mapper.ChildProfileRestMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/guardians")
public class GuardianController {
    private final GetGuardianChildrenUseCase getGuardianChildrenUseCase;
    private final ChildProfileRestMapper childProfileRestMapper;

    public GuardianController(GetGuardianChildrenUseCase getGuardianChildrenUseCase, ChildProfileRestMapper childProfileRestMapper) {
        this.getGuardianChildrenUseCase = getGuardianChildrenUseCase;
        this.childProfileRestMapper = childProfileRestMapper;
    }

    @GetMapping("/me/children")
    @PreAuthorize("hasRole('GUARDIAN')")
    public ResponseEntity<List<ChildProfileResponseDto>> getMyChildren() {
        Object credentials = SecurityContextHolder.getContext().getAuthentication().getCredentials();
        Long userId;
        userId = credentials instanceof Number ? ((Number) credentials).longValue() : (Long) credentials;
        List<ChildProfileResponseDto> response = getGuardianChildrenUseCase.getChildrenByGuardianId(userId).stream()
                .map(childProfileRestMapper::toResponseDto).toList();
        return ResponseEntity.ok(response);
    }
}

