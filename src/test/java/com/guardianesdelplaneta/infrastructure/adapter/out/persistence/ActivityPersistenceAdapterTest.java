package com.guardianesdelplaneta.infrastructure.adapter.out.persistence;

import com.guardianesdelplaneta.domain.model.Activity;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.entity.ActivityEntity;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.mapper.ActivityPersistenceMapper;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.repository.ActivityRepository;
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
class ActivityPersistenceAdapterTest {

    @Mock
    private ActivityRepository repository;

    @Mock
    private ActivityPersistenceMapper mapper;

    @InjectMocks
    private ActivityPersistenceAdapter adapter;

    @Test
    @DisplayName("findById debe retornar actividad cuando existe y vacío cuando no")
    void findById_HappyAndSadPath() {
        ActivityEntity entity = new ActivityEntity();
        Activity domain = new Activity();
        domain.setId(1L);
        when(repository.findById(1L)).thenReturn(Optional.of(entity));
        when(mapper.toDomain(entity)).thenReturn(domain);
        when(repository.findById(99L)).thenReturn(Optional.empty());

        assertTrue(adapter.findById(1L).isPresent());
        assertTrue(adapter.findById(99L).isEmpty());
    }

    @Test
    @DisplayName("findByLessonIdOrderByOrderIndexAsc debe retornar lista ordenada de actividades")
    void findByLessonIdOrderByOrderIndexAsc_Success() {
        ActivityEntity entity = new ActivityEntity();
        Activity domain = new Activity();
        domain.setId(1L);
        domain.setLessonId(5L);
        domain.setOrderIndex(1);
        when(repository.findByLessonIdOrderByOrderIndexAsc(5L)).thenReturn(List.of(entity));
        when(mapper.toDomain(entity)).thenReturn(domain);

        List<Activity> result = adapter.findByLessonIdOrderByOrderIndexAsc(5L);

        assertEquals(1, result.size());
        assertEquals(5L, result.get(0).getLessonId());
    }
}
