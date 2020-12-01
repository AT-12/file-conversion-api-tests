package org.fundacionjala.fc.client;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.fundacionjala.fc.config.Environment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.HashMap;
import java.util.Map;

public class Database {

    private static final String DB_URL = Environment.getInstance().getDbMysqlUrlConection();
    private static final String DB_USERNAME = Environment.getInstance().getDbUserName();
    private static final String DB_PWD = Environment.getInstance().getDbPassword();
    private static final Logger LOGGER = LogManager.getLogger(org.mozilla.javascript.tools.shell.Environment.class);

    private Connection dbConnection;

    /**
     * Constructor for the Database.
     */
    public Database() {
        try {
            dbConnection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PWD);
        } catch (Exception e) {
            LOGGER.error("Error when connecting to database");
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * Makes a query.
     *
     * @param query
     * @return query result as Map<String, String>
     */
    public Map<String, String> query(final String query) {
        Map<String, String> results = new HashMap<>();
        try {
            Statement stmt = dbConnection.createStatement();
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
            LOGGER.error("Error when executing query");
            LOGGER.error(e.getMessage());
        }
        return results;
    }
}

