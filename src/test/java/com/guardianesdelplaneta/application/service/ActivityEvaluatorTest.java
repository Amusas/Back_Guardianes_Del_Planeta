package com.guardianesdelplaneta.application.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.guardianesdelplaneta.domain.model.Activity;
import com.guardianesdelplaneta.domain.model.enums.ActivityType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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
    @DisplayName("MULTIPLE_CHOICE evalúa correctAnswer, correctOption+answer y correctOption+option")
    void testMultipleChoice_AllBranches() {
        Activity activity = new Activity();
        activity.setType(ActivityType.MULTIPLE_CHOICE);
        activity.setConfigurationJson("{\"correctAnswer\":\"A\"}");

        ObjectNode response = mapper.createObjectNode();
        response.put("answer", "A");
        assertTrue(evaluator.evaluate(activity, response));

        // correctOption + answer
        activity.setConfigurationJson("{\"correctOption\":1}");
        ObjectNode respOptAns = mapper.createObjectNode();
        respOptAns.put("answer", 1);
        assertTrue(evaluator.evaluate(activity, respOptAns));

        // correctOption + option
        ObjectNode respOpt = mapper.createObjectNode();
        respOpt.put("option", 1);
        assertTrue(evaluator.evaluate(activity, respOpt));

        // Missing answer/option -> false
        ObjectNode emptyResp = mapper.createObjectNode();
        assertFalse(evaluator.evaluate(activity, emptyResp));
    }

    @Test
    @DisplayName("MULTIPLE_CHOICE retorna false cuando la respuesta es incorrecta")
    void testMultipleChoice_Incorrect() {
        Activity activity = new Activity();
        activity.setType(ActivityType.MULTIPLE_CHOICE);
        activity.setConfigurationJson("{\"correctAnswer\":\"A\"}");

        ObjectNode response = mapper.createObjectNode();
        response.put("answer", "B");

        assertFalse(evaluator.evaluate(activity, response));
    }

    @Test
    @DisplayName("ORDERING, CLASSIFICATION y DRAG_AND_DROP evalúan correctSequence y correctAnswers")
    void testOrderingAndClassification_AllBranches() {
        Activity activity = new Activity();
        activity.setType(ActivityType.ORDERING);
        activity.setConfigurationJson("{\"correctSequence\":[1,2,3]}");

        ObjectNode response = mapper.createObjectNode();
        response.putArray("sequence").add(1).add(2).add(3);
        assertTrue(evaluator.evaluate(activity, response));

        // Classification con correctAnswers + answers
        Activity classAct = new Activity();
        classAct.setType(ActivityType.CLASSIFICATION);
        classAct.setConfigurationJson("{\"correctAnswers\":{\"oso\":\"bosque\"}}");
        ObjectNode classResp = mapper.createObjectNode();
        classResp.putObject("answers").put("oso", "bosque");
        assertTrue(evaluator.evaluate(classAct, classResp));

        // Sin sequence ni answers -> false
        assertFalse(evaluator.evaluate(classAct, mapper.createObjectNode()));
    }

    @Test
    @DisplayName("ORDERING retorna false con secuencia incorrecta")
    void testOrdering_Incorrect() {
        Activity activity = new Activity();
        activity.setType(ActivityType.ORDERING);
        activity.setConfigurationJson("{\"correctSequence\":[1,2,3]}");

        ObjectNode response = mapper.createObjectNode();
        response.putArray("sequence").add(1).add(3).add(2);

        assertFalse(evaluator.evaluate(activity, response));
    }

    @Test
    @DisplayName("JSON inválido retorna false de forma segura (Camino Triste)")
    void testInvalidJson() {
        Activity activity = new Activity();
        activity.setType(ActivityType.MULTIPLE_CHOICE);
        activity.setConfigurationJson("invalid json");

        ObjectNode response = mapper.createObjectNode();
        response.put("answer", "A");

        assertFalse(evaluator.evaluate(activity, response));
    }
}
