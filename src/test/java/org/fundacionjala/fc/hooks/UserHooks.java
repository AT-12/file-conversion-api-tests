package org.fundacionjala.fc.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.fundacionjala.fc.client.DatabaseClient;
import org.fundacionjala.fc.client.RequestManager;
import org.fundacionjala.fc.config.Environment;
import org.fundacionjala.fc.context.Context;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class UserHooks {

    public static final int BOUND = 50;
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
        String endpoint = Environment.getInstance().getBaseUrl() + "/user/createUser";
        String randomInteger = new Random().nextInt(BOUND) + "";
        Map<String, String> formData = Map.of(
                        "lastName", "lastName",
                        "name", "laura",
                        "password", "Secret123",
                        "rePassword", "Secret123",
                        "username", "laura" + randomInteger);
        RequestManager.setLoggedReqSpec();
        RequestManager.post(endpoint, formData);
        Map<String, String> queryResult = new HashMap<String, String>();
        String query = String.format("select * from demo.users where username = '%s'", "laura" + randomInteger);
        queryResult = DatabaseClient.getInstance().runQuery(query);
        context.saveData("usernameToDelete", "laura" + randomInteger);
        context.saveData("id", queryResult.get("id"));
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
}
