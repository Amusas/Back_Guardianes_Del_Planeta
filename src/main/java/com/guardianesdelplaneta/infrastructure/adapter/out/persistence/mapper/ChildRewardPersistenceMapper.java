package com.guardianesdelplaneta.infrastructure.adapter.out.persistence.mapper;

import com.guardianesdelplaneta.domain.model.ChildReward;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.entity.ChildRewardEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ChildRewardPersistenceMapper {

    @Mapping(target = "childId", source = "child.id")
    @Mapping(target = "rewardId", source = "reward.id")
    ChildReward toDomain(ChildRewardEntity entity);

    @Mapping(target = "child.id", source = "childId")
    @Mapping(target = "reward.id", source = "rewardId")
    ChildRewardEntity toEntity(ChildReward domain);
}
