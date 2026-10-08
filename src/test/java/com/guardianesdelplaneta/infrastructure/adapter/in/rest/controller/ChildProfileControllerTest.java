package com.guardianesdelplaneta.infrastructure.adapter.in.rest.controller;

import com.guardianesdelplaneta.domain.model.ChildProfile;
import com.guardianesdelplaneta.domain.port.in.GetChildProfileUseCase;
import com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto.ChildProfileResponseDto;
import com.guardianesdelplaneta.infrastructure.adapter.in.rest.mapper.ChildProfileRestMapper;
import org.junit.jupiter.api.DisplayName;
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

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ChildProfileController.class)
@AutoConfigureMockMvc(addFilters = false)
class ChildProfileControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetChildProfileUseCase getChildProfileUseCase;

    @MockBean
    private ChildProfileRestMapper childProfileRestMapper;

    @MockBean
    private com.guardianesdelplaneta.infrastructure.security.jwt.JwtTokenProvider jwtTokenProvider;

    @Test
    @DisplayName("GET /api/v1/children/me debe retornar 200 y el perfil del niño")
    void getMyProfile_Success() throws Exception {
        SecurityContext securityContext = mock(SecurityContext.class);
        Authentication authentication = mock(Authentication.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getCredentials()).thenReturn(2L);
        SecurityContextHolder.setContext(securityContext);

        ChildProfile profile = new ChildProfile();
        ChildProfileResponseDto responseDto = new ChildProfileResponseDto(10L, 2L, "Juanito", java.time.LocalDate.now(), 5, 0, 0L, 0, java.time.LocalDateTime.now(), java.time.LocalDateTime.now());

        when(getChildProfileUseCase.getChildProfileByUserId(org.mockito.ArgumentMatchers.anyLong())).thenReturn(profile);
        when(childProfileRestMapper.toResponseDto(profile)).thenReturn(responseDto);

        mockMvc.perform(get("/api/v1/children/me")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(10))
                .andExpect(jsonPath("$.userId").value(2))
                .andExpect(jsonPath("$.name").value("Juanito"));
    }
}
