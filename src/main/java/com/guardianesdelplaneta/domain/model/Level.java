package com.guardianesdelplaneta.domain.model;

public class Level {

    private Long id;
    private Integer levelNumber;
    private String name;
    private Integer requiredXp;
    private String description;
    private String visualKey;

    public Level() {
    }

    public Level(Long id, Integer levelNumber, String name, Integer requiredXp, String description, String visualKey) {
        this.id = id;
        this.levelNumber = levelNumber;
        this.name = name;
        this.requiredXp = requiredXp;
        this.description = description;
        this.visualKey = visualKey;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getLevelNumber() {
        return levelNumber;
    }

    public void setLevelNumber(Integer levelNumber) {
        this.levelNumber = levelNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRequiredXp() {
        return requiredXp;
    }

    public void setRequiredXp(Integer requiredXp) {
        this.requiredXp = requiredXp;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVisualKey() {
        return visualKey;
    }

    public void setVisualKey(String visualKey) {
        this.visualKey = visualKey;
    }
}
