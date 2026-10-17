package com.guardianesdelplaneta.infrastructure.adapter.out.persistence;

import com.guardianesdelplaneta.domain.model.Lesson;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.entity.LessonEntity;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.mapper.LessonPersistenceMapper;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.repository.LessonRepository;
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
class LessonPersistenceAdapterTest {

    @Mock
    private LessonRepository repository;

    @Mock
    private LessonPersistenceMapper mapper;

    @InjectMocks
    private LessonPersistenceAdapter adapter;

    @Test
    @DisplayName("findActiveLessonsByEcosystemId debe retornar lecciones activas ordenadas")
    void findActiveLessonsByEcosystemId_Success() {
        LessonEntity entity = new LessonEntity();
        Lesson domain = new Lesson();
        domain.setId(1L);
        domain.setEcosystemId(2L);
        when(repository.findByEcosystemIdAndIsActiveTrueOrderByOrderIndexAsc(2L)).thenReturn(List.of(entity));
        when(mapper.toDomain(entity)).thenReturn(domain);

        List<Lesson> result = adapter.findActiveLessonsByEcosystemId(2L);

        assertEquals(1, result.size());
        assertEquals(2L, result.get(0).getEcosystemId());
    }

    @Test
    @DisplayName("findById debe retornar lección cuando existe y vacío cuando no")
    void findById_HappyAndSadPath() {
        LessonEntity entity = new LessonEntity();
        Lesson domain = new Lesson();
        domain.setId(1L);
        when(repository.findById(1L)).thenReturn(Optional.of(entity));
        when(mapper.toDomain(entity)).thenReturn(domain);
        when(repository.findById(99L)).thenReturn(Optional.empty());

        assertTrue(adapter.findById(1L).isPresent());
        assertTrue(adapter.findById(99L).isEmpty());
    }
}
