package com.guardianesdelplaneta.domain.model;

import java.time.LocalDateTime;

public class ChildAchievement {

    private Long id;
    private Long childId;
    private Long achievementId;
    private LocalDateTime unlockedAt;

    public ChildAchievement() {
    }

    public ChildAchievement(Long id, Long childId, Long achievementId, LocalDateTime unlockedAt) {
        this.id = id;
        this.childId = childId;
        this.achievementId = achievementId;
        this.unlockedAt = unlockedAt != null ? unlockedAt : LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getChildId() {
        return childId;
    }

    public void setChildId(Long childId) {
        this.childId = childId;
    }

    public Long getAchievementId() {
        return achievementId;
    }

    public void setAchievementId(Long achievementId) {
        this.achievementId = achievementId;
    }

    public LocalDateTime getUnlockedAt() {
        return unlockedAt;
    }

    public void setUnlockedAt(LocalDateTime unlockedAt) {
        this.unlockedAt = unlockedAt;
    }
}
