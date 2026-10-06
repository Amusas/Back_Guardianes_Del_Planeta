package com.guardianesdelplaneta.infrastructure.adapter.out.persistence.mapper;

import com.guardianesdelplaneta.domain.model.Activity;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.entity.ActivityEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ActivityPersistenceMapper {

    @Mapping(target = "lessonId", source = "lesson.id")
    Activity toDomain(ActivityEntity entity);

    @Mapping(target = "lesson.id", source = "lessonId")
    ActivityEntity toEntity(Activity domain);
}
