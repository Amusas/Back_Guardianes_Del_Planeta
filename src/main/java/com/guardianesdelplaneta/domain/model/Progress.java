package com.guardianesdelplaneta.domain.model;

import com.guardianesdelplaneta.domain.model.enums.ProgressStatus;

import java.time.LocalDateTime;

public class Progress {

    private Long id;
    private Long childId;
    private Long lessonId;
    private ProgressStatus status;
    private Integer progressPercentage;
    private LocalDateTime completedAt;
    private LocalDateTime updatedAt;

    public Progress() {
    }

    public Progress(Long id, Long childId, Long lessonId, ProgressStatus status, Integer progressPercentage, LocalDateTime completedAt, LocalDateTime updatedAt) {
        this.id = id;
        this.childId = childId;
        this.lessonId = lessonId;
        this.status = status;
        this.progressPercentage = progressPercentage != null ? progressPercentage : 0;
        this.completedAt = completedAt;
        this.updatedAt = updatedAt;
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

    public Long getLessonId() {
        return lessonId;
    }

    public void setLessonId(Long lessonId) {
        this.lessonId = lessonId;
    }

    public ProgressStatus getStatus() {
        return status;
    }

    public void setStatus(ProgressStatus status) {
        this.status = status;
    }

    public Integer getProgressPercentage() {
        return progressPercentage;
    }

    public void setProgressPercentage(Integer progressPercentage) {
        this.progressPercentage = progressPercentage;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public boolean isCompleted() {
        return ProgressStatus.COMPLETED.equals(this.status);
    }
}
