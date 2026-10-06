package com.guardianesdelplaneta.domain.model;

import com.guardianesdelplaneta.domain.model.enums.AchievementConditionType;

public class Achievement {

    private Long id;
    private String code;
    private String title;
    private String description;
    private AchievementConditionType conditionType;
    private Integer conditionValue;
    private String visualKey;
    private Boolean isActive;

    public Achievement() {
    }

    public Achievement(Long id, String code, String title, String description, AchievementConditionType conditionType, Integer conditionValue, String visualKey, Boolean isActive) {
        this.id = id;
        this.code = code;
        this.title = title;
        this.description = description;
        this.conditionType = conditionType;
        this.conditionValue = conditionValue;
        this.visualKey = visualKey;
        this.isActive = isActive != null ? isActive : true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AchievementConditionType getConditionType() {
        return conditionType;
    }

    public void setConditionType(AchievementConditionType conditionType) {
        this.conditionType = conditionType;
    }

    public Integer getConditionValue() {
        return conditionValue;
    }

    public void setConditionValue(Integer conditionValue) {
        this.conditionValue = conditionValue;
    }

    public String getVisualKey() {
        return visualKey;
    }

    public void setVisualKey(String visualKey) {
        this.visualKey = visualKey;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }
}
