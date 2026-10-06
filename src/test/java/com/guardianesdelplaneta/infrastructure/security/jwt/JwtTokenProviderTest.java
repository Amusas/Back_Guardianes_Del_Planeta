package com.guardianesdelplaneta.infrastructure.security.jwt;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class JwtTokenProviderTest {

    private JwtTokenProvider jwtTokenProvider;
    private final String secretKey = "404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970";
    private final long expirationMs = 3600000; // 1 hora

    @BeforeEach
    void setUp() {
        jwtTokenProvider = new JwtTokenProvider(secretKey, expirationMs);
    }

    @Test
    @DisplayName("Debe generar un token válido y extraer el username correctamente")
    void shouldGenerateAndValidateToken() {
        String username = "guardian@planeta.org";

        String token = jwtTokenProvider.generateToken(username);

        assertNotNull(token);
        assertTrue(jwtTokenProvider.validateToken(token));
        assertEquals(username, jwtTokenProvider.getUsernameFromToken(token));
    }

    @Test
    @DisplayName("Debe fallar la validación si el token está manipulado")
    void shouldFailWhenTokenIsTampered() {
        String token = jwtTokenProvider.generateToken("guardian@planeta.org");
        String tamperedToken = token + "xyz";

        assertFalse(jwtTokenProvider.validateToken(tamperedToken));
    }
}
