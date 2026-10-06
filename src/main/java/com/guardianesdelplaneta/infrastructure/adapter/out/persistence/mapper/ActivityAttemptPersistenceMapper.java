package com.guardianesdelplaneta.infrastructure.adapter.out.persistence.mapper;

import com.guardianesdelplaneta.domain.model.ActivityAttempt;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.entity.ActivityAttemptEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ActivityAttemptPersistenceMapper {

    @Mapping(target = "childId", source = "child.id")
    @Mapping(target = "activityId", source = "activity.id")
    ActivityAttempt toDomain(ActivityAttemptEntity entity);

    @Mapping(target = "child.id", source = "childId")
    @Mapping(target = "activity.id", source = "activityId")
    ActivityAttemptEntity toEntity(ActivityAttempt domain);
}
