package com.guardianesdelplaneta.infrastructure.adapter.out.persistence;

import com.guardianesdelplaneta.domain.model.User;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.entity.UserEntity;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.mapper.UserPersistenceMapper;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserPersistenceAdapterTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserPersistenceMapper userPersistenceMapper;

    @InjectMocks
    private UserPersistenceAdapter adapter;

    @Test
    @DisplayName("save debe persistir y retornar el usuario")
    void save_Success() {
        User domain = new User();
        domain.setId(1L);
        domain.setUsername("guardian1");
        UserEntity entity = new UserEntity();
        when(userPersistenceMapper.toEntity(domain)).thenReturn(entity);
        when(userRepository.save(entity)).thenReturn(entity);
        when(userPersistenceMapper.toDomain(entity)).thenReturn(domain);

        User saved = adapter.save(domain);

        assertNotNull(saved);
        assertEquals("guardian1", saved.getUsername());
    }

    @Test
    @DisplayName("findByUsername debe retornar usuario cuando existe y vacío cuando no")
    void findByUsername_HappyAndSadPath() {
        UserEntity entity = new UserEntity();
        User domain = new User();
        domain.setId(1L);
        domain.setUsername("guardian1");
        when(userRepository.findByUsername("guardian1")).thenReturn(Optional.of(entity));
        when(userPersistenceMapper.toDomain(entity)).thenReturn(domain);
        when(userRepository.findByUsername("unknown")).thenReturn(Optional.empty());

        assertTrue(adapter.findByUsername("guardian1").isPresent());
        assertTrue(adapter.findByUsername("unknown").isEmpty());
    }

    @Test
    @DisplayName("existsByUsername debe retornar true o false")
    void existsByUsername_Success() {
        when(userRepository.existsByUsername("guardian1")).thenReturn(true);
        when(userRepository.existsByUsername("unknown")).thenReturn(false);

        assertTrue(adapter.existsByUsername("guardian1"));
        assertFalse(adapter.existsByUsername("unknown"));
    }
}
