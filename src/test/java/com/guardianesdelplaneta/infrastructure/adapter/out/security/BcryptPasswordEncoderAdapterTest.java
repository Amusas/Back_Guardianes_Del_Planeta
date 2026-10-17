package com.guardianesdelplaneta.infrastructure.adapter.out.security;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BcryptPasswordEncoderAdapterTest {

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private BcryptPasswordEncoderAdapter adapter;

    @Test
    @DisplayName("encode debe delegar al PasswordEncoder de Spring Security")
    void encode_Success() {
        when(passwordEncoder.encode("secret123")).thenReturn("$2a$10$hashed");

        String result = adapter.encode("secret123");

        assertEquals("$2a$10$hashed", result);
        verify(passwordEncoder).encode("secret123");
    }

    @Test
    @DisplayName("matches debe retornar true cuando coincide y false cuando no coincide")
    void matches_HappyAndSadPath() {
        when(passwordEncoder.matches("secret123", "$2a$10$hashed")).thenReturn(true);
        when(passwordEncoder.matches("wrong", "$2a$10$hashed")).thenReturn(false);

        assertTrue(adapter.matches("secret123", "$2a$10$hashed"));
        assertFalse(adapter.matches("wrong", "$2a$10$hashed"));
    }
}
