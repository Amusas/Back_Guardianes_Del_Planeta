package com.guardianesdelplaneta.infrastructure.adapter.out.persistence.mapper;

import com.guardianesdelplaneta.domain.model.GuardianChild;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.entity.GuardianChildEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GuardianChildPersistenceMapper {

    @Mapping(target = "guardianId", source = "guardian.id")
    @Mapping(target = "childId", source = "child.id")
    GuardianChild toDomain(GuardianChildEntity entity);

    @Mapping(target = "guardian.id", source = "guardianId")
    @Mapping(target = "child.id", source = "childId")
    GuardianChildEntity toEntity(GuardianChild domain);
}
