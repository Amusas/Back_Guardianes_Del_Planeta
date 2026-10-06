package com.guardianesdelplaneta.infrastructure.adapter.out.persistence.mapper;

import com.guardianesdelplaneta.domain.model.Achievement;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.entity.AchievementEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AchievementPersistenceMapper {

    Achievement toDomain(AchievementEntity entity);

    AchievementEntity toEntity(Achievement domain);
}
