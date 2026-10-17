package com.guardianesdelplaneta.infrastructure.adapter.out.persistence;

import com.guardianesdelplaneta.domain.model.ActivityAttempt;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.entity.ActivityAttemptEntity;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.mapper.ActivityAttemptPersistenceMapper;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.repository.ActivityAttemptRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ActivityAttemptPersistenceAdapterTest {

    @Mock
    private ActivityAttemptRepository repository;

    @Mock
    private ActivityAttemptPersistenceMapper mapper;

    @InjectMocks
    private ActivityAttemptPersistenceAdapter adapter;

    @Test
    @DisplayName("save debe mapear, persistir y retornar el intento de actividad")
    void save_Success() {
        ActivityAttempt domain = new ActivityAttempt();
        domain.setId(1L);
        domain.setChildId(10L);
        domain.setActivityId(20L);
        ActivityAttemptEntity entity = new ActivityAttemptEntity();
        when(mapper.toEntity(domain)).thenReturn(entity);
        when(repository.save(entity)).thenReturn(entity);
        when(mapper.toDomain(entity)).thenReturn(domain);

        ActivityAttempt result = adapter.save(domain);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(repository).save(entity);
    }

    @Test
    @DisplayName("countByChildIdAndActivityId debe retornar el conteo del repositorio")
    void countByChildIdAndActivityId_Success() {
        when(repository.countByChildIdAndActivityId(10L, 20L)).thenReturn(3L);

        long count = adapter.countByChildIdAndActivityId(10L, 20L);

        assertEquals(3L, count);
        verify(repository).countByChildIdAndActivityId(10L, 20L);
    }
}
