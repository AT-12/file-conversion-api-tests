package org.fundacionjala.fc.client;

import org.fundacionjala.fc.config.Environment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.HashMap;
import java.util.Map;

public class Database {

    private static final String DB_URL = Environment.getInstance().getDbMysqlUrlConection();
    private static final String DB_USERNAME = Environment.getInstance().getDbUserName();
    private static final String DB_PWD = Environment.getInstance().getDbPassword();
    private Connection dbConnection;

    /**
     * Constructor for the Database.
     *
     * @throws SQLException
     */
    public Database() throws SQLException {
        dbConnection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PWD);
    }

    /**
     * Makes a query.
     *
     * @param query
     * @return query result as Map<String, String>
     * @throws SQLException
     */
    public Map<String, String> query(final String query) throws SQLException {
        Statement stmt = dbConnection.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        ResultSetMetaData metaData = rs.getMetaData();
        int columns = metaData.getColumnCount();
        Map<String, String> results = new HashMap<>();
        while (rs.next()) {
            for (int i = 1; i <= columns; i++) {
                String key = metaData.getColumnName(i);
                String value = rs.getString(i);
                results.put(key, value);
            }
        }
        return results;
    }
}

