package com.guardianesdelplaneta.domain.model;

import com.guardianesdelplaneta.domain.model.enums.ActivityType;

public class Activity {

    private Long id;
    private Long lessonId;
    private ActivityType type;
    private String title;
    private String configurationJson;
    private Integer xpReward;
    private Integer orderIndex;

    public Activity() {
    }

    public Activity(Long id, Long lessonId, ActivityType type, String title, String configurationJson, Integer xpReward, Integer orderIndex) {
        this.id = id;
        this.lessonId = lessonId;
        this.type = type;
        this.title = title;
        this.configurationJson = configurationJson;
        this.xpReward = xpReward != null ? xpReward : 0;
        this.orderIndex = orderIndex;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLessonId() {
        return lessonId;
    }

    public void setLessonId(Long lessonId) {
        this.lessonId = lessonId;
    }

    public ActivityType getType() {
        return type;
    }

    public void setType(ActivityType type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getConfigurationJson() {
        return configurationJson;
    }

    public void setConfigurationJson(String configurationJson) {
        this.configurationJson = configurationJson;
    }

    public Integer getXpReward() {
        return xpReward;
    }

    public void setXpReward(Integer xpReward) {
        this.xpReward = xpReward;
    }

    public Integer getOrderIndex() {
        return orderIndex;
    }

    public void setOrderIndex(Integer orderIndex) {
        this.orderIndex = orderIndex;
    }
}
