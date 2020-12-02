package org.fundacionjala.fc.stepdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.fundacionjala.fc.client.RequestManager;
import org.fundacionjala.fc.context.Context;
import org.fundacionjala.fc.utils.JsonSchemaValidator;
import org.fundacionjala.fc.utils.Mapper;
import org.fundacionjala.fc.utils.ResponseBodyValidator;
import org.testng.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * Defines request step definitions.
 */
public class RequestStepDefs {

    private Response response;
    private Context context;

    /**
     * Constructor of RequestStepDefs.
     *
     * @param contextToSet Context to set.
     */
    public RequestStepDefs(final Context contextToSet) {
        this.context = contextToSet;
    }
    /**
     * Sets valid authentication headers.
     */
    @Given("I am logged in the Converter Application")
    @Given("I set valid authentication headers")
    public void setValidAuthenticationHeaders() {
        RequestManager.setLoggedReqSpec();
    }

    /**
     * Sets the headers without authentication.
     */
    @Given("I set the request headers without authentication")
    public void setRequestHeadersWithoutAuthentication() {
        RequestManager.setNotLoggedReqSpec();
    }

    /**
     * Sends GET request.
     *
     * @param endpoint resource endpoint.
     */
    @When("I send a GET request to {string}")
    public void sendGETRequest(final String endpoint) {
        response = RequestManager.get(endpoint);
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
    public void sendPOSTRequestWithEmptyFormData(final String endpoint) {
        response = RequestManager.post(endpoint, new HashMap<>());
    }

    /**
     * Sends a PUT request.
     *
     * @param endpoint resource endpoint.
     * @param formData form data.
     */
    @When("I send a PUT request to {string} with following form data")
    public void sendPUTRequest(final String endpoint, final Map<String, String> formData) {
        String endpointMapped = Mapper.mapValue(endpoint, context.getData());
        response = RequestManager.put(endpointMapped, formData);
    }

    /**
     * Sends a DELETE request.
     *
     * @param endpoint resource endpoint.
     */
    @When("I send a DELETE request to {string}")
    public void sendDELETERequest(final String endpoint) {
        String endpointMapped = Mapper.mapValue(endpoint, context.getData());
        response = RequestManager.delete(endpointMapped);
    }

    /**
     * Stores the value from request to clean the workspace.
     *
     * @param key
     */
    @And("I store the {string} value from request to clean workspace")
    public void storeRequestValue(final String key) {
        String value = context.getRequestValue(key);
        context.saveData("usernameToDelete", value);
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
