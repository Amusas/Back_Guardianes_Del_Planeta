package com.guardianesdelplaneta.application.service;

import com.guardianesdelplaneta.domain.exception.ChildProfileNotFoundException;
import com.guardianesdelplaneta.domain.exception.DomainException;
import com.guardianesdelplaneta.domain.model.ChildProfile;
import com.guardianesdelplaneta.domain.model.Lesson;
import com.guardianesdelplaneta.domain.model.Progress;
import com.guardianesdelplaneta.domain.model.enums.ProgressStatus;
import com.guardianesdelplaneta.domain.port.out.ChildProfilePersistencePort;
import com.guardianesdelplaneta.domain.port.out.LessonPersistencePort;
import com.guardianesdelplaneta.domain.port.out.ProgressPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LessonProgressServiceTest {

    @Mock
    private ChildProfilePersistencePort childProfilePort;

    @Mock
    private LessonPersistencePort lessonPort;

    @Mock
    private ProgressPersistencePort progressPort;

    @Mock
    private AchievementEvaluator achievementEvaluator;

    @InjectMocks
    private LessonProgressService lessonProgressService;

    private ChildProfile childProfile;
    private Lesson lesson;

    @BeforeEach
    void setUp() {
        childProfile = new ChildProfile();
        childProfile.setId(10L);
        childProfile.setUserId(2L);
        childProfile.setName("Test Child");

        lesson = new Lesson();
        lesson.setId(5L);
        lesson.setEcosystemId(1L);
        lesson.setTitle("Bosque Andino");
    }

    @Test
    void completeLesson_Success_WhenNoExistingProgress() {
        when(childProfilePort.findByUserId(2L)).thenReturn(Optional.of(childProfile));
        when(lessonPort.findById(5L)).thenReturn(Optional.of(lesson));
        when(progressPort.findByChildIdAndLessonId(10L, 5L)).thenReturn(Optional.empty());
        when(progressPort.save(any(Progress.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Progress result = lessonProgressService.completeLesson(2L, 5L);

        assertNotNull(result);
        assertEquals(ProgressStatus.COMPLETED, result.getStatus());
        assertEquals(100, result.getProgressPercentage());
        assertEquals(10L, result.getChildId());
        assertEquals(5L, result.getLessonId());
        assertNotNull(result.getCompletedAt());
        verify(progressPort, times(1)).save(any(Progress.class));
    }

    @Test
    void completeLesson_Success_WhenExistingProgress() {
        Progress existing = new Progress();
        existing.setId(1L);
        existing.setChildId(10L);
        existing.setLessonId(5L);
        existing.setStatus(ProgressStatus.IN_PROGRESS);

        when(childProfilePort.findByUserId(2L)).thenReturn(Optional.of(childProfile));
        when(lessonPort.findById(5L)).thenReturn(Optional.of(lesson));
        when(progressPort.findByChildIdAndLessonId(10L, 5L)).thenReturn(Optional.of(existing));
        when(progressPort.save(any(Progress.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Progress result = lessonProgressService.completeLesson(2L, 5L);

        assertNotNull(result);
        assertEquals(ProgressStatus.COMPLETED, result.getStatus());
        assertEquals(100, result.getProgressPercentage());
        assertEquals(1L, result.getId());
    }

    @Test
    void completeLesson_ThrowsException_WhenChildNotFound() {
        when(childProfilePort.findByUserId(99L)).thenReturn(Optional.empty());

        assertThrows(ChildProfileNotFoundException.class, () -> lessonProgressService.completeLesson(99L, 5L));
    }

    @Test
    void completeLesson_ThrowsException_WhenLessonNotFound() {
        when(childProfilePort.findByUserId(2L)).thenReturn(Optional.of(childProfile));
        when(lessonPort.findById(99L)).thenReturn(Optional.empty());

        assertThrows(DomainException.class, () -> lessonProgressService.completeLesson(2L, 99L));
    }

    @Test
    void getCompletedLessonIdsByUserId_ReturnsOnlyCompletedIds() {
        Progress p1 = new Progress(1L, 10L, 5L, ProgressStatus.COMPLETED, 100, null, null);
        Progress p2 = new Progress(2L, 10L, 6L, ProgressStatus.IN_PROGRESS, 50, null, null);
        Progress p3 = new Progress(3L, 10L, 7L, ProgressStatus.COMPLETED, 100, null, null);

        when(childProfilePort.findByUserId(2L)).thenReturn(Optional.of(childProfile));
        when(progressPort.findByChildId(10L)).thenReturn(List.of(p1, p2, p3));

        List<Long> completedIds = lessonProgressService.getCompletedLessonIdsByUserId(2L);

        assertEquals(2, completedIds.size());
        assertTrue(completedIds.contains(5L));
        assertTrue(completedIds.contains(7L));
        assertFalse(completedIds.contains(6L));
    }
}
