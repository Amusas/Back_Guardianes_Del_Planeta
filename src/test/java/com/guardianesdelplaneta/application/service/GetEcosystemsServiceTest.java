package com.guardianesdelplaneta.application.service;

import com.guardianesdelplaneta.domain.exception.EcosystemNotFoundException;
import com.guardianesdelplaneta.domain.model.Ecosystem;
import com.guardianesdelplaneta.domain.port.out.EcosystemPersistencePort;
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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetEcosystemsServiceTest {

    @Mock private EcosystemPersistencePort port;

    private GetEcosystemsService service;

    @BeforeEach
    void setUp() {
        service = new GetEcosystemsService(port);
    }

    @Test
    void testGetActiveEcosystems() {
        when(port.findActiveEcosystems()).thenReturn(Collections.singletonList(new Ecosystem()));
        List<Ecosystem> res = service.getActiveEcosystems();
        assertEquals(1, res.size());
    }

    @Test
    void testGetEcosystemById_Found() {
        when(port.findById(100L)).thenReturn(Optional.of(new Ecosystem()));
        assertNotNull(service.getEcosystemById(100L));
    }

    @Test
    void testGetEcosystemById_NotFound() {
        when(port.findById(100L)).thenReturn(Optional.empty());
        assertThrows(EcosystemNotFoundException.class, () -> service.getEcosystemById(100L));
    }

    @Test
    void testGetEcosystemByCode_Found() {
        when(port.findByCode("ECO1")).thenReturn(Optional.of(new Ecosystem()));
        assertNotNull(service.getEcosystemByCode("ECO1"));
    }

    @Test
    void testGetEcosystemByCode_NotFound() {
        when(port.findByCode("ECO1")).thenReturn(Optional.empty());
        assertThrows(EcosystemNotFoundException.class, () -> service.getEcosystemByCode("ECO1"));
    }
}
