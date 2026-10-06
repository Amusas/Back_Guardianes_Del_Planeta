package com.guardianesdelplaneta.infrastructure.adapter.out.persistence.mapper;

import com.guardianesdelplaneta.domain.model.Ecosystem;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.entity.EcosystemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EcosystemPersistenceMapper {

    @Mapping(target = "requiredLevelId", source = "requiredLevel.id")
    Ecosystem toDomain(EcosystemEntity entity);

    @Mapping(target = "requiredLevel.id", source = "requiredLevelId")
    EcosystemEntity toEntity(Ecosystem domain);
}
