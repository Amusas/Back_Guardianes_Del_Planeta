package com.guardianesdelplaneta.infrastructure.adapter.in.rest.controller;

import com.guardianesdelplaneta.domain.model.Ecosystem;
import com.guardianesdelplaneta.domain.port.in.GetEcosystemsUseCase;
import com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto.EcosystemResponseDto;
import com.guardianesdelplaneta.infrastructure.adapter.in.rest.mapper.EcosystemRestMapper;
import com.guardianesdelplaneta.infrastructure.security.jwt.JwtTokenProvider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EcosystemController.class)
@AutoConfigureMockMvc(addFilters = false)
class EcosystemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetEcosystemsUseCase getEcosystemsUseCase;

    @MockBean
    private EcosystemRestMapper ecosystemRestMapper;

    @MockBean
    private JwtTokenProvider jwtTokenProvider;

    @Test
    void getActiveEcosystems_ReturnsList() throws Exception {
        Ecosystem ecosystem = new Ecosystem(1L, "ECO1", "Ecosystem 1", "Desc", 1L, "key", true);
        EcosystemResponseDto dto = new EcosystemResponseDto(1L, "ECO1", "Ecosystem 1", "Desc", 1L, "key", true);

        when(getEcosystemsUseCase.getActiveEcosystems()).thenReturn(List.of(ecosystem));
        when(ecosystemRestMapper.toResponseDto(ecosystem)).thenReturn(dto);

        mockMvc.perform(get("/api/v1/ecosystems"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].code").value("ECO1"));
    }

    @Test
    void getEcosystemById_ReturnsDto() throws Exception {
        Ecosystem ecosystem = new Ecosystem(1L, "ECO1", "Ecosystem 1", "Desc", 1L, "key", true);
        EcosystemResponseDto dto = new EcosystemResponseDto(1L, "ECO1", "Ecosystem 1", "Desc", 1L, "key", true);

        when(getEcosystemsUseCase.getEcosystemById(1L)).thenReturn(ecosystem);
        when(ecosystemRestMapper.toResponseDto(ecosystem)).thenReturn(dto);

        mockMvc.perform(get("/api/v1/ecosystems/{ecosystemId}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.code").value("ECO1"));
    }

    @Test
    void getEcosystemByCode_ReturnsDto() throws Exception {
        Ecosystem ecosystem = new Ecosystem(1L, "ECO1", "Ecosystem 1", "Desc", 1L, "key", true);
        EcosystemResponseDto dto = new EcosystemResponseDto(1L, "ECO1", "Ecosystem 1", "Desc", 1L, "key", true);

        when(getEcosystemsUseCase.getEcosystemByCode("ECO1")).thenReturn(ecosystem);
        when(ecosystemRestMapper.toResponseDto(ecosystem)).thenReturn(dto);

        mockMvc.perform(get("/api/v1/ecosystems/code/{code}", "ECO1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.code").value("ECO1"));
    }
}
