package com.guardianesdelplaneta.application.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.guardianesdelplaneta.domain.model.Activity;
import com.guardianesdelplaneta.domain.model.enums.ActivityType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ActivityEvaluatorTest {

    private ActivityEvaluator evaluator;
    private ObjectMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new ObjectMapper();
        evaluator = new ActivityEvaluator(mapper);
    }

    @Test
    void testMultipleChoice_Correct() {
        Activity activity = new Activity();
        activity.setType(ActivityType.MULTIPLE_CHOICE);
        activity.setConfigurationJson("{\"correctAnswer\":\"A\"}");

        ObjectNode response = mapper.createObjectNode();
        response.put("answer", "A");

        assertTrue(evaluator.evaluate(activity, response));
    }

    @Test
    void testMultipleChoice_Incorrect() {
        Activity activity = new Activity();
        activity.setType(ActivityType.MULTIPLE_CHOICE);
        activity.setConfigurationJson("{\"correctAnswer\":\"A\"}");

        ObjectNode response = mapper.createObjectNode();
        response.put("answer", "B");

        assertFalse(evaluator.evaluate(activity, response));
    }

    @Test
    void testOrdering_Correct() {
        Activity activity = new Activity();
        activity.setType(ActivityType.ORDERING);
        activity.setConfigurationJson("{\"correctSequence\":[1,2,3]}");

        ObjectNode response = mapper.createObjectNode();
        response.putArray("sequence").add(1).add(2).add(3);

        assertTrue(evaluator.evaluate(activity, response));
    }

    @Test
    void testOrdering_Incorrect() {
        Activity activity = new Activity();
        activity.setType(ActivityType.ORDERING);
        activity.setConfigurationJson("{\"correctSequence\":[1,2,3]}");

        ObjectNode response = mapper.createObjectNode();
        response.putArray("sequence").add(1).add(3).add(2);

        assertFalse(evaluator.evaluate(activity, response));
    }
    
    @Test
    void testInvalidJson() {
        Activity activity = new Activity();
        activity.setType(ActivityType.MULTIPLE_CHOICE);
        activity.setConfigurationJson("invalid json");

        ObjectNode response = mapper.createObjectNode();
        response.put("answer", "A");

        assertFalse(evaluator.evaluate(activity, response));
    }
}
