package org.fundacionjala.fc.config;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * PropertiesReader class.
 */
public final class PropertiesReader {

    /**
     * PropertiesReader class.
     */
    private static FileReader reader = null;

    /**
     * PropertiesReader class.
     */
    private static Properties property = null;

    /**
     * PropertiesReader class.
     */
    private static final String PATH = "gradle.properties";

    /**
     * PropertiesReader class.
     */
    private static PropertiesReader singleInstance = null;
    private PropertiesReader() {

    }

    /**
     * Spin the words with more letters than the limit.
     * @return PropertiesReader instance.
     */
    public static PropertiesReader getInstance() throws IOException {
        if (singleInstance == null) {
            setReader();
            setPropertiesReader();
            initializeClass();
        }
        return singleInstance;
    }

    /**
     * Spin the words with more letters than the limit.
     */
    private static void setReader() throws FileNotFoundException {
        reader = new FileReader(PATH);
    }

    /**
     * Spin the words with more letters than the limit.
     */
    private static void setPropertiesReader() throws IOException {
        property = new Properties();
        property.load(reader);
    }

    /**
     * Spin the words with more letters than the limit.
     */
    private static void initializeClass() {
        singleInstance = new PropertiesReader();
    }

    /**
     * Spin the words with more letters than the limit.
     * @return PropertiesReader instance.
     */
    public static String getBaseUrl() {
        String baseUrl = property.getProperty("baseUrl");
        return baseUrl;
    }

    /**
     * Spin the words with more letters than the limit.
     * @return PropertiesReader instance.
     */
    public static String getUser() {
        String user = property.getProperty("user");
        return user;
    }

    /**
     * Spin the words with more letters than the limit.
     * @return PropertiesReader instance.
     */
    public static String getPassword() {
        String password = property.getProperty("password");
        return password;
    }

    /**
     * Spin the words with more letters than the limit.
     * @return PropertiesReader instance.
     */
    public static String getAudioTemplatesPath() {
        String audioTemplatesPath = property.getProperty("audioTemplatesPath");
        return audioTemplatesPath;
    }

    /**
     * Spin the words with more letters than the limit.
     * @return PropertiesReader instance.
     */
    public static String getVideoTemplatesPath() {
        String videoTemplatesPath = property.getProperty("videoTemplatesPath");
        return videoTemplatesPath;
    }

    /**
     * Spin the words with more letters than the limit.
     * @return PropertiesReader instance.
     */
    public static String getImageTemplatesPath() {
        String imageTemplatesPath = property.getProperty("imageTemplatesPath");
        return imageTemplatesPath;
    }
}
