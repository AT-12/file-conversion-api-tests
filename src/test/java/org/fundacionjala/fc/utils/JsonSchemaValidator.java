package org.fundacionjala.fc.utils;

import io.restassured.response.Response;
import org.fundacionjala.fc.config.Environment;

import java.io.File;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;

public final class JsonSchemaValidator {

    /**
     * This avoids a default constructor for utility class.
     */
    private JsonSchemaValidator() {
    }

    /**
     * A method that validate a http response from a given JsonSchema file.
     *
     * @param response       the response obtained from the server.
     * @param schemaFilePath the path of the JsonSchema file.
     */
    public static void validate(final Response response, final String schemaFilePath) {
        File schemaFile = new File(Environment.getInstance().getSchemasPath().concat(schemaFilePath));
        response.then().assertThat().body(matchesJsonSchema(schemaFile));
    }
}
