package com.guardianesdelplaneta.application.service;

import com.guardianesdelplaneta.domain.exception.LessonNotFoundException;
import com.guardianesdelplaneta.domain.model.Activity;
import com.guardianesdelplaneta.domain.model.Lesson;
import com.guardianesdelplaneta.domain.port.out.ActivityPersistencePort;
import com.guardianesdelplaneta.domain.port.out.LessonPersistencePort;
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
class GetActivitiesServiceTest {

    @Mock
    private ActivityPersistencePort activityPersistencePort;

    @Mock
    private LessonPersistencePort lessonPersistencePort;

    @InjectMocks
    private GetActivitiesService getActivitiesService;

    @Test
    @DisplayName("Debe retornar actividades ordenadas cuando la lección existe (Camino Feliz)")
    void getActivitiesByLessonId_Success() {
        Lesson lesson = new Lesson();
        lesson.setId(1L);
        lesson.setTitle("El Agua");
        Activity act1 = new Activity();
        act1.setId(10L);
        act1.setLessonId(1L);
        act1.setOrderIndex(1);
        act1.setTitle("Act 1");
        Activity act2 = new Activity();
        act2.setId(11L);
        act2.setLessonId(1L);
        act2.setOrderIndex(2);
        act2.setTitle("Act 2");

        when(lessonPersistencePort.findById(1L)).thenReturn(Optional.of(lesson));
        when(activityPersistencePort.findByLessonIdOrderByOrderIndexAsc(1L)).thenReturn(List.of(act1, act2));

        List<Activity> result = getActivitiesService.getActivitiesByLessonId(1L);

        assertEquals(2, result.size());
        assertEquals("Act 1", result.get(0).getTitle());
        verify(lessonPersistencePort).findById(1L);
        verify(activityPersistencePort).findByLessonIdOrderByOrderIndexAsc(1L);
    }

    @Test
    @DisplayName("Debe lanzar LessonNotFoundException cuando la lección no existe (Camino Triste)")
    void getActivitiesByLessonId_LessonNotFound() {
        when(lessonPersistencePort.findById(99L)).thenReturn(Optional.empty());

        LessonNotFoundException ex = assertThrows(LessonNotFoundException.class,
                () -> getActivitiesService.getActivitiesByLessonId(99L));

        assertTrue(ex.getMessage().contains("99"));
        verify(activityPersistencePort, never()).findByLessonIdOrderByOrderIndexAsc(anyLong());
    }
}
