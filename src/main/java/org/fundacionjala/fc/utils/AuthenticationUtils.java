package org.fundacionjala.fc.utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.entity.ContentType;
import org.fundacionjala.fc.config.Environment;

public final class AuthenticationUtils {

    private static final String BASE_URL_API = Environment.getInstance().getBaseUrl();
    private static final String USERNAME_VAL = Environment.getInstance().getUsername();
    private static final String PWD_VAL = Environment.getInstance().getPassword();
    private static final String USERNAME = "username";
    private static final String PWD = "password";
    private static final String PATH = "login";

    /**
     * Constructor for AuthenticationUtils.
     */
    private AuthenticationUtils() {

    }

    /**
     * Get id session of authentication.
     *
     * @return a String.
     */
    private static String getCookie() {
        RestAssured.baseURI = BASE_URL_API;
        Response response = RestAssured
                .given()
                .contentType(ContentType.MULTIPART_FORM_DATA.toString())
                .multiPart(USERNAME, USERNAME_VAL)
                .multiPart(PWD, PWD_VAL)
                .when()
                .post("/" + PATH);

        return response.getDetailedCookie("JSESSIONID").toString();
    }

    /**
     * Get request specifications.
     *
     * @return request.
     */
    public static RequestSpecification getLoggedReqSpec() {
        RequestSpecification request = RestAssured.given();
        request.baseUri(BASE_URL_API);
        request.cookie(getCookie());
        return request;
    }

    /**
     * Get request specifications.
     *
     * @return request.
     */
    public static RequestSpecification getNotLoggedReqSpec() {
        RequestSpecification request = RestAssured.given();
        request.baseUri(BASE_URL_API);
        return request;
    }
}
