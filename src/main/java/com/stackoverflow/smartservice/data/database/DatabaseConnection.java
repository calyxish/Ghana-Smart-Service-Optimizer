package com.stackoverflow.smartservice.data.database;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Manages SQLite connections for the UG Campus Smart Service Optimizer.
 *
 * PLACEHOLDER NOTE (Role 3 / Prosper): Role 2 (Robert, Database Architect)
 * owns this file per Guide_Implementation.md. His branch
 * (feature/robert/database-schema) had not been pushed yet with any Java
 * code, so this minimal version was written here to unblock and test the
 * data loader end-to-end. It matches his spec exactly. Replace/reconcile
 * with Robert's real implementation when his branch is ready.
 */
public class DatabaseConnection {

    private static final String DB_URL = "jdbc:sqlite:database/ug_campus.db";
    private static final String SCHEMA_PATH = "database/schema.sql";

    static {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("SQLite JDBC driver not found on classpath (lib/sqlite-jdbc.jar)", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        Connection conn = DriverManager.getConnection(DB_URL);
        conn.createStatement().execute("PRAGMA foreign_keys = ON");
        return conn;
    }

    /**
     * Creates the database file (if needed) and runs schema.sql to ensure
     * every table exists. Safe to call repeatedly (schema.sql uses
     * CREATE TABLE IF NOT EXISTS).
     */
    public static void initializeSchema() throws SQLException, IOException {
        Path dbDir = Path.of("database");
        if (!Files.exists(dbDir)) {
            Files.createDirectories(dbDir);
        }

        String schemaSql = readSchemaFile();
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            for (String statement : schemaSql.split(";")) {
                String trimmed = statement.trim();
                if (!trimmed.isEmpty()) {
                    stmt.execute(trimmed);
                }
            }
        }
    }

    private static String readSchemaFile() throws IOException {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(SCHEMA_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // strip whole-line SQL comments so they don't interfere with ';' splitting
                if (line.trim().startsWith("--")) {
                    continue;
                }
                sb.append(line).append('\n');
            }
        }
        return sb.toString();
    }
}
