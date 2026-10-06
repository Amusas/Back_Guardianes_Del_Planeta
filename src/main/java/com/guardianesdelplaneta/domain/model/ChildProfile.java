package com.guardianesdelplaneta.domain.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

public class ChildProfile {

    private Long id;
    private Long userId;
    private String name;
    private LocalDate dateOfBirth;
    private Integer totalXp;
    private Long levelId;
    private Integer streakCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ChildProfile() {
    }

    public ChildProfile(Long id, Long userId, String name, LocalDate dateOfBirth, Integer totalXp, Long levelId, Integer streakCount, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.totalXp = totalXp != null ? totalXp : 0;
        this.levelId = levelId;
        this.streakCount = streakCount != null ? streakCount : 0;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Integer getTotalXp() {
        return totalXp;
    }

    public void setTotalXp(Integer totalXp) {
        this.totalXp = totalXp;
    }

    public Long getLevelId() {
        return levelId;
    }

    public void setLevelId(Long levelId) {
        this.levelId = levelId;
    }

    public Integer getStreakCount() {
        return streakCount;
    }

    public void setStreakCount(Integer streakCount) {
        this.streakCount = streakCount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getAge() {
        if (this.dateOfBirth == null) {
            return null;
        }
        return Period.between(this.dateOfBirth, LocalDate.now()).getYears();
    }
}
