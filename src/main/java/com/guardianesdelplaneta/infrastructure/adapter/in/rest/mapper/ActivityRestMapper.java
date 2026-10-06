package com.guardianesdelplaneta.infrastructure.adapter.in.rest.mapper;

import com.guardianesdelplaneta.domain.model.Activity;
import com.guardianesdelplaneta.domain.model.ActivityAttempt;
import com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto.ActivityAttemptRequestDto;
import com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto.ActivityAttemptResponseDto;
import com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto.ActivityRequestDto;
import com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto.ActivityResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ActivityRestMapper {

    ActivityResponseDto toResponseDto(Activity domain);

    @Mapping(target = "id", ignore = true)
    Activity toDomain(ActivityRequestDto dto);

    ActivityAttemptResponseDto toResponseDto(ActivityAttempt domain);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "attemptedAt", ignore = true)
    ActivityAttempt toDomain(ActivityAttemptRequestDto dto);
}
