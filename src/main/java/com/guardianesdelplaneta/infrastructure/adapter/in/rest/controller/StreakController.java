package com.guardianesdelplaneta.infrastructure.adapter.in.rest.controller;

import com.guardianesdelplaneta.domain.model.Streak;
import com.guardianesdelplaneta.domain.port.in.GetStreakUseCase;
import com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto.StreakResponseDto;
import com.guardianesdelplaneta.infrastructure.adapter.in.rest.mapper.StreakRestMapper;
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
public class StreakController {

    private final GetStreakUseCase getStreakUseCase;
    private final StreakRestMapper streakRestMapper;

    public StreakController(GetStreakUseCase getStreakUseCase, StreakRestMapper streakRestMapper) {
        this.getStreakUseCase = getStreakUseCase;
        this.streakRestMapper = streakRestMapper;
    }

    @PreAuthorize("hasRole('CHILD')")
    @GetMapping
    public ResponseEntity<StreakResponseDto> getCurrentStreak() {
        Long userId = getCurrentUserId();
        Optional<Streak> streak = getStreakUseCase.getCurrentStreak(userId);
        return streak.map(s -> ResponseEntity.ok(streakRestMapper.toResponseDto(s)))
                .orElse(ResponseEntity.noContent().build());
    }

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
