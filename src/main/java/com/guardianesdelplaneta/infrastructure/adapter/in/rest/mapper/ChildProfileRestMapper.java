package com.guardianesdelplaneta.infrastructure.adapter.in.rest.mapper;

import com.guardianesdelplaneta.domain.model.ChildProfile;
import com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto.ChildProfileCreateRequestDto;
import com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto.ChildProfileResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ChildProfileRestMapper {

    @Mapping(target = "age", expression = "java(domain.getAge())")
    ChildProfileResponseDto toResponseDto(ChildProfile domain);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "totalXp", constant = "0")
    @Mapping(target = "levelId", ignore = true)
    @Mapping(target = "streakCount", constant = "0")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    ChildProfile toDomain(ChildProfileCreateRequestDto dto);
}
