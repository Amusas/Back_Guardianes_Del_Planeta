package com.guardianesdelplaneta.domain.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Streak {

    private Long id;
    private Long childId;
    private LocalDate activityDate;
    private Integer consecutiveDay;
    private LocalDateTime createdAt;

    public Streak() {
    }

    public Streak(Long id, Long childId, LocalDate activityDate, Integer consecutiveDay, LocalDateTime createdAt) {
        this.id = id;
        this.childId = childId;
        this.activityDate = activityDate;
        this.consecutiveDay = consecutiveDay != null ? consecutiveDay : 1;
        this.createdAt = createdAt != null ? createdAt : LocalDateTime.now();
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

    public LocalDate getActivityDate() {
        return activityDate;
    }

    public void setActivityDate(LocalDate activityDate) {
        this.activityDate = activityDate;
    }

    public Integer getConsecutiveDay() {
        return consecutiveDay;
    }

    public void setConsecutiveDay(Integer consecutiveDay) {
        this.consecutiveDay = consecutiveDay;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
