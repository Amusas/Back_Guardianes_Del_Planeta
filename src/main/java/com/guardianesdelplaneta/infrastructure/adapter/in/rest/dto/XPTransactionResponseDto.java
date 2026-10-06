package com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto;

import com.guardianesdelplaneta.domain.model.enums.XPTransactionType;

import java.time.LocalDateTime;

public record XPTransactionResponseDto(
        Long id,
        Long childId,
        Integer amount,
        XPTransactionType type,
        Long referenceId,
        String description,
        LocalDateTime createdAt
) {
}
