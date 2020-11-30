package org.fundacionjala.fc.unit.tests;

import org.fundacionjala.fc.config.Environment;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * Defines unit tests for PropertiesReader Class.
 */
public class EnvironmentTests {

    /**
     * Test get User value from gradle.properties.
     */
    @Test
    public void getUserTest() throws IOException {
        Environment reader = Environment.getInstance();
        String actual = reader.getUsername();
        String expected = "";
        Assert.assertEquals(actual, expected);
    }

    /**
     * Test get baseUrl value from gradle.properties.
     */
    @Test
    public void getBaseUrlTest() throws IOException {
        Environment reader = Environment.getInstance();
        String actual = reader.getBaseUrl();
        String expected = "";
        Assert.assertEquals(actual, expected);
    }

    /**
     * Test get password value from gradle.properties.
     */
    @Test
    public void getPasswordTest() throws IOException {
        Environment reader = Environment.getInstance();
        String actual = reader.getPassword();
        String expected = "";
        Assert.assertEquals(actual, expected);
    }

    /**
     * Test get audioTemplatesPathTest value from gradle.properties.
     */
    @Test
    public void getTemplatesPathTest() throws IOException {
        Environment reader = Environment.getInstance();
        String actual = Environment.getInstance().getTemplatesPath();
        String expected = "src/test/resources/templates/";
        Assert.assertEquals(actual, expected);
    }
}
