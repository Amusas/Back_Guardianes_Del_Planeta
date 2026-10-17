package com.guardianesdelplaneta.infrastructure.adapter.out.persistence;

import com.guardianesdelplaneta.domain.model.Achievement;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.entity.AchievementEntity;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.mapper.AchievementPersistenceMapper;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.repository.AchievementRepository;
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
class AchievementPersistenceAdapterTest {

    @Mock
    private AchievementRepository achievementRepository;

    @Mock
    private AchievementPersistenceMapper achievementPersistenceMapper;

    @InjectMocks
    private AchievementPersistenceAdapter adapter;

    @Test
    @DisplayName("findAllActive debe retornar lista de logros activos")
    void findAllActive_Success() {
        AchievementEntity entity = new AchievementEntity();
        Achievement domain = new Achievement();
        domain.setId(1L);
        domain.setCode("ACH_1");
        when(achievementRepository.findByIsActiveTrue()).thenReturn(List.of(entity));
        when(achievementPersistenceMapper.toDomain(entity)).thenReturn(domain);

        List<Achievement> result = adapter.findAllActive();

        assertEquals(1, result.size());
        assertEquals("ACH_1", result.get(0).getCode());
        verify(achievementRepository).findByIsActiveTrue();
    }

    @Test
    @DisplayName("findById debe retornar logro cuando existe y vacío cuando no")
    void findById_HappyAndSadPath() {
        AchievementEntity entity = new AchievementEntity();
        Achievement domain = new Achievement();
        domain.setId(1L);
        when(achievementRepository.findById(1L)).thenReturn(Optional.of(entity));
        when(achievementPersistenceMapper.toDomain(entity)).thenReturn(domain);
        when(achievementRepository.findById(99L)).thenReturn(Optional.empty());

        assertTrue(adapter.findById(1L).isPresent());
        assertTrue(adapter.findById(99L).isEmpty());
    }

    @Test
    @DisplayName("findByCode debe retornar logro cuando existe y vacío cuando no")
    void findByCode_HappyAndSadPath() {
        AchievementEntity entity = new AchievementEntity();
        Achievement domain = new Achievement();
        domain.setCode("ACH_1");
        when(achievementRepository.findByCode("ACH_1")).thenReturn(Optional.of(entity));
        when(achievementPersistenceMapper.toDomain(entity)).thenReturn(domain);
        when(achievementRepository.findByCode("NONE")).thenReturn(Optional.empty());

        assertTrue(adapter.findByCode("ACH_1").isPresent());
        assertTrue(adapter.findByCode("NONE").isEmpty());
    }
}
