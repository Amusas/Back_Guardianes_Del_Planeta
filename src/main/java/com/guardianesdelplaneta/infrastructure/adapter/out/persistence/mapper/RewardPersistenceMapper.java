package com.guardianesdelplaneta.infrastructure.adapter.out.persistence.mapper;

import com.guardianesdelplaneta.domain.model.Reward;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.entity.RewardEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RewardPersistenceMapper {

    @Mapping(target = "requiredLevelId", source = "requiredLevel.id")
    Reward toDomain(RewardEntity entity);

    @Mapping(target = "requiredLevel.id", source = "requiredLevelId")
    RewardEntity toEntity(Reward domain);
}
