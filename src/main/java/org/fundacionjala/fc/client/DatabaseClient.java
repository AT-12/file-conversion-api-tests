package org.fundacionjala.fc.client;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.fundacionjala.fc.config.Environment;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.HashMap;
import java.util.Map;
import java.sql.SQLException;

/**
 * DatabaseClient class.
 */
public final class DatabaseClient {

    private static final Logger LOGGER = LogManager.getLogger(DatabaseClient.class);
    private static DatabaseClient instance;
    private static Connection dbConnection;
    private static final String DB_URL = Environment.getInstance().getDbMysqlUrlConection();
    private static final String DB_USERNAME = Environment.getInstance().getDbUserName();
    private static final String DB_PWD = Environment.getInstance().getDbPassword();

    /**
     * Constructor private for the Database.
     */
    private DatabaseClient() {
        try {
            dbConnection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PWD);
        } catch (Exception e) {
            LOGGER.error("Cannot connect to the database.");
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * get instance or create a new one.
     *
     * @return DatabaseClient instance.
     */
    public static DatabaseClient getInstance() {
        if (instance == null) {
            instance = new DatabaseClient();
        }
        return instance;
    }

    /**
     * Makes a query.
     *
     * @param query
     * @return query result as Map<String, String>
     */
    public Map<String, String> runQuery(final String query) {
        Map<String, String> results = new HashMap<>();
        try (Statement stmt = dbConnection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            ResultSetMetaData metaData = rs.getMetaData();
            int columns = metaData.getColumnCount();
            while (rs.next()) {
                for (int i = 1; i <= columns; i++) {
                    String key = metaData.getColumnName(i);
                    String value = rs.getString(i);
                    results.put(key, value);
                }
            }
        } catch (Exception e) {
            LOGGER.error("Cannot connect to the database.");
            LOGGER.error(e.getMessage());
        } finally {
            closeConnection();
        }
        return results;
    }

    private void closeConnection() {
        if (dbConnection != null) {
            try {
                dbConnection.close();
            } catch (SQLException e) {
                LOGGER.error("Cannot disconnect to the database.");
                LOGGER.error(e.getMessage());
            }
        }
    }
}

