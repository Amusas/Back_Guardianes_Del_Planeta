package com.guardianesdelplaneta.infrastructure.adapter.in.rest.mapper;

import com.guardianesdelplaneta.domain.model.XPTransaction;
import com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto.XPTransactionResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface XPTransactionRestMapper {

    XPTransactionResponseDto toResponseDto(XPTransaction domain);
}
