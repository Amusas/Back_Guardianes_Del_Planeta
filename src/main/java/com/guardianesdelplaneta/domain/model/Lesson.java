package com.guardianesdelplaneta.domain.model;

public class Lesson {

    private Long id;
    private Long ecosystemId;
    private String title;
    private String content;
    private Integer orderIndex;
    private Integer xpReward;
    private Boolean isActive;

    public Lesson() {
    }

    public Lesson(Long id, Long ecosystemId, String title, String content, Integer orderIndex, Integer xpReward, Boolean isActive) {
        this.id = id;
        this.ecosystemId = ecosystemId;
        this.title = title;
        this.content = content;
        this.orderIndex = orderIndex;
        this.xpReward = xpReward != null ? xpReward : 0;
        this.isActive = isActive != null ? isActive : true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEcosystemId() {
        return ecosystemId;
    }

    public void setEcosystemId(Long ecosystemId) {
        this.ecosystemId = ecosystemId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getOrderIndex() {
        return orderIndex;
    }

    public void setOrderIndex(Integer orderIndex) {
        this.orderIndex = orderIndex;
    }

    public Integer getXpReward() {
        return xpReward;
    }

    public void setXpReward(Integer xpReward) {
        this.xpReward = xpReward;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }
}
