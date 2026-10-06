package com.guardianesdelplaneta.infrastructure.adapter.out.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "levels")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LevelEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "level_number", unique = true, nullable = false)
    private Integer levelNumber;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "required_xp", nullable = false)
    private Integer requiredXp;

    @Column(name = "description", length = 255)
    private String description;

    @Column(name = "visual_key", length = 100)
    private String visualKey;
}
