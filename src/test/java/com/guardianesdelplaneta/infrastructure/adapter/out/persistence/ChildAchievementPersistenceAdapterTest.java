package com.guardianesdelplaneta.infrastructure.adapter.out.persistence;

import com.guardianesdelplaneta.domain.model.ChildAchievement;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.entity.ChildAchievementEntity;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.mapper.ChildAchievementPersistenceMapper;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.repository.ChildAchievementRepository;
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
class ChildAchievementPersistenceAdapterTest {

    @Mock
    private ChildAchievementRepository repository;

    @Mock
    private ChildAchievementPersistenceMapper mapper;

    @InjectMocks
    private ChildAchievementPersistenceAdapter adapter;

    @Test
    @DisplayName("findByChildId debe retornar logros del niño")
    void findByChildId_Success() {
        ChildAchievementEntity entity = new ChildAchievementEntity();
        ChildAchievement domain = new ChildAchievement();
        domain.setId(1L);
        domain.setChildId(10L);
        when(repository.findByChildId(10L)).thenReturn(List.of(entity));
        when(mapper.toDomain(entity)).thenReturn(domain);

        List<ChildAchievement> result = adapter.findByChildId(10L);

        assertEquals(1, result.size());
        assertEquals(10L, result.get(0).getChildId());
    }

    @Test
    @DisplayName("findByChildIdAndAchievementId debe retornar logro cuando existe y vacío cuando no")
    void findByChildIdAndAchievementId_HappyAndSadPath() {
        ChildAchievementEntity entity = new ChildAchievementEntity();
        ChildAchievement domain = new ChildAchievement();
        domain.setId(1L);
        when(repository.findByChildIdAndAchievementId(10L, 2L)).thenReturn(Optional.of(entity));
        when(mapper.toDomain(entity)).thenReturn(domain);
        when(repository.findByChildIdAndAchievementId(10L, 99L)).thenReturn(Optional.empty());

        assertTrue(adapter.findByChildIdAndAchievementId(10L, 2L).isPresent());
        assertTrue(adapter.findByChildIdAndAchievementId(10L, 99L).isEmpty());
    }

    @Test
    @DisplayName("existsByChildIdAndAchievementId debe retornar true o false")
    void existsByChildIdAndAchievementId_Success() {
        when(repository.existsByChildIdAndAchievementId(10L, 2L)).thenReturn(true);
        when(repository.existsByChildIdAndAchievementId(10L, 3L)).thenReturn(false);

        assertTrue(adapter.existsByChildIdAndAchievementId(10L, 2L));
        assertFalse(adapter.existsByChildIdAndAchievementId(10L, 3L));
    }

    @Test
    @DisplayName("save debe persistir y retornar el logro del niño")
    void save_Success() {
        ChildAchievement domain = new ChildAchievement();
        domain.setId(1L);
        ChildAchievementEntity entity = new ChildAchievementEntity();
        when(mapper.toEntity(domain)).thenReturn(entity);
        when(repository.save(entity)).thenReturn(entity);
        when(mapper.toDomain(entity)).thenReturn(domain);

        ChildAchievement saved = adapter.save(domain);

        assertNotNull(saved);
        assertEquals(1L, saved.getId());
    }
}
