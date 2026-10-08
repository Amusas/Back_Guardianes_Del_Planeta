package com.guardianesdelplaneta.infrastructure.adapter.in.rest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.guardianesdelplaneta.domain.port.in.SubmitActivityAttemptUseCase;
import com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto.ActivityAttemptRequestDto;
import com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto.ActivityAttemptResponseDto;
import com.guardianesdelplaneta.infrastructure.security.jwt.JwtTokenProvider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.authentication;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ActivityAttemptController.class)
@AutoConfigureMockMvc(addFilters = false)
class ActivityAttemptControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private SubmitActivityAttemptUseCase submitActivityAttemptUseCase;

    @MockBean
    private JwtTokenProvider jwtTokenProvider;

    @Test
    void submitAttempt_ReturnsOk() throws Exception {
        Long activityId = 1L;
        Long userId = 100L;

        ActivityAttemptRequestDto requestDto = new ActivityAttemptRequestDto(
                JsonNodeFactory.instance.objectNode().put("answer", "Option A")
        );

        ActivityAttemptResponseDto responseDto = new ActivityAttemptResponseDto(
                true, 1, 50, 1500, 5L, false, 5L, true, false, false, 3, List.of("Respuesta correcta"), List.of()
        );

        when(submitActivityAttemptUseCase.submitAttempt(eq(userId), eq(activityId), any(ActivityAttemptRequestDto.class)))
                .thenReturn(responseDto);

        Authentication auth = new UsernamePasswordAuthenticationToken("user", userId);

        mockMvc.perform(post("/api/v1/activities/{activityId}/attempts", activityId)
                        .principal(auth)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.isCorrect").value(true))
                .andExpect(jsonPath("$.xpAwarded").value(50));
    }
}
