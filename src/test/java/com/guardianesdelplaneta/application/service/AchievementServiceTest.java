package com.guardianesdelplaneta.application.service;

import com.guardianesdelplaneta.domain.model.Achievement;
import com.guardianesdelplaneta.domain.model.ChildAchievement;
import com.guardianesdelplaneta.domain.model.ChildProfile;
import com.guardianesdelplaneta.domain.port.out.AchievementPersistencePort;
import com.guardianesdelplaneta.domain.port.out.ChildAchievementPersistencePort;
import com.guardianesdelplaneta.domain.port.out.ChildProfilePersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AchievementServiceTest {

    @Mock private AchievementPersistencePort achievementPort;
    @Mock private ChildAchievementPersistencePort childAchievementPort;
    @Mock private ChildProfilePersistencePort childProfilePort;

    private AchievementService service;

    @BeforeEach
    void setUp() {
        service = new AchievementService(achievementPort, childAchievementPort, childProfilePort);
    }

    @Test
    void testGetAllActiveAchievements() {
        when(achievementPort.findAllActive()).thenReturn(Collections.singletonList(new Achievement()));
        List<Achievement> res = service.getAllActiveAchievements();
        assertEquals(1, res.size());
    }

    @Test
    void testGetUnlockedAchievements() {
        ChildProfile c = new ChildProfile();
        c.setId(10L);
        when(childProfilePort.findByUserId(100L)).thenReturn(Optional.of(c));
        
        ChildAchievement ca = new ChildAchievement();
        ca.setAchievementId(1L);
        when(childAchievementPort.findByChildId(10L)).thenReturn(Collections.singletonList(ca));
        
        when(achievementPort.findById(1L)).thenReturn(Optional.of(new Achievement()));
        
        List<Achievement> res = service.getUnlockedAchievements(100L);
        assertEquals(1, res.size());
    }

    @Test
    void testGetAchievementStatus() {
        ChildProfile c = new ChildProfile();
        c.setId(10L);
        when(childProfilePort.findByUserId(100L)).thenReturn(Optional.of(c));
        
        when(childAchievementPort.findByChildIdAndAchievementId(10L, 1L)).thenReturn(Optional.of(new ChildAchievement()));
        
        ChildAchievement res = service.getAchievementStatus(100L, 1L);
        assertNotNull(res);
    }
}
