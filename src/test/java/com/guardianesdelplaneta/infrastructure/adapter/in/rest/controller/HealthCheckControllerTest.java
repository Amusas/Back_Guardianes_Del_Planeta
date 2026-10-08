package com.guardianesdelplaneta.infrastructure.adapter.in.rest.controller;

import com.guardianesdelplaneta.domain.model.DatabaseHealth;
import com.guardianesdelplaneta.domain.port.in.CheckHealthUseCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

@WebMvcTest(HealthCheckController.class)
@AutoConfigureMockMvc(addFilters = false)
class HealthCheckControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CheckHealthUseCase checkHealthUseCase;

    @MockBean
    private com.guardianesdelplaneta.infrastructure.security.jwt.JwtTokenProvider jwtTokenProvider;

    @Test
    void checkHealth_shouldReturnOk_whenDatabaseIsConnected() throws Exception {
        DatabaseHealth dbHealth = DatabaseHealth.success("MySQL", "8.0");
        when(checkHealthUseCase.checkHealth()).thenReturn(dbHealth);

        mockMvc.perform(get("/health"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.application").value("Guardianes del Planeta API"))
                .andExpect(jsonPath("$.status").value("UP"))
                .andExpect(jsonPath("$.databaseConnected").value(true))
                .andExpect(jsonPath("$.databaseEngine").value("MySQL"))
                .andExpect(jsonPath("$.databaseVersion").value("8.0"))
                .andExpect(jsonPath("$.message").value("Conexión a MySQL exitosa."))
                .andExpect(jsonPath("$.timestamp").exists());
    }

    @Test
    void checkHealth_shouldReturnServiceUnavailable_whenDatabaseIsDown() throws Exception {
        DatabaseHealth dbHealth = DatabaseHealth.failure("Connection failed");
        when(checkHealthUseCase.checkHealth()).thenReturn(dbHealth);

        mockMvc.perform(get("/health"))
                .andExpect(status().isServiceUnavailable())
                .andExpect(jsonPath("$.application").value("Guardianes del Planeta API"))
                .andExpect(jsonPath("$.status").value("DOWN"))
                .andExpect(jsonPath("$.databaseConnected").value(false))
                .andExpect(jsonPath("$.databaseEngine").value("Desconocida"))
                .andExpect(jsonPath("$.databaseVersion").value("Desconocida"))
                .andExpect(jsonPath("$.message").value("Connection failed"))
                .andExpect(jsonPath("$.timestamp").exists());
    }
}
