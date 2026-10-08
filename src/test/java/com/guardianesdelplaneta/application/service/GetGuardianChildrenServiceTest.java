package com.guardianesdelplaneta.application.service;

import com.guardianesdelplaneta.domain.model.ChildProfile;
import com.guardianesdelplaneta.domain.port.out.GuardianChildPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetGuardianChildrenServiceTest {

    @Mock private GuardianChildPersistencePort port;

    private GetGuardianChildrenService service;

    @BeforeEach
    void setUp() {
        service = new GetGuardianChildrenService(port);
    }

    @Test
    void testGetChildrenByGuardianId() {
        when(port.findChildrenByGuardianId(1L)).thenReturn(Collections.singletonList(new ChildProfile()));
        List<ChildProfile> res = service.getChildrenByGuardianId(1L);
        assertEquals(1, res.size());
    }
}
