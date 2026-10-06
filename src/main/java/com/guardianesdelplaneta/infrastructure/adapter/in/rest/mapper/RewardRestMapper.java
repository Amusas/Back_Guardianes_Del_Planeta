package com.guardianesdelplaneta.infrastructure.adapter.in.rest.mapper;

import com.guardianesdelplaneta.domain.model.ChildReward;
import com.guardianesdelplaneta.domain.model.Reward;
import com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto.ChildRewardResponseDto;
import com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto.RewardRequestDto;
import com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto.RewardResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RewardRestMapper {

    RewardResponseDto toResponseDto(Reward domain);

    @Mapping(target = "id", ignore = true)
    Reward toDomain(RewardRequestDto dto);

    ChildRewardResponseDto toResponseDto(ChildReward domain);
}
