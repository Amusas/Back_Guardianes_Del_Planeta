package com.guardianesdelplaneta.infrastructure.adapter.in.rest.controller;

import com.guardianesdelplaneta.domain.model.Achievement;
import com.guardianesdelplaneta.domain.model.ChildAchievement;
import com.guardianesdelplaneta.domain.port.in.GetAchievementsUseCase;
import com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto.AchievementResponseDto;
import com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto.ChildAchievementResponseDto;
import com.guardianesdelplaneta.infrastructure.adapter.in.rest.mapper.AchievementRestMapper;
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
public class AchievementController {

    private final GetAchievementsUseCase getAchievementsUseCase;
    private final AchievementRestMapper achievementRestMapper;

    public AchievementController(GetAchievementsUseCase getAchievementsUseCase, AchievementRestMapper achievementRestMapper) {
        this.getAchievementsUseCase = getAchievementsUseCase;
        this.achievementRestMapper = achievementRestMapper;
    }

    @GetMapping("/achievements")
    public ResponseEntity<List<AchievementResponseDto>> getAllAchievements() {
        List<Achievement> activeAchievements = getAchievementsUseCase.getAllActiveAchievements();
        return ResponseEntity.ok(activeAchievements.stream()
                .map(achievementRestMapper::toResponseDto)
                .collect(Collectors.toList()));
    }

    @PreAuthorize("hasRole('CHILD')")
    @GetMapping("/children/me/achievements")
    public ResponseEntity<List<AchievementResponseDto>> getMyAchievements() {
        Long userId = getCurrentUserId();
        List<Achievement> achievements = getAchievementsUseCase.getUnlockedAchievements(userId);
        return ResponseEntity.ok(achievements.stream()
                .map(achievementRestMapper::toResponseDto)
                .collect(Collectors.toList()));
    }

    @PreAuthorize("hasRole('CHILD')")
    @GetMapping("/children/me/achievements/{achievementId}")
    public ResponseEntity<ChildAchievementResponseDto> getMyAchievementStatus(@PathVariable Long achievementId) {
        Long userId = getCurrentUserId();
        ChildAchievement childAchievement = getAchievementsUseCase.getAchievementStatus(userId, achievementId);
        return ResponseEntity.ok(achievementRestMapper.toResponseDto(childAchievement));
    }

    private Long getCurrentUserId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return Long.parseLong(auth.getName()); // assuming JWT Subject is the user ID
    }
}
