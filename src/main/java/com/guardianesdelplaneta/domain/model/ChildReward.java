package com.guardianesdelplaneta.domain.model;

import java.time.LocalDateTime;

public class ChildReward {

    private Long id;
    private Long childId;
    private Long rewardId;
    private LocalDateTime unlockedAt;
    private Boolean isEquipped;

    public ChildReward() {
    }

    public ChildReward(Long id, Long childId, Long rewardId, LocalDateTime unlockedAt, Boolean isEquipped) {
        this.id = id;
        this.childId = childId;
        this.rewardId = rewardId;
        this.unlockedAt = unlockedAt != null ? unlockedAt : LocalDateTime.now();
        this.isEquipped = isEquipped != null ? isEquipped : false;
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

    public Long getRewardId() {
        return rewardId;
    }

    public void setRewardId(Long rewardId) {
        this.rewardId = rewardId;
    }

    public LocalDateTime getUnlockedAt() {
        return unlockedAt;
    }

    public void setUnlockedAt(LocalDateTime unlockedAt) {
        this.unlockedAt = unlockedAt;
    }

    public Boolean getIsEquipped() {
        return isEquipped;
    }

    public void setIsEquipped(Boolean equipped) {
        isEquipped = equipped;
    }
}
