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
     * FileReader attribute.
     */
    private static FileReader reader = null;

    /**
     * Properties attribute.
     */
    private static Properties property = null;

    /**
     * path from the file.properties.
     */
    private static final String PATH = "gradle.properties";

    /**
     * PropertiesReader attribute.
     */
    private static PropertiesReader singleInstance = null;
    private PropertiesReader() {

    }

    /**
     * get instance or create a new one.
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
     * instance the reader class.
     */
    private static void setReader() throws FileNotFoundException {
        reader = new FileReader(PATH);
    }

    /**
     * instance the properties class.
     */
    private static void setPropertiesReader() throws IOException {
        property = new Properties();
        property.load(reader);
    }

    /**
     * instance the PropertiesReader class.
     */
    private static void initializeClass() {
        singleInstance = new PropertiesReader();
    }

    /**
     * get the BaseUrl from the file.properties.
     * @return base url.
     */
    public static String getBaseUrl() {
        String baseUrl = property.getProperty("baseUrl");
        return baseUrl;
    }

    /**
     * get the User from the file.properties.
     * @return User value.
     */
    public static String getUser() {
        String user = property.getProperty("user");
        return user;
    }

    /**
     * get the password from the file.properties.
     * @return Password value.
     */
    public static String getPassword() {
        String password = property.getProperty("password");
        return password;
    }

    /**
     * get the audioTemplatesPath from the file.properties.
     * @return AudioTemplatesPath value.
     */
    public static String getAudioTemplatesPath() {
        String audioTemplatesPath = property.getProperty("audioTemplatesPath");
        return audioTemplatesPath;
    }

    /**
     * get the videoTemplatesPath from the file.properties.
     * @return videoTemplatesPath value.
     */
    public static String getVideoTemplatesPath() {
        String videoTemplatesPath = property.getProperty("videoTemplatesPath");
        return videoTemplatesPath;
    }

    /**
     * get the imageTemplatesPath from the file.properties.
     * @return imageTemplatesPath value.
     */
    public static String getImageTemplatesPath() {
        String imageTemplatesPath = property.getProperty("imageTemplatesPath");
        return imageTemplatesPath;
    }
}
