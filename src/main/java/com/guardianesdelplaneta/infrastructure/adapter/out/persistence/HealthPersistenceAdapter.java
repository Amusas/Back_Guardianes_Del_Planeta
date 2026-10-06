package com.guardianesdelplaneta.infrastructure.adapter.out.persistence;

import com.guardianesdelplaneta.domain.model.DatabaseHealth;
import com.guardianesdelplaneta.domain.port.out.DatabaseHealthPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

@Component
public class HealthPersistenceAdapter implements DatabaseHealthPort {

    private static final Logger log = LoggerFactory.getLogger(HealthPersistenceAdapter.class);

    private final DataSource dataSource;

    public HealthPersistenceAdapter(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public DatabaseHealth pingDatabase() {
        log.info("Probando conectividad a la base de datos MySQL...");
        try (Connection connection = dataSource.getConnection()) {
            if (connection.isValid(3)) {
                DatabaseMetaData metaData = connection.getMetaData();
                String databaseName = metaData.getDatabaseProductName();
                String databaseVersion = metaData.getDatabaseProductVersion();
                log.info("Conexión exitosa a: {} versión: {}", databaseName, databaseVersion);
                return DatabaseHealth.success(databaseName, databaseVersion);
            } else {
                log.warn("La conexión a MySQL no es válida o agotó el tiempo de espera.");
                return DatabaseHealth.failure("La conexión no fue válida en el tiempo esperado.");
            }
        } catch (SQLException e) {
            log.error("Error al conectar con la base de datos MySQL: {}", e.getMessage(), e);
            return DatabaseHealth.failure("Error de conexión a MySQL: " + e.getMessage());
        }
    }
}
