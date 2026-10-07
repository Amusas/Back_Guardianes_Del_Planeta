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
                case MULTIPLE_CHOICE, TRUE_FALSE, IDENTIFICATION -> 
                    config.has("correctAnswer") && config.get("correctAnswer").equals(response.get("answer"));
                case CLASSIFICATION, ORDERING, DRAG_AND_DROP -> 
                    config.has("correctSequence") && config.get("correctSequence").equals(response.get("sequence"));
                default -> false;
            };
        } catch (JsonProcessingException e) {
            return false;
        }
    }
}
