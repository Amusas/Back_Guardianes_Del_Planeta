package com.guardianesdelplaneta.application.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.guardianesdelplaneta.domain.model.Activity;
import org.springframework.stereotype.Component;

@Component
public class ActivityEvaluator {
    private final ObjectMapper mapper;

    public ActivityEvaluator(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    public boolean evaluate(Activity activity, JsonNode response) {
        try {
            JsonNode config = mapper.readTree(activity.getConfigurationJson());
            return switch (activity.getType()) {
                case MULTIPLE_CHOICE, TRUE_FALSE, IDENTIFICATION -> {
                    if (config.has("correctAnswer") && response.has("answer")) {
                        yield config.get("correctAnswer").equals(response.get("answer"));
                    }
                    if (config.has("correctOption") && response.has("answer")) {
                        yield config.get("correctOption").equals(response.get("answer"));
                    }
                    if (config.has("correctOption") && response.has("option")) {
                        yield config.get("correctOption").equals(response.get("option"));
                    }
                    yield false;
                }
                case CLASSIFICATION, ORDERING, DRAG_AND_DROP -> {
                    if (config.has("correctSequence") && response.has("sequence")) {
                        yield config.get("correctSequence").equals(response.get("sequence"));
                    }
                    if (config.has("correctAnswers") && response.has("answers")) {
                        yield config.get("correctAnswers").equals(response.get("answers"));
                    }
                    yield false;
                }
                default -> false;
            };
        } catch (JsonProcessingException e) {
            return false;
        }
    }
}
