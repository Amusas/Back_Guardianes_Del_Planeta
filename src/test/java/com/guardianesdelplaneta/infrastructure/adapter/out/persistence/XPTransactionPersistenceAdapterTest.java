package com.guardianesdelplaneta.infrastructure.adapter.out.persistence;

import com.guardianesdelplaneta.domain.model.XPTransaction;
import com.guardianesdelplaneta.domain.model.enums.XPTransactionType;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.entity.XPTransactionEntity;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.mapper.XPTransactionPersistenceMapper;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.repository.XPTransactionRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class XPTransactionPersistenceAdapterTest {

    @Mock
    private XPTransactionRepository repository;

    @Mock
    private XPTransactionPersistenceMapper mapper;

    @InjectMocks
    private XPTransactionPersistenceAdapter adapter;

    @Test
    @DisplayName("save debe persistir y retornar la transacción de XP")
    void save_Success() {
        XPTransaction domain = new XPTransaction();
        domain.setId(1L);
        domain.setAmount(25);
        XPTransactionEntity entity = new XPTransactionEntity();
        when(mapper.toEntity(domain)).thenReturn(entity);
        when(repository.save(entity)).thenReturn(entity);
        when(mapper.toDomain(entity)).thenReturn(domain);

        XPTransaction saved = adapter.save(domain);

        assertNotNull(saved);
        assertEquals(25, saved.getAmount());
    }

    @Test
    @DisplayName("existsByChildIdAndTypeAndReferenceId debe retornar true o false")
    void existsByChildIdAndTypeAndReferenceId_Success() {
        when(repository.existsByChildIdAndTypeAndReferenceId(10L, XPTransactionType.ACTIVITY, 5L)).thenReturn(true);
        when(repository.existsByChildIdAndTypeAndReferenceId(10L, XPTransactionType.LESSON, 99L)).thenReturn(false);

        assertTrue(adapter.existsByChildIdAndTypeAndReferenceId(10L, XPTransactionType.ACTIVITY, 5L));
        assertFalse(adapter.existsByChildIdAndTypeAndReferenceId(10L, XPTransactionType.LESSON, 99L));
    }
}
