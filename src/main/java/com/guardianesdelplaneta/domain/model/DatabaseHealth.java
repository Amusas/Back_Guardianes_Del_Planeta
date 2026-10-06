package com.guardianesdelplaneta.domain.model;

import java.time.LocalDateTime;

public class DatabaseHealth {

    private final boolean connected;
    private final String databaseName;
    private final String databaseVersion;
    private final LocalDateTime checkedAt;
    private final String message;

    public DatabaseHealth(boolean connected, String databaseName, String databaseVersion, LocalDateTime checkedAt, String message) {
        this.connected = connected;
        this.databaseName = databaseName;
        this.databaseVersion = databaseVersion;
        this.checkedAt = checkedAt;
        this.message = message;
    }

    public static DatabaseHealth success(String databaseName, String databaseVersion) {
        return new DatabaseHealth(true, databaseName, databaseVersion, LocalDateTime.now(), "Conexión a MySQL exitosa.");
    }

    public static DatabaseHealth failure(String message) {
        return new DatabaseHealth(false, "Desconocida", "Desconocida", LocalDateTime.now(), message);
    }

    public boolean isConnected() {
        return connected;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public String getDatabaseVersion() {
        return databaseVersion;
    }

    public LocalDateTime getCheckedAt() {
        return checkedAt;
    }

    public String getMessage() {
        return message;
    }
}
