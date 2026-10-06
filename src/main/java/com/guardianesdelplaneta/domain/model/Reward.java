package com.guardianesdelplaneta.domain.model;

import com.guardianesdelplaneta.domain.model.enums.RewardType;

public class Reward {

    private Long id;
    private String name;
    private String description;
    private RewardType type;
    private String visualKey;
    private Long requiredLevelId;
    private Boolean isActive;

    public Reward() {
    }

    public Reward(Long id, String name, String description, RewardType type, String visualKey, Long requiredLevelId, Boolean isActive) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
        this.visualKey = visualKey;
        this.requiredLevelId = requiredLevelId;
        this.isActive = isActive != null ? isActive : true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public RewardType getType() {
        return type;
    }

    public void setType(RewardType type) {
        this.type = type;
    }

    public String getVisualKey() {
        return visualKey;
    }

    public void setVisualKey(String visualKey) {
        this.visualKey = visualKey;
    }

    public Long getRequiredLevelId() {
        return requiredLevelId;
    }

    public void setRequiredLevelId(Long requiredLevelId) {
        this.requiredLevelId = requiredLevelId;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }
}
