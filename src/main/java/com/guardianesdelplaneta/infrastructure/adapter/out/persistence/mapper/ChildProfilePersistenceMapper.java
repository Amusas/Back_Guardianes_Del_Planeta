package com.guardianesdelplaneta.infrastructure.adapter.out.persistence.mapper;

import com.guardianesdelplaneta.domain.model.ChildProfile;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.entity.ChildProfileEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ChildProfilePersistenceMapper {

    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "levelId", source = "level.id")
    ChildProfile toDomain(ChildProfileEntity entity);

    @Mapping(target = "user.id", source = "userId")
    @Mapping(target = "level.id", source = "levelId")
    ChildProfileEntity toEntity(ChildProfile domain);
}
