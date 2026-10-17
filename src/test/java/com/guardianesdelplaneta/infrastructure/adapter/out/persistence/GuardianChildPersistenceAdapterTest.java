package com.guardianesdelplaneta.infrastructure.adapter.out.persistence;

import com.guardianesdelplaneta.domain.model.ChildProfile;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.entity.ChildProfileEntity;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.entity.GuardianChildEntity;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.mapper.ChildProfilePersistenceMapper;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.repository.GuardianChildRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GuardianChildPersistenceAdapterTest {

    @Mock
    private GuardianChildRepository repository;

    @Mock
    private ChildProfilePersistenceMapper childMapper;

    @InjectMocks
    private GuardianChildPersistenceAdapter adapter;

    @Test
    @DisplayName("findChildrenByGuardianId debe retornar perfiles de niños asociados al acudiente")
    void findChildrenByGuardianId_Success() {
        ChildProfileEntity childEntity = new ChildProfileEntity();
        GuardianChildEntity gcEntity = new GuardianChildEntity();
        gcEntity.setChild(childEntity);
        ChildProfile domain = new ChildProfile();
        domain.setId(10L);
        domain.setName("Mateo");

        when(repository.findByGuardianId(1L)).thenReturn(List.of(gcEntity));
        when(childMapper.toDomain(childEntity)).thenReturn(domain);

        List<ChildProfile> result = adapter.findChildrenByGuardianId(1L);

        assertEquals(1, result.size());
        assertEquals("Mateo", result.get(0).getName());
    }
}
