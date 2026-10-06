package com.guardianesdelplaneta.infrastructure.adapter.in.rest.mapper;

import com.guardianesdelplaneta.domain.model.Achievement;
import com.guardianesdelplaneta.domain.model.ChildAchievement;
import com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto.AchievementRequestDto;
import com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto.AchievementResponseDto;
import com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto.ChildAchievementResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AchievementRestMapper {

    AchievementResponseDto toResponseDto(Achievement domain);

    @Mapping(target = "id", ignore = true)
    Achievement toDomain(AchievementRequestDto dto);

    ChildAchievementResponseDto toResponseDto(ChildAchievement domain);
}
