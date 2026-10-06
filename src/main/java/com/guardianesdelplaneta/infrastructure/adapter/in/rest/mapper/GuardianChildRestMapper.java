package com.guardianesdelplaneta.infrastructure.adapter.in.rest.mapper;

import com.guardianesdelplaneta.domain.model.GuardianChild;
import com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto.GuardianChildLinkRequestDto;
import com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto.GuardianChildResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GuardianChildRestMapper {

    GuardianChildResponseDto toResponseDto(GuardianChild domain);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    GuardianChild toDomain(GuardianChildLinkRequestDto dto);
}
