package com.guardianesdelplaneta.infrastructure.adapter.in.rest.controller;

import com.guardianesdelplaneta.domain.port.in.GetGuardianChildrenUseCase;
import com.guardianesdelplaneta.infrastructure.adapter.in.rest.mapper.ChildProfileRestMapper;
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

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = GuardianController.class)
@AutoConfigureMockMvc(addFilters = false)
class GuardianControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private com.guardianesdelplaneta.infrastructure.security.jwt.JwtAuthenticationFilter jwtAuthenticationFilter;

    @MockBean
    private GetGuardianChildrenUseCase getGuardianChildrenUseCase;

    @MockBean
    private ChildProfileRestMapper childProfileRestMapper;

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
}
