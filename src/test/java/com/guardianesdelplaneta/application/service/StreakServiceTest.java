package com.guardianesdelplaneta.application.service;

import com.guardianesdelplaneta.domain.model.ChildProfile;
import com.guardianesdelplaneta.domain.model.Streak;
import com.guardianesdelplaneta.domain.port.out.ChildProfilePersistencePort;
import com.guardianesdelplaneta.domain.port.out.StreakPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StreakServiceTest {

    @Mock private StreakPersistencePort streakPort;
    @Mock private ChildProfilePersistencePort childProfilePort;

    private StreakService service;

    @BeforeEach
    void setUp() {
        service = new StreakService(streakPort, childProfilePort);
    }

    @Test
    void testGetCurrentStreak() {
        ChildProfile c = new ChildProfile();
        c.setId(10L);
        when(childProfilePort.findByUserId(100L)).thenReturn(Optional.of(c));
        when(streakPort.findByChildIdAndActivityDate(eq(10L), any())).thenReturn(Optional.of(new Streak()));
        
        Optional<Streak> res = service.getCurrentStreak(100L);
        assertTrue(res.isPresent());
    }

    @Test
    void testGetStreakHistory() {
        ChildProfile c = new ChildProfile();
        c.setId(10L);
        when(childProfilePort.findByUserId(100L)).thenReturn(Optional.of(c));
        when(streakPort.findByChildId(10L)).thenReturn(Collections.singletonList(new Streak()));
        
        List<Streak> res = service.getStreakHistory(100L);
        assertEquals(1, res.size());
    }
}
