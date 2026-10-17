package com.guardianesdelplaneta.infrastructure.adapter.in.rest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.guardianesdelplaneta.domain.model.ChildProfile;
import com.guardianesdelplaneta.domain.port.in.GetGuardianChildDashboardUseCase;
import com.guardianesdelplaneta.domain.port.in.GetGuardianChildrenUseCase;
import com.guardianesdelplaneta.domain.port.in.RegisterChildForGuardianUseCase;
import com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto.ChildProfileResponseDto;
import com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto.GuardianChildDashboardDto;
import com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto.RegisterChildForGuardianRequestDto;
import com.guardianesdelplaneta.infrastructure.adapter.in.rest.mapper.ChildProfileRestMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = GuardianController.class)
@AutoConfigureMockMvc(addFilters = false)
class GuardianControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private com.guardianesdelplaneta.infrastructure.security.jwt.JwtAuthenticationFilter jwtAuthenticationFilter;

    @MockBean
    private GetGuardianChildrenUseCase getGuardianChildrenUseCase;

    @MockBean
    private RegisterChildForGuardianUseCase registerChildForGuardianUseCase;

    @MockBean
    private GetGuardianChildDashboardUseCase getGuardianChildDashboardUseCase;

    @MockBean
    private ChildProfileRestMapper childProfileRestMapper;

    @MockBean
    private com.guardianesdelplaneta.infrastructure.adapter.out.persistence.repository.UserRepository userRepository;

    @BeforeEach
    void setUp() {
        Authentication auth = mock(Authentication.class);
        when(auth.getCredentials()).thenReturn(1L);
        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(auth);
        SecurityContextHolder.setContext(securityContext);
    }

    @AfterEach
    void tearDown() {
        SecurityContextHolder.clearContext();
    }

    @Test
    void getMyChildren_ShouldReturnOk() throws Exception {
        when(getGuardianChildrenUseCase.getChildrenByGuardianId(1L)).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/v1/guardians/me/children"))
                .andExpect(status().isOk());

        verify(getGuardianChildrenUseCase).getChildrenByGuardianId(1L);
    }

    @Test
    void registerChild_ShouldReturnCreated() throws Exception {
        RegisterChildForGuardianRequestDto request = new RegisterChildForGuardianRequestDto(
                "juanito123",
                "password123",
                "Juanito",
                LocalDate.of(2016, 5, 10)
        );

        ChildProfile profileMock = mock(ChildProfile.class);
        ChildProfileResponseDto dtoMock = new ChildProfileResponseDto(
                1L, 2L, "Juanito", LocalDate.of(2016, 5, 10), 8, 0, 1L, 0, null, null
        );

        when(registerChildForGuardianUseCase.registerChildForGuardian(
                eq(1L), eq("juanito123"), eq("password123"), eq("Juanito"), eq(LocalDate.of(2016, 5, 10))
        )).thenReturn(profileMock);
        when(childProfileRestMapper.toResponseDto(profileMock)).thenReturn(dtoMock);

        mockMvc.perform(post("/api/v1/guardians/me/children")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Juanito"));

        verify(registerChildForGuardianUseCase).registerChildForGuardian(
                eq(1L), eq("juanito123"), eq("password123"), eq("Juanito"), eq(LocalDate.of(2016, 5, 10))
        );
    }

    @Test
    void getChildDashboard_ShouldReturnOk() throws Exception {
        GuardianChildDashboardDto dashboardDto = new GuardianChildDashboardDto(
                10L,
                "Juanito",
                "juanito123",
                LocalDate.of(2016, 5, 10),
                8,
                500,
                2,
                "Explorador del Agua",
                500,
                1000,
                "Guardián de los Mares",
                0.0,
                3,
                10,
                15,
                12,
                80.0,
                3,
                2,
                6,
                List.of(),
                List.of(),
                List.of(),
                List.of()
        );

        when(getGuardianChildDashboardUseCase.getChildDashboard(1L, 10L)).thenReturn(dashboardDto);

        mockMvc.perform(get("/api/v1/guardians/me/children/10/dashboard"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Juanito"))
                .andExpect(jsonPath("$.accuracyPercentage").value(80.0));

        verify(getGuardianChildDashboardUseCase).getChildDashboard(1L, 10L);
    }
}
