package com.guardianesdelplaneta.infrastructure.adapter.out.persistence.mapper;

import com.guardianesdelplaneta.domain.model.XPTransaction;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.entity.XPTransactionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface XPTransactionPersistenceMapper {

    @Mapping(target = "childId", source = "child.id")
    XPTransaction toDomain(XPTransactionEntity entity);

    @Mapping(target = "child.id", source = "childId")
    XPTransactionEntity toEntity(XPTransaction domain);
}
