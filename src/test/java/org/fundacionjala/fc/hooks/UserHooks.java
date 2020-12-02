package org.fundacionjala.fc.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.fundacionjala.fc.client.DatabaseClient;
import org.fundacionjala.fc.context.Context;

public class UserHooks {

    private Context context;

    /**
     * Constructor for the UserHooks.
     * @param contextToSet
     */
    public UserHooks(final Context contextToSet) {
        this.context = contextToSet;
    }

    /**
     * Creates user in the database.
     */
    @Before(value = "@createUser")
    public void createUser() {
        String query =  "INSERT INTO users (last_name, name, password, rol, username)"
                        + "VALUES (lastName, demo, Secret123, user, laura)";
        DatabaseClient.getInstance().runQuery(query);
    }

    /**
     * Creates users in the database.
     */
    @After(value = "@createUsers")
    public void createUsers() {
        String query = "INSERT INTO users (last_name, name, password, rol, username)"
                + "VALUES (lastName, demo, Secret123, user, laura), (lastName, demo, Secret123, user, user2)";
        DatabaseClient.getInstance().runQuery(query);
    }

    /**
     * Deletes user from the database.
     */
    @After(value = "@deleteUser")
    public void deleteUser() {
        String username = context.getValueData("usernameToDelete");
        String query = String.format("DELETE FROM users WHERE username = '%s'", username);
        DatabaseClient.getInstance().runQuery(query);
    }

    /**
     * Deletes users from the database.
     */
    @After(value = "@deleteUsers")
    public void deleteUsers() {
        String query = "DELETE FROM users";
        DatabaseClient.getInstance().runQuery(query);
    }
}
