package com.guardianesdelplaneta.infrastructure.adapter.out.persistence;

import com.guardianesdelplaneta.domain.model.ChildProfile;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.entity.ChildProfileEntity;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.mapper.ChildProfilePersistenceMapper;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.repository.ChildProfileRepository;
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
class ChildProfilePersistenceAdapterTest {

    @Mock
    private ChildProfileRepository repository;

    @Mock
    private ChildProfilePersistenceMapper mapper;

    @InjectMocks
    private ChildProfilePersistenceAdapter adapter;

    @Test
    @DisplayName("findByUserId debe retornar perfil cuando existe y vacío cuando no")
    void findByUserId_HappyAndSadPath() {
        ChildProfileEntity entity = new ChildProfileEntity();
        ChildProfile domain = new ChildProfile();
        domain.setId(1L);
        domain.setUserId(5L);
        when(repository.findByUserId(5L)).thenReturn(Optional.of(entity));
        when(mapper.toDomain(entity)).thenReturn(domain);
        when(repository.findByUserId(99L)).thenReturn(Optional.empty());

        assertTrue(adapter.findByUserId(5L).isPresent());
        assertTrue(adapter.findByUserId(99L).isEmpty());
    }

    @Test
    @DisplayName("save debe persistir y retornar el perfil del niño")
    void save_Success() {
        ChildProfile domain = new ChildProfile();
        domain.setId(1L);
        domain.setUserId(5L);
        ChildProfileEntity entity = new ChildProfileEntity();
        when(mapper.toEntity(domain)).thenReturn(entity);
        when(repository.save(entity)).thenReturn(entity);
        when(mapper.toDomain(entity)).thenReturn(domain);

        ChildProfile result = adapter.save(domain);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }
}
