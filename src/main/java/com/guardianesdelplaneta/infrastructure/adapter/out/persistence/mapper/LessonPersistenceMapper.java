package com.guardianesdelplaneta.infrastructure.adapter.out.persistence.mapper;

import com.guardianesdelplaneta.domain.model.Lesson;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.entity.LessonEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LessonPersistenceMapper {

    @Mapping(target = "ecosystemId", source = "ecosystem.id")
    Lesson toDomain(LessonEntity entity);

    @Mapping(target = "ecosystem.id", source = "ecosystemId")
    LessonEntity toEntity(Lesson domain);
}
