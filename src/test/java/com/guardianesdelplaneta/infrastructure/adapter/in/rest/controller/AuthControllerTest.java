package com.guardianesdelplaneta.infrastructure.adapter.in.rest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.guardianesdelplaneta.domain.exception.InvalidCredentialsException;
import com.guardianesdelplaneta.domain.exception.UserBlockedException;
import com.guardianesdelplaneta.domain.model.User;
import com.guardianesdelplaneta.domain.model.enums.UserRole;
import com.guardianesdelplaneta.domain.model.enums.UserStatus;
import com.guardianesdelplaneta.domain.port.in.AuthenticateUserUseCase;
import com.guardianesdelplaneta.domain.port.in.CreateUserUseCase;
import com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto.UserLoginRequestDto;
import com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto.UserRegisterRequestDto;
import com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto.UserResponseDto;
import com.guardianesdelplaneta.infrastructure.adapter.in.rest.mapper.UserRestMapper;
import com.guardianesdelplaneta.infrastructure.security.jwt.JwtTokenProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = AuthController.class)
@AutoConfigureMockMvc(addFilters = false)
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CreateUserUseCase createUserUseCase;

    @MockBean
    private AuthenticateUserUseCase authenticateUserUseCase;

    @MockBean
    private UserRestMapper userRestMapper;

    @MockBean
    private JwtTokenProvider jwtTokenProvider;

    @Test
    @DisplayName("POST /auth/v1/user debe retornar 201 Created al registrar un usuario")
    void shouldCreateUserAndReturn201() throws Exception {
        UserRegisterRequestDto request = new UserRegisterRequestDto("guardian_test", "password123", UserRole.GUARDIAN);

        User domainUser = new User();
        domainUser.setId(1L);
        domainUser.setUsername("guardian_test");
        domainUser.setRole(UserRole.GUARDIAN);
        domainUser.setStatus(UserStatus.ACTIVE);

        UserResponseDto responseDto = new UserResponseDto(
                1L, "guardian_test", UserRole.GUARDIAN, UserStatus.ACTIVE, LocalDateTime.now()
        );

        when(userRestMapper.toDomain(any(UserRegisterRequestDto.class))).thenReturn(domainUser);
        when(createUserUseCase.createUser(any(User.class), eq("password123"))).thenReturn(domainUser);
        when(userRestMapper.toResponseDto(domainUser)).thenReturn(responseDto);

        mockMvc.perform(post("/auth/v1/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.username").value("guardian_test"))
                .andExpect(jsonPath("$.role").value("GUARDIAN"))
                .andExpect(jsonPath("$.status").value("ACTIVE"));
    }

    @Test
    @DisplayName("POST /auth/v1/login debe retornar 200 OK con el token JWT")
    void shouldLoginAndReturn200WithJwt() throws Exception {
        UserLoginRequestDto request = new UserLoginRequestDto("guardian_test", "password123");

        User user = new User();
        user.setId(5L);
        user.setUsername("guardian_test");
        user.setRole(UserRole.GUARDIAN);
        user.setStatus(UserStatus.ACTIVE);

        when(authenticateUserUseCase.authenticate("guardian_test", "password123")).thenReturn(user);
        when(jwtTokenProvider.generateToken(eq("guardian_test"), any())).thenReturn("mocked.jwt.token");
        when(jwtTokenProvider.getExpirationMs()).thenReturn(86400000L);

        mockMvc.perform(post("/auth/v1/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value("mocked.jwt.token"))
                .andExpect(jsonPath("$.tokenType").value("Bearer"))
                .andExpect(jsonPath("$.username").value("guardian_test"))
                .andExpect(jsonPath("$.role").value("GUARDIAN"))
                .andExpect(jsonPath("$.userId").value(5))
                .andExpect(jsonPath("$.expiresInMs").value(86400000));
    }

    @Test
    @DisplayName("POST /auth/v1/user debe retornar 400 Bad Request cuando la peticion no es valida")
    void shouldReturn400WhenValidationFails() throws Exception {
        UserRegisterRequestDto invalidRequest = new UserRegisterRequestDto("", "12", null);

        mockMvc.perform(post("/auth/v1/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidRequest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("POST /auth/v1/login debe retornar 400 Bad Request cuando el body es invalido")
    void loginShouldReturn400WhenValidationFails() throws Exception {
        UserLoginRequestDto invalidRequest = new UserLoginRequestDto("", "");

        mockMvc.perform(post("/auth/v1/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidRequest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("POST /auth/v1/login debe retornar 401 Unauthorized cuando las credenciales son invalidas")
    void loginShouldReturn401WhenInvalidCredentials() throws Exception {
        UserLoginRequestDto request = new UserLoginRequestDto("guardian_test", "wrongpass");

        when(authenticateUserUseCase.authenticate("guardian_test", "wrongpass"))
                .thenThrow(new InvalidCredentialsException());

        mockMvc.perform(post("/auth/v1/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @DisplayName("POST /auth/v1/login debe retornar 403 Forbidden cuando el usuario esta bloqueado")
    void loginShouldReturn403WhenUserBlocked() throws Exception {
        UserLoginRequestDto request = new UserLoginRequestDto("blocked_user", "password123");

        when(authenticateUserUseCase.authenticate("blocked_user", "password123"))
                .thenThrow(new UserBlockedException("blocked_user"));

        mockMvc.perform(post("/auth/v1/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isForbidden());
    }
}
