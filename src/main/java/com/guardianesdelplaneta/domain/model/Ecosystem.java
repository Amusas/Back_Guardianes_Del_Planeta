package com.guardianesdelplaneta.domain.model;

public class Ecosystem {

    private Long id;
    private String code;
    private String name;
    private String description;
    private Long requiredLevelId;
    private String mascotKey;
    private Boolean isActive;

    public Ecosystem() {
    }

    public Ecosystem(Long id, String code, String name, String description, Long requiredLevelId, String mascotKey, Boolean isActive) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.description = description;
        this.requiredLevelId = requiredLevelId;
        this.mascotKey = mascotKey;
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

    public Long getRequiredLevelId() {
        return requiredLevelId;
    }

    public void setRequiredLevelId(Long requiredLevelId) {
        this.requiredLevelId = requiredLevelId;
    }

    public String getMascotKey() {
        return mascotKey;
    }

    public void setMascotKey(String mascotKey) {
        this.mascotKey = mascotKey;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }
}
