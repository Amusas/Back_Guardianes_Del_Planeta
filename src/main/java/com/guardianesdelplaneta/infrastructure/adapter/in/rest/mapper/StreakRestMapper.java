package com.guardianesdelplaneta.infrastructure.adapter.in.rest.mapper;

import com.guardianesdelplaneta.domain.model.Streak;
import com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto.StreakResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StreakRestMapper {

    StreakResponseDto toResponseDto(Streak domain);
}
