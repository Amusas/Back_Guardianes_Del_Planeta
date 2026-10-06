package com.guardianesdelplaneta.infrastructure.adapter.in.rest.mapper;

import com.guardianesdelplaneta.domain.model.Progress;
import com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto.ProgressResponseDto;
import com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto.ProgressUpdateRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProgressRestMapper {

    ProgressResponseDto toResponseDto(Progress domain);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "completedAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Progress toDomain(ProgressUpdateRequestDto dto);
}
