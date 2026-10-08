package com.guardianesdelplaneta.application.service;

import com.guardianesdelplaneta.domain.exception.ChildProfileNotFoundException;
import com.guardianesdelplaneta.domain.model.ChildProfile;
import com.guardianesdelplaneta.domain.port.out.ChildProfilePersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetChildProfileServiceTest {

    @Mock private ChildProfilePersistencePort port;

    private GetChildProfileService service;

    @BeforeEach
    void setUp() {
        service = new GetChildProfileService(port);
    }

    @Test
    void testGetChildProfileByUserId_Found() {
        when(port.findByUserId(100L)).thenReturn(Optional.of(new ChildProfile()));
        assertNotNull(service.getChildProfileByUserId(100L));
    }

    @Test
    void testGetChildProfileByUserId_NotFound() {
        when(port.findByUserId(100L)).thenReturn(Optional.empty());
        assertThrows(ChildProfileNotFoundException.class, () -> service.getChildProfileByUserId(100L));
    }
}
