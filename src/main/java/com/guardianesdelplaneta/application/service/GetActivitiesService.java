package com.guardianesdelplaneta.application.service;

import com.guardianesdelplaneta.domain.exception.LessonNotFoundException;
import com.guardianesdelplaneta.domain.model.Activity;
import com.guardianesdelplaneta.domain.port.in.GetActivitiesUseCase;
import com.guardianesdelplaneta.domain.port.out.ActivityPersistencePort;
import com.guardianesdelplaneta.domain.port.out.LessonPersistencePort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class GetActivitiesService implements GetActivitiesUseCase {

    private static final Logger log = LoggerFactory.getLogger(GetActivitiesService.class);

    private final ActivityPersistencePort activityPersistencePort;
    private final LessonPersistencePort lessonPersistencePort;

    public GetActivitiesService(ActivityPersistencePort activityPersistencePort,
                                LessonPersistencePort lessonPersistencePort) {
        this.activityPersistencePort = activityPersistencePort;
        this.lessonPersistencePort = lessonPersistencePort;
    }

    @Override
    public List<Activity> getActivitiesByLessonId(Long lessonId) {
        log.info("Consultando actividades para la lección con id: {}", lessonId);

        // Validar que la lección exista
        lessonPersistencePort.findById(lessonId)
                .orElseThrow(() -> new LessonNotFoundException("No se encontró la lección con id: " + lessonId));

        List<Activity> activities = activityPersistencePort.findByLessonIdOrderByOrderIndexAsc(lessonId);
        log.info("Se encontraron {} actividades para la lección id: {}", activities.size(), lessonId);
        return activities;
    }
}
