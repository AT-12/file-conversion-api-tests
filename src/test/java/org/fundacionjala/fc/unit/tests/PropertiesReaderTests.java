package org.fundacionjala.fc.unit.tests;

import org.fundacionjala.fc.config.PropertiesReader;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;


/**
 * Defines unit tests for Dummy Util Class.
 */
public class PropertiesReaderTests {

    /**
     * Test get value from Dummy Util class.
     */
    @Test
    public void getUserTest() throws IOException {
        PropertiesReader reader = PropertiesReader.getInstance();
        String actual = reader.getUser();
        String expected = "";
        Assert.assertEquals(actual, expected);
    }

    /**
     * Test get value from Dummy Util class.
     */
    @Test
    public void getBaseUrlTest() throws IOException {
        PropertiesReader reader = PropertiesReader.getInstance();
        String actual = reader.getBaseUrl();
        String expected = "";
        Assert.assertEquals(actual, expected);
    }

    /**
     * Test get value from Dummy Util class.
     */
    @Test
    public void getPasswordTest() throws IOException {
        PropertiesReader reader = PropertiesReader.getInstance();
        String actual = reader.getPassword();
        String expected = "";
        Assert.assertEquals(actual, expected);
    }

    /**
     * Test get value from Dummy Util class.
     */
    @Test
    public void getAudioTemplatesPathTest() throws IOException {
        PropertiesReader reader = PropertiesReader.getInstance();
        String actual = reader.getAudioTemplatesPath();
        String expected = "";
        Assert.assertEquals(actual, expected);
    }

    /**
     * Test get value from Dummy Util class.
     */
    @Test
    public void getVideoTemplatesPathTest() throws IOException {
        PropertiesReader reader = PropertiesReader.getInstance();
        String actual = reader.getVideoTemplatesPath();
        String expected = "";
        Assert.assertEquals(actual, expected);
    }

    /**
     * Test get value from Dummy Util class.
     */
    @Test
    public void getImageTemplatesPathTest() throws IOException {
        PropertiesReader reader = PropertiesReader.getInstance();
        String actual = reader.getImageTemplatesPath();
        String expected = "";
        Assert.assertEquals(actual, expected);
    }

}
