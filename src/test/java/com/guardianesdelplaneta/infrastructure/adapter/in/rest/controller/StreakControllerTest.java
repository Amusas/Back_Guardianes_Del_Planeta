package com.guardianesdelplaneta.infrastructure.adapter.in.rest.controller;

import com.guardianesdelplaneta.domain.model.Streak;
import com.guardianesdelplaneta.domain.port.in.GetStreakUseCase;
import com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto.StreakResponseDto;
import com.guardianesdelplaneta.infrastructure.adapter.in.rest.mapper.StreakRestMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.guardianesdelplaneta.infrastructure.security.jwt.JwtTokenProvider;

@WebMvcTest(StreakController.class)
@AutoConfigureMockMvc(addFilters = false)
class StreakControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JwtTokenProvider jwtTokenProvider;

    @MockBean
    private GetStreakUseCase getStreakUseCase;

    @MockBean
    private StreakRestMapper streakRestMapper;

    @Test
    @WithMockUser(username = "1", roles = {"CHILD"})
    void getCurrentStreak_ReturnsStreak() throws Exception {
        Streak streakMock = mock(Streak.class);
        StreakResponseDto dtoMock = mock(StreakResponseDto.class);

        when(getStreakUseCase.getCurrentStreak(1L)).thenReturn(Optional.of(streakMock));
        when(streakRestMapper.toResponseDto(any())).thenReturn(dtoMock);

        mockMvc.perform(get("/api/v1/children/me/streak")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "1", roles = {"CHILD"})
    void getCurrentStreak_ReturnsNoContent_WhenStreakEmpty() throws Exception {
        when(getStreakUseCase.getCurrentStreak(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/v1/children/me/streak")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    @WithMockUser(username = "1", roles = {"CHILD"})
    void getStreakHistory_ReturnsHistory() throws Exception {
        Streak streakMock = mock(Streak.class);
        StreakResponseDto dtoMock = mock(StreakResponseDto.class);

        when(getStreakUseCase.getStreakHistory(1L)).thenReturn(List.of(streakMock));
        when(streakRestMapper.toResponseDto(any())).thenReturn(dtoMock);

        mockMvc.perform(get("/api/v1/children/me/streak/history")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }
}
