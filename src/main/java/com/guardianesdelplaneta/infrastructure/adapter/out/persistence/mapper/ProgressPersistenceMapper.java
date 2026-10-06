package com.guardianesdelplaneta.infrastructure.adapter.out.persistence.mapper;

import com.guardianesdelplaneta.domain.model.Progress;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.entity.ProgressEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProgressPersistenceMapper {

    @Mapping(target = "childId", source = "child.id")
    @Mapping(target = "lessonId", source = "lesson.id")
    Progress toDomain(ProgressEntity entity);

    @Mapping(target = "child.id", source = "childId")
    @Mapping(target = "lesson.id", source = "lessonId")
    ProgressEntity toEntity(Progress domain);
}
