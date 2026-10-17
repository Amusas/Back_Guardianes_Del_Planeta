package com.guardianesdelplaneta.infrastructure.adapter.out.persistence;

import com.guardianesdelplaneta.domain.model.Reward;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.entity.RewardEntity;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.mapper.RewardPersistenceMapper;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.repository.RewardRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RewardPersistenceAdapterTest {

    @Mock
    private RewardRepository repository;

    @Mock
    private RewardPersistenceMapper mapper;

    @InjectMocks
    private RewardPersistenceAdapter adapter;

    @Test
    @DisplayName("findByRequiredLevelIdLessThanEqual debe retornar recompensas desbloqueables")
    void findByRequiredLevelIdLessThanEqual_Success() {
        RewardEntity entity = new RewardEntity();
        Reward domain = new Reward();
        domain.setId(1L);
        domain.setRequiredLevelId(2L);
        when(repository.findByRequiredLevelIdLessThanEqual(2L)).thenReturn(List.of(entity));
        when(mapper.toDomain(entity)).thenReturn(domain);

        List<Reward> result = adapter.findByRequiredLevelIdLessThanEqual(2L);

        assertEquals(1, result.size());
        assertEquals(2L, result.get(0).getRequiredLevelId());
    }
}
