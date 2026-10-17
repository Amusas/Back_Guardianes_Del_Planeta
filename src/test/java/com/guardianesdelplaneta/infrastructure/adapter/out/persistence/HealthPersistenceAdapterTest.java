package com.guardianesdelplaneta.infrastructure.adapter.out.persistence;

import com.guardianesdelplaneta.domain.model.DatabaseHealth;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HealthPersistenceAdapterTest {

    @Mock
    private DataSource dataSource;

    @Mock
    private Connection connection;

    @Mock
    private DatabaseMetaData metaData;

    @InjectMocks
    private HealthPersistenceAdapter adapter;

    @Test
    @DisplayName("pingDatabase debe retornar connected=true cuando la conexión es válida")
    void pingDatabase_ValidConnection() throws SQLException {
        when(dataSource.getConnection()).thenReturn(connection);
        when(connection.isValid(3)).thenReturn(true);
        when(connection.getMetaData()).thenReturn(metaData);
        when(metaData.getDatabaseProductName()).thenReturn("MySQL");
        when(metaData.getDatabaseProductVersion()).thenReturn("8.0.35");

        DatabaseHealth result = adapter.pingDatabase();

        assertTrue(result.isConnected());
        assertEquals("MySQL", result.getDatabaseName());
        assertEquals("8.0.35", result.getDatabaseVersion());
        assertNotNull(result.getCheckedAt());
    }

    @Test
    @DisplayName("pingDatabase debe retornar connected=false cuando la conexión no es válida")
    void pingDatabase_InvalidConnection() throws SQLException {
        when(dataSource.getConnection()).thenReturn(connection);
        when(connection.isValid(3)).thenReturn(false);

        DatabaseHealth result = adapter.pingDatabase();

        assertFalse(result.isConnected());
        assertNotNull(result.getMessage());
    }

    @Test
    @DisplayName("pingDatabase debe retornar connected=false cuando ocurre una SQLException")
    void pingDatabase_ThrowsSQLException() throws SQLException {
        when(dataSource.getConnection()).thenThrow(new SQLException("Connection refused"));

        DatabaseHealth result = adapter.pingDatabase();

        assertFalse(result.isConnected());
        assertTrue(result.getMessage().contains("Connection refused"));
    }
}
