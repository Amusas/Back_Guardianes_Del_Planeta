package com.guardianesdelplaneta.infrastructure.adapter.out.persistence;

import com.guardianesdelplaneta.domain.model.Progress;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.entity.ProgressEntity;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.mapper.ProgressPersistenceMapper;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.repository.ProgressRepository;
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
class ProgressPersistenceAdapterTest {

    @Mock
    private ProgressRepository repository;

    @Mock
    private ProgressPersistenceMapper mapper;

    @InjectMocks
    private ProgressPersistenceAdapter adapter;

    @Test
    @DisplayName("findByChildIdAndLessonId debe retornar progreso cuando existe y vacío cuando no")
    void findByChildIdAndLessonId_HappyAndSadPath() {
        ProgressEntity entity = new ProgressEntity();
        Progress domain = new Progress();
        domain.setId(1L);
        domain.setChildId(10L);
        domain.setLessonId(5L);
        when(repository.findByChildIdAndLessonId(10L, 5L)).thenReturn(Optional.of(entity));
        when(mapper.toDomain(entity)).thenReturn(domain);
        when(repository.findByChildIdAndLessonId(10L, 99L)).thenReturn(Optional.empty());

        assertTrue(adapter.findByChildIdAndLessonId(10L, 5L).isPresent());
        assertTrue(adapter.findByChildIdAndLessonId(10L, 99L).isEmpty());
    }

    @Test
    @DisplayName("findByChildId debe retornar lista de progresos del niño")
    void findByChildId_Success() {
        ProgressEntity entity = new ProgressEntity();
        Progress domain = new Progress();
        domain.setId(1L);
        domain.setChildId(10L);
        when(repository.findByChildId(10L)).thenReturn(List.of(entity));
        when(mapper.toDomain(entity)).thenReturn(domain);

        List<Progress> result = adapter.findByChildId(10L);

        assertEquals(1, result.size());
        assertEquals(10L, result.get(0).getChildId());
    }

    @Test
    @DisplayName("save debe persistir y retornar el progreso")
    void save_Success() {
        Progress domain = new Progress();
        domain.setId(1L);
        ProgressEntity entity = new ProgressEntity();
        when(mapper.toEntity(domain)).thenReturn(entity);
        when(repository.save(entity)).thenReturn(entity);
        when(mapper.toDomain(entity)).thenReturn(domain);

        Progress saved = adapter.save(domain);

        assertNotNull(saved);
        assertEquals(1L, saved.getId());
    }
}
