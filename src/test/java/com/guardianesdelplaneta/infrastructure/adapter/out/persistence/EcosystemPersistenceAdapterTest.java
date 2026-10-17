package com.guardianesdelplaneta.infrastructure.adapter.out.persistence;

import com.guardianesdelplaneta.domain.model.Ecosystem;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.entity.EcosystemEntity;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.mapper.EcosystemPersistenceMapper;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.repository.EcosystemRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EcosystemPersistenceAdapterTest {

    @Mock
    private EcosystemRepository repository;

    @Mock
    private EcosystemPersistenceMapper mapper;

    @InjectMocks
    private EcosystemPersistenceAdapter adapter;

    @Test
    @DisplayName("findActiveEcosystems debe retornar lista de ecosistemas activos")
    void findActiveEcosystems_Success() {
        EcosystemEntity entity = new EcosystemEntity();
        Ecosystem domain = new Ecosystem();
        domain.setId(1L);
        domain.setCode("BOSQUE_ANDINO");
        when(repository.findByIsActiveTrue()).thenReturn(List.of(entity));
        when(mapper.toDomain(entity)).thenReturn(domain);

        List<Ecosystem> result = adapter.findActiveEcosystems();

        assertEquals(1, result.size());
        assertEquals("BOSQUE_ANDINO", result.get(0).getCode());
    }

    @Test
    @DisplayName("findById debe retornar ecosistema cuando existe y vacío cuando no")
    void findById_HappyAndSadPath() {
        EcosystemEntity entity = new EcosystemEntity();
        Ecosystem domain = new Ecosystem();
        domain.setId(1L);
        when(repository.findById(1L)).thenReturn(Optional.of(entity));
        when(mapper.toDomain(entity)).thenReturn(domain);
        when(repository.findById(99L)).thenReturn(Optional.empty());

        assertTrue(adapter.findById(1L).isPresent());
        assertTrue(adapter.findById(99L).isEmpty());
    }

    @Test
    @DisplayName("findByCode debe retornar ecosistema cuando existe y vacío cuando no")
    void findByCode_HappyAndSadPath() {
        EcosystemEntity entity = new EcosystemEntity();
        Ecosystem domain = new Ecosystem();
        domain.setCode("PARAMO");
        when(repository.findByCode("PARAMO")).thenReturn(Optional.of(entity));
        when(mapper.toDomain(entity)).thenReturn(domain);
        when(repository.findByCode("NONE")).thenReturn(Optional.empty());

        assertTrue(adapter.findByCode("PARAMO").isPresent());
        assertTrue(adapter.findByCode("NONE").isEmpty());
    }
}
