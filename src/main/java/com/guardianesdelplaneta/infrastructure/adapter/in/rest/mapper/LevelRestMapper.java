package com.guardianesdelplaneta.infrastructure.adapter.in.rest.mapper;

import com.guardianesdelplaneta.domain.model.Level;
import com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto.LevelRequestDto;
import com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto.LevelResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LevelRestMapper {

    LevelResponseDto toResponseDto(Level domain);

    @Mapping(target = "id", ignore = true)
    Level toDomain(LevelRequestDto dto);
}
