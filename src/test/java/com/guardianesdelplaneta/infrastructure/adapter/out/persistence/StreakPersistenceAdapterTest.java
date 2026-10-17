package com.guardianesdelplaneta.infrastructure.adapter.out.persistence;

import com.guardianesdelplaneta.domain.model.Streak;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.entity.StreakEntity;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.mapper.StreakPersistenceMapper;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.repository.StreakRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StreakPersistenceAdapterTest {

    @Mock
    private StreakRepository repository;

    @Mock
    private StreakPersistenceMapper mapper;

    @InjectMocks
    private StreakPersistenceAdapter adapter;

    @Test
    @DisplayName("findByChildIdAndActivityDate debe retornar racha cuando existe y vacío cuando no")
    void findByChildIdAndActivityDate_HappyAndSadPath() {
        LocalDate today = LocalDate.now();
        StreakEntity entity = new StreakEntity();
        Streak domain = new Streak();
        domain.setId(1L);
        domain.setChildId(10L);
        domain.setActivityDate(today);
        when(repository.findByChildIdAndActivityDate(10L, today)).thenReturn(Optional.of(entity));
        when(mapper.toDomain(entity)).thenReturn(domain);
        when(repository.findByChildIdAndActivityDate(99L, today)).thenReturn(Optional.empty());

        assertTrue(adapter.findByChildIdAndActivityDate(10L, today).isPresent());
        assertTrue(adapter.findByChildIdAndActivityDate(99L, today).isEmpty());
    }

    @Test
    @DisplayName("findByChildId debe retornar historial de rachas del niño")
    void findByChildId_Success() {
        StreakEntity entity = new StreakEntity();
        Streak domain = new Streak();
        domain.setId(1L);
        domain.setChildId(10L);
        when(repository.findByChildId(10L)).thenReturn(List.of(entity));
        when(mapper.toDomain(entity)).thenReturn(domain);

        List<Streak> result = adapter.findByChildId(10L);

        assertEquals(1, result.size());
        assertEquals(10L, result.get(0).getChildId());
    }

    @Test
    @DisplayName("save debe persistir y retornar la racha")
    void save_Success() {
        Streak domain = new Streak();
        domain.setId(1L);
        StreakEntity entity = new StreakEntity();
        when(mapper.toEntity(domain)).thenReturn(entity);
        when(repository.save(entity)).thenReturn(entity);
        when(mapper.toDomain(entity)).thenReturn(domain);

        Streak saved = adapter.save(domain);

        assertNotNull(saved);
        assertEquals(1L, saved.getId());
    }
}
