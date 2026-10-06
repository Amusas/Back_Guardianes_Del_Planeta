package com.guardianesdelplaneta.infrastructure.adapter.out.persistence.mapper;

import com.guardianesdelplaneta.domain.model.Level;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.entity.LevelEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LevelPersistenceMapper {

    Level toDomain(LevelEntity entity);

    LevelEntity toEntity(Level domain);
}
