package com.guardianesdelplaneta.infrastructure.adapter.in.rest.controller;

import com.guardianesdelplaneta.domain.port.in.GetAchievementsUseCase;
import com.guardianesdelplaneta.infrastructure.adapter.in.rest.mapper.AchievementRestMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = AchievementController.class)
@AutoConfigureMockMvc(addFilters = false)
class AchievementControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private com.guardianesdelplaneta.infrastructure.security.jwt.JwtAuthenticationFilter jwtAuthenticationFilter;

    @MockBean
    private GetAchievementsUseCase getAchievementsUseCase;

    @MockBean
    private AchievementRestMapper achievementRestMapper;

    @BeforeEach
    void setUp() {
        Authentication auth = mock(Authentication.class);
        when(auth.getName()).thenReturn("1");
        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(auth);
        SecurityContextHolder.setContext(securityContext);
    }

    @AfterEach
    void tearDown() {
        SecurityContextHolder.clearContext();
    }

    @Test
    void getAllAchievements_ShouldReturnOk() throws Exception {
        when(getAchievementsUseCase.getAllActiveAchievements()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/v1/achievements"))
                .andExpect(status().isOk());

        verify(getAchievementsUseCase).getAllActiveAchievements();
    }

    @Test
    void getMyAchievements_ShouldReturnOk() throws Exception {
        when(getAchievementsUseCase.getUnlockedAchievements(1L)).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/v1/children/me/achievements"))
                .andExpect(status().isOk());

        verify(getAchievementsUseCase).getUnlockedAchievements(1L);
    }

    @Test
    void getMyAchievementStatus_ShouldReturnOk() throws Exception {
        when(getAchievementsUseCase.getAchievementStatus(eq(1L), eq(2L))).thenReturn(null);
        when(achievementRestMapper.toResponseDto(any(com.guardianesdelplaneta.domain.model.ChildAchievement.class))).thenReturn(null);

        mockMvc.perform(get("/api/v1/children/me/achievements/2"))
                .andExpect(status().isOk());

        verify(getAchievementsUseCase).getAchievementStatus(1L, 2L);
    }
}
