package com.guardianesdelplaneta.infrastructure.adapter.out.persistence.mapper;

import com.guardianesdelplaneta.domain.model.Streak;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.entity.StreakEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StreakPersistenceMapper {

    @Mapping(target = "childId", source = "child.id")
    Streak toDomain(StreakEntity entity);

    @Mapping(target = "child.id", source = "childId")
    StreakEntity toEntity(Streak domain);
}
