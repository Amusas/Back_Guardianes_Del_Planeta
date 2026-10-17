package com.guardianesdelplaneta.infrastructure.adapter.out.persistence;

import com.guardianesdelplaneta.domain.model.Level;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.entity.LevelEntity;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.mapper.LevelPersistenceMapper;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.repository.LevelRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LevelPersistenceAdapterTest {

    @Mock
    private LevelRepository repository;

    @Mock
    private LevelPersistenceMapper mapper;

    @InjectMocks
    private LevelPersistenceAdapter adapter;

    @Test
    @DisplayName("findById debe retornar nivel cuando existe y vacío cuando no")
    void findById_HappyAndSadPath() {
        LevelEntity entity = new LevelEntity();
        Level domain = new Level();
        domain.setId(1L);
        domain.setLevelNumber(1);
        when(repository.findById(1L)).thenReturn(Optional.of(entity));
        when(mapper.toDomain(entity)).thenReturn(domain);
        when(repository.findById(99L)).thenReturn(Optional.empty());

        assertTrue(adapter.findById(1L).isPresent());
        assertTrue(adapter.findById(99L).isEmpty());
    }

    @Test
    @DisplayName("findByLevelNumber debe retornar nivel cuando existe y vacío cuando no")
    void findByLevelNumber_HappyAndSadPath() {
        LevelEntity entity = new LevelEntity();
        Level domain = new Level();
        domain.setId(1L);
        domain.setLevelNumber(1);
        when(repository.findByLevelNumber(1)).thenReturn(Optional.of(entity));
        when(mapper.toDomain(entity)).thenReturn(domain);
        when(repository.findByLevelNumber(99)).thenReturn(Optional.empty());

        assertTrue(adapter.findByLevelNumber(1).isPresent());
        assertTrue(adapter.findByLevelNumber(99).isEmpty());
    }
}
