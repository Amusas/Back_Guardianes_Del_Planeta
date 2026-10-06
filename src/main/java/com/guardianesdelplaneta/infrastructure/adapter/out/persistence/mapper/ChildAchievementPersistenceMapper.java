package com.guardianesdelplaneta.infrastructure.adapter.out.persistence.mapper;

import com.guardianesdelplaneta.domain.model.ChildAchievement;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.entity.ChildAchievementEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ChildAchievementPersistenceMapper {

    @Mapping(target = "childId", source = "child.id")
    @Mapping(target = "achievementId", source = "achievement.id")
    ChildAchievement toDomain(ChildAchievementEntity entity);

    @Mapping(target = "child.id", source = "childId")
    @Mapping(target = "achievement.id", source = "achievementId")
    ChildAchievementEntity toEntity(ChildAchievement domain);
}
