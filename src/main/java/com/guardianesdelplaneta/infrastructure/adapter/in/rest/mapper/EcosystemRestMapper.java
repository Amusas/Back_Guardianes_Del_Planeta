package com.guardianesdelplaneta.infrastructure.adapter.in.rest.mapper;

import com.guardianesdelplaneta.domain.model.Ecosystem;
import com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto.EcosystemRequestDto;
import com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto.EcosystemResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EcosystemRestMapper {

    EcosystemResponseDto toResponseDto(Ecosystem domain);

    @Mapping(target = "id", ignore = true)
    Ecosystem toDomain(EcosystemRequestDto dto);
}
