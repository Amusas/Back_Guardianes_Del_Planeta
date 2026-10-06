package com.guardianesdelplaneta.application.service;

import com.guardianesdelplaneta.domain.model.DatabaseHealth;
import com.guardianesdelplaneta.domain.port.out.DatabaseHealthPort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HealthCheckServiceTest {

    @Mock
    private DatabaseHealthPort databaseHealthPort;

    @InjectMocks
    private HealthCheckService healthCheckService;

    @Test
    @DisplayName("Debe retornar estado de salud exitoso cuando la BD está conectada")
    void shouldReturnHealthyWhenDatabaseIsConnected() {
        // Arrange
        DatabaseHealth expectedHealth = DatabaseHealth.success("MySQL", "8.0.36");
        when(databaseHealthPort.pingDatabase()).thenReturn(expectedHealth);

        // Act
        DatabaseHealth actualHealth = healthCheckService.checkHealth();

        // Assert
        assertTrue(actualHealth.isConnected());
        assertEquals("MySQL", actualHealth.getDatabaseName());
        assertEquals("8.0.36", actualHealth.getDatabaseVersion());
        verify(databaseHealthPort).pingDatabase();
    }
}
