package com.guardianesdelplaneta.infrastructure.adapter.out.persistence.entity;

import com.guardianesdelplaneta.domain.model.enums.ActivityType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "activities")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActivityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lesson_id", nullable = false)
    private LessonEntity lesson;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false, length = 30)
    private ActivityType type;

    @Column(name = "title", nullable = false, length = 150)
    private String title;

    @Column(name = "configuration_json", columnDefinition = "TEXT", nullable = false)
    private String configurationJson;

    @Column(name = "xp_reward", nullable = false)
    private Integer xpReward;

    @Column(name = "order_index", nullable = false)
    private Integer orderIndex;

    @PrePersist
    public void prePersist() {
        if (this.xpReward == null) {
            this.xpReward = 0;
        }
    }
}
