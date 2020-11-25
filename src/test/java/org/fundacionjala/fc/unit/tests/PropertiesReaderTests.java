package org.fundacionjala.fc.unit.tests;

import org.fundacionjala.fc.config.PropertiesReader;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;

/**
 * Defines unit tests for PropertiesReader Class.
 */
public class PropertiesReaderTests {

    /**
     * Test get User value from gradle.properties.
     */
    @Test
    public void getUserTest() throws IOException {
        PropertiesReader reader = PropertiesReader.getInstance();
        String actual = reader.getUser();
        String expected = "";
        Assert.assertEquals(actual, expected);
    }

    /**
     * Test get baseUrl value from gradle.properties.
     */
    @Test
    public void getBaseUrlTest() throws IOException {
        PropertiesReader reader = PropertiesReader.getInstance();
        String actual = reader.getBaseUrl();
        String expected = "";
        Assert.assertEquals(actual, expected);
    }

    /**
     * Test get password value from gradle.properties.
     */
    @Test
    public void getPasswordTest() throws IOException {
        PropertiesReader reader = PropertiesReader.getInstance();
        String actual = reader.getPassword();
        String expected = "";
        Assert.assertEquals(actual, expected);
    }

    /**
     * Test get audioTemplatesPathTest value from gradle.properties.
     */
    @Test
    public void getAudioTemplatesPathTest() throws IOException {
        PropertiesReader reader = PropertiesReader.getInstance();
        String actual = reader.getAudioTemplatesPath();
        String expected = "";
        Assert.assertEquals(actual, expected);
    }

    /**
     * Test get VideoTemplatesPathTest value from gradle.properties.
     */
    @Test
    public void getVideoTemplatesPathTest() throws IOException {
        PropertiesReader reader = PropertiesReader.getInstance();
        String actual = reader.getVideoTemplatesPath();
        String expected = "";
        Assert.assertEquals(actual, expected);
    }

    /**
     * Test get ImageTemplatesPathTest value from gradle.properties.
     */
    @Test
    public void getImageTemplatesPathTest() throws IOException {
        PropertiesReader reader = PropertiesReader.getInstance();
        String actual = reader.getImageTemplatesPath();
        String expected = "";
        Assert.assertEquals(actual, expected);
    }

}
