package com.guardianesdelplaneta.infrastructure.adapter.in.rest.mapper;

import com.guardianesdelplaneta.domain.model.User;
import com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto.UserRegisterRequestDto;
import com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto.UserResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserRestMapper {

    UserResponseDto toResponseDto(User domain);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "passwordHash", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    User toDomain(UserRegisterRequestDto dto);
}
