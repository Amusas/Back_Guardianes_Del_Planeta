package com.guardianesdelplaneta.infrastructure.adapter.in.rest.exception;

import com.guardianesdelplaneta.domain.exception.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class GlobalExceptionHandlerTest {

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new TestController())
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    @Test
    void shouldHandleUserAlreadyExists() throws Exception {
        mockMvc.perform(get("/test/user-already-exists"))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.statusCode").value(409))
                .andExpect(jsonPath("$.error").value("Conflict"))
                .andExpect(jsonPath("$.message").value("El usuario 'user_test' ya se encuentra registrado en el sistema."))
                .andExpect(jsonPath("$.path").value("/test/user-already-exists"));
    }

    @Test
    void shouldHandleInvalidCredentials() throws Exception {
        mockMvc.perform(get("/test/invalid-credentials"))
                .andDo(org.springframework.test.web.servlet.result.MockMvcResultHandlers.print())
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.statusCode").value(401))
                .andExpect(jsonPath("$.error").value("Unauthorized"))
                .andExpect(jsonPath("$.message").value("Credenciales inválidas. Por favor verifique el usuario y la contraseña."))
                .andExpect(jsonPath("$.path").value("/test/invalid-credentials"));
    }

    @Test
    void shouldHandleUserBlocked() throws Exception {
        mockMvc.perform(get("/test/user-blocked"))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.statusCode").value(403))
                .andExpect(jsonPath("$.error").value("Forbidden"))
                .andExpect(jsonPath("$.message").value("La cuenta del usuario 'user_test' se encuentra bloqueada o inactiva."))
                .andExpect(jsonPath("$.path").value("/test/user-blocked"));
    }

    @Test
    void shouldHandleNotFound() throws Exception {
        mockMvc.perform(get("/test/ecosystem-not-found"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.statusCode").value(404))
                .andExpect(jsonPath("$.error").value("Not Found"))
                .andExpect(jsonPath("$.message").value("Ecosystem missing"))
                .andExpect(jsonPath("$.path").value("/test/ecosystem-not-found"));
    }

    @Test
    void shouldHandleForbiddenAccess() throws Exception {
        mockMvc.perform(get("/test/forbidden-access"))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.statusCode").value(403))
                .andExpect(jsonPath("$.error").value("Forbidden"))
                .andExpect(jsonPath("$.message").value("Forbidden access"))
                .andExpect(jsonPath("$.path").value("/test/forbidden-access"));
    }

    @Test
    void shouldHandleGenericDomainException() throws Exception {
        mockMvc.perform(get("/test/generic-domain"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.statusCode").value(400))
                .andExpect(jsonPath("$.error").value("Bad Request"))
                .andExpect(jsonPath("$.message").value("Domain error"))
                .andExpect(jsonPath("$.path").value("/test/generic-domain"));
    }

    @Test
    void shouldHandleGenericException() throws Exception {
        mockMvc.perform(get("/test/generic"))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.statusCode").value(500))
                .andExpect(jsonPath("$.error").value("Internal Server Error"))
                .andExpect(jsonPath("$.message").value("Ha ocurrido un error inesperado en el servidor."))
                .andExpect(jsonPath("$.path").value("/test/generic"));
    }

    @RestController
    private static class TestController {
        @GetMapping("/test/user-already-exists")
        public void throwUserAlreadyExists() {
            throw new UserAlreadyExistsException("user_test");
        }

        @GetMapping("/test/invalid-credentials")
        public void throwInvalidCredentials() {
            throw new InvalidCredentialsException();
        }

        @GetMapping("/test/user-blocked")
        public void throwUserBlocked() {
            throw new UserBlockedException("user_test");
        }

        @GetMapping("/test/ecosystem-not-found")
        public void throwEcosystemNotFound() {
            throw new EcosystemNotFoundException("Ecosystem missing");
        }

        @GetMapping("/test/forbidden-access")
        public void throwForbiddenAccess() {
            throw new ForbiddenAccessException("Forbidden access");
        }

        @GetMapping("/test/generic-domain")
        public void throwGenericDomain() {
            throw new DomainException("Domain error") {};
        }

        @GetMapping("/test/generic")
        public void throwGeneric() throws Exception {
            throw new Exception("Generic error");
        }
    }
}
