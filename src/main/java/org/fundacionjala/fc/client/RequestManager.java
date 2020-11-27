package org.fundacionjala.fc.client;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.entity.ContentType;
import org.fundacionjala.fc.utils.AuthenticationUtils;

import java.io.File;
import java.util.Map;
import static io.restassured.RestAssured.given;

public final class RequestManager {

    private static RequestSpecification reqSpec;

    /**
     * Constructor for the Request Manager.
     */
    private RequestManager() {
    }

    /**
     * Makes a GET request.
     *
     * @param endpoint
     * @return a response object.
     */
    public static Response get(final String endpoint) {
        return given().spec(reqSpec).when().get(endpoint);
    }

    /**
     * Makes a GET request.
     *
     * @param endpoint
     * @param params
     * @return a response object.
     */
    public static Response get(final String endpoint, final Map<String, String> params) {
        RequestSpecification req = reqSpec;
        req.contentType(ContentType.MULTIPART_FORM_DATA.toString());
        for (Map.Entry<String, String> entry : params.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (value.startsWith("@")) {
                req.multiPart(key, new File(getFilePath(value)));
            } else {
                req.multiPart(key, value);
            }
        }
        return req.post(endpoint);
    }

    /**
     * Makes POST request.
     *
     * @param endpoint
     * @param params
     * @return a response object.
     */
    public static Response post(final String endpoint, final Map<String, String> params) {
        RequestSpecification req = reqSpec;
        req.contentType(ContentType.MULTIPART_FORM_DATA.toString());

        for (Map.Entry<String, String> entry : params.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (value.startsWith("@")) {
                req.multiPart(key, new File(getFilePath(value)));
            } else {
                req.multiPart(key, value);
            }
        }
        return req.post(endpoint);
    }

    /**
     * Makes a PUT request.
     *
     * @param endpoint
     * @param params
     * @return a response object.
     */
    public static Response put(final String endpoint, final Map<String, String> params) {
        RequestSpecification req =  RestAssured.given();
        req.contentType(ContentType.MULTIPART_FORM_DATA.toString());

        for (Map.Entry<String, String> entry : params.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            req.multiPart(key, value);
        }
        return req.put(endpoint);
    }

    /**
     * Makes a DELETE request.
     *
     * @param endpoint
     * @return a response object.
     */
    public static Response delete(final String endpoint) {
        Response response = given()
                .spec(reqSpec)
                .when()
                .delete(endpoint);
        return response;
    }

    /**
     * Extract a substring.
     *
     * @param path
     * @return a String with the path of file.
     */
    private static String getFilePath(final String path) {
        int beginIndex = path.indexOf('"') + 1;
        int endIndex = path.indexOf('"', beginIndex);

        return path.substring(beginIndex, endIndex);
    }

    /**
     * Extract a substring.
     *
     * @return a String with the path of file.
     */
    public static void setLoggedReqSpec() {
        reqSpec = AuthenticationUtils.getLoggedReqSpec();
    }

    /**
     * Extract a substring.
     *
     * @return a String with the path of file.
     */
    public static void setNotLoggedReqSpec() {
        reqSpec = AuthenticationUtils.getNotLoggedReqSpec();
    }
}
