package com.guardianesdelplaneta.infrastructure.adapter.in.rest.mapper;

import com.guardianesdelplaneta.domain.model.Lesson;
import com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto.LessonRequestDto;
import com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto.LessonResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LessonRestMapper {

    LessonResponseDto toResponseDto(Lesson domain);

    @Mapping(target = "id", ignore = true)
    Lesson toDomain(LessonRequestDto dto);
}
