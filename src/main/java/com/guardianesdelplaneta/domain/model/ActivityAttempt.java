package com.guardianesdelplaneta.domain.model;

import java.time.LocalDateTime;

public class ActivityAttempt {

    private Long id;
    private Long childId;
    private Long activityId;
    private Integer attemptNumber;
    private String responseJson;
    private Boolean isCorrect;
    private Integer xpAwarded;
    private LocalDateTime attemptedAt;

    public ActivityAttempt() {
    }

    public ActivityAttempt(Long id, Long childId, Long activityId, Integer attemptNumber, String responseJson, Boolean isCorrect, Integer xpAwarded, LocalDateTime attemptedAt) {
        this.id = id;
        this.childId = childId;
        this.activityId = activityId;
        this.attemptNumber = attemptNumber;
        this.responseJson = responseJson;
        this.isCorrect = isCorrect != null ? isCorrect : false;
        this.xpAwarded = xpAwarded != null ? xpAwarded : 0;
        this.attemptedAt = attemptedAt;
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

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public Integer getAttemptNumber() {
        return attemptNumber;
    }

    public void setAttemptNumber(Integer attemptNumber) {
        this.attemptNumber = attemptNumber;
    }

    public String getResponseJson() {
        return responseJson;
    }

    public void setResponseJson(String responseJson) {
        this.responseJson = responseJson;
    }

    public Boolean getIsCorrect() {
        return isCorrect;
    }

    public void setIsCorrect(Boolean correct) {
        isCorrect = correct;
    }

    public Integer getXpAwarded() {
        return xpAwarded;
    }

    public void setXpAwarded(Integer xpAwarded) {
        this.xpAwarded = xpAwarded;
    }

    public LocalDateTime getAttemptedAt() {
        return attemptedAt;
    }

    public void setAttemptedAt(LocalDateTime attemptedAt) {
        this.attemptedAt = attemptedAt;
    }
}
