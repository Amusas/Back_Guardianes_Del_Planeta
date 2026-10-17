package com.guardianesdelplaneta.infrastructure.adapter.out.persistence;

import com.guardianesdelplaneta.domain.model.ChildReward;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.entity.ChildRewardEntity;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.mapper.ChildRewardPersistenceMapper;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.repository.ChildRewardRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ChildRewardPersistenceAdapterTest {

    @Mock
    private ChildRewardRepository repository;

    @Mock
    private ChildRewardPersistenceMapper mapper;

    @InjectMocks
    private ChildRewardPersistenceAdapter adapter;

    @Test
    @DisplayName("save debe persistir y retornar la recompensa del niño")
    void save_Success() {
        ChildReward domain = new ChildReward();
        domain.setId(1L);
        domain.setChildId(10L);
        domain.setRewardId(2L);
        ChildRewardEntity entity = new ChildRewardEntity();
        when(mapper.toEntity(domain)).thenReturn(entity);
        when(repository.save(entity)).thenReturn(entity);
        when(mapper.toDomain(entity)).thenReturn(domain);

        ChildReward saved = adapter.save(domain);

        assertNotNull(saved);
        assertEquals(1L, saved.getId());
    }

    @Test
    @DisplayName("existsByChildIdAndRewardId debe retornar true o false")
    void existsByChildIdAndRewardId_Success() {
        when(repository.existsByChildIdAndRewardId(10L, 2L)).thenReturn(true);
        when(repository.existsByChildIdAndRewardId(10L, 3L)).thenReturn(false);

        assertTrue(adapter.existsByChildIdAndRewardId(10L, 2L));
        assertFalse(adapter.existsByChildIdAndRewardId(10L, 3L));
    }
}
