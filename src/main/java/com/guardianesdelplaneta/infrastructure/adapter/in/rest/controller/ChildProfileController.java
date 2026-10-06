package com.guardianesdelplaneta.infrastructure.adapter.in.rest.controller;

import com.guardianesdelplaneta.domain.port.in.GetChildProfileUseCase;
import com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto.ChildProfileResponseDto;
import com.guardianesdelplaneta.infrastructure.adapter.in.rest.mapper.ChildProfileRestMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/children")
public class ChildProfileController {
    private final GetChildProfileUseCase getChildProfileUseCase;
    private final ChildProfileRestMapper childProfileRestMapper;

    public ChildProfileController(GetChildProfileUseCase getChildProfileUseCase, ChildProfileRestMapper childProfileRestMapper) {
        this.getChildProfileUseCase = getChildProfileUseCase;
        this.childProfileRestMapper = childProfileRestMapper;
    }

    @GetMapping("/me")
    @PreAuthorize("hasRole('CHILD')")
    public ResponseEntity<ChildProfileResponseDto> getMyProfile() {
        Object credentials = SecurityContextHolder.getContext().getAuthentication().getCredentials();
        Long userId = credentials instanceof Number ? ((Number) credentials).longValue() : (Long) credentials;
        return ResponseEntity.ok(childProfileRestMapper.toResponseDto(getChildProfileUseCase.getChildProfileByUserId(userId)));
    }
}

