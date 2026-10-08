package com.guardianesdelplaneta.application.service;

import com.guardianesdelplaneta.domain.exception.LessonNotFoundException;
import com.guardianesdelplaneta.domain.model.Lesson;
import com.guardianesdelplaneta.domain.port.out.LessonPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetLessonsServiceTest {

    @Mock private LessonPersistencePort port;

    private GetLessonsService service;

    @BeforeEach
    void setUp() {
        service = new GetLessonsService(port);
    }

    @Test
    void testGetActiveLessonsByEcosystemId() {
        when(port.findActiveLessonsByEcosystemId(1L)).thenReturn(Collections.singletonList(new Lesson()));
        List<Lesson> res = service.getActiveLessonsByEcosystemId(1L);
        assertEquals(1, res.size());
    }

    @Test
    void testGetLessonById_Found() {
        when(port.findById(100L)).thenReturn(Optional.of(new Lesson()));
        assertNotNull(service.getLessonById(100L));
    }

    @Test
    void testGetLessonById_NotFound() {
        when(port.findById(100L)).thenReturn(Optional.empty());
        assertThrows(LessonNotFoundException.class, () -> service.getLessonById(100L));
    }
}
