package com.guardianesdelplaneta.application.service;

import com.guardianesdelplaneta.domain.exception.LessonNotFoundException;
import com.guardianesdelplaneta.domain.model.Lesson;
import com.guardianesdelplaneta.domain.port.in.GetLessonsUseCase;
import com.guardianesdelplaneta.domain.port.out.LessonPersistencePort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GetLessonsService implements GetLessonsUseCase {
    private static final Logger log = LoggerFactory.getLogger(GetLessonsService.class);
    private final LessonPersistencePort lessonPersistencePort;

    public GetLessonsService(LessonPersistencePort lessonPersistencePort) {
        this.lessonPersistencePort = lessonPersistencePort;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Lesson> getActiveLessonsByEcosystemId(Long ecosystemId) {
        log.info("Fetching active lessons for ecosystem id: {}", ecosystemId);
        return lessonPersistencePort.findActiveLessonsByEcosystemId(ecosystemId);
    }

    @Override
    @Transactional(readOnly = true)
    public Lesson getLessonById(Long id) {
        log.info("Fetching lesson by id: {}", id);
        return lessonPersistencePort.findById(id)
                .orElseThrow(() -> new LessonNotFoundException("Lesson with id " + id + " not found"));
    }
}
