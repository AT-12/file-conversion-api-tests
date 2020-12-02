package org.fundacionjala.fc.stepdefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.fundacionjala.fc.client.RequestManager;
import org.fundacionjala.fc.utils.JsonSchemaValidator;
import org.fundacionjala.fc.utils.ResponseBodyValidator;
import org.testng.Assert;
import java.util.HashMap;
import java.util.Map;

/**
 * Defines request step definitions.
 */
public class RequestStepDefs {

    private Response response;

    /**
     * Sets valid authentication headers.
     */
    @Given("I set valid authentication headers")
    public void setValidAuthenticationHeaders() {
        RequestManager.setLoggedReqSpec();
    }

    /**
     * Sends POST request.
     *
     * @param endpoint resource endpoint.
     * @param formData form data.
     */
    @When("I send a POST request to {string} with the following form data")
    public void sendPOSTRequest(final String endpoint, final Map<String, String> formData) {
        response = RequestManager.post(endpoint, formData);
    }

    /**
     * Sends a POST request.
     *
     * @param endpoint resource endpoint.
     */
    @When("I send a POST request to {string} with the empty form data")
    public void iSendAPOSTRequestToWithTheEmptyFormData(final String endpoint) {
        response = RequestManager.post(endpoint, new HashMap<>());
    }

    /**
     * Validates response status code.
     *
     * @param expectedStatusCode expected status code.
     */
    @Then("I validate the response has the \"{int}\" status code")
    public void validateStatusCode(final int expectedStatusCode) {
        Assert.assertEquals(response.statusCode(), expectedStatusCode);
    }

    /**
     * Validates response body json schema.
     *
     * @param schemaPath schema path
     */
    @Then("I validate that the response body should match with {string} JSON schema")
    public void validateResponseJSONSchema(final String schemaPath) {
        JsonSchemaValidator.validate(response, schemaPath);
    }

    /**
     * Validates response values.
     *
     * @param expectedValues expected response values.
     */
    @Then("I validate that the response contain the following values")
    public void validateResponseValues(final Map<String, String> expectedValues) {
        ResponseBodyValidator.validate(response, expectedValues);
    }
}
