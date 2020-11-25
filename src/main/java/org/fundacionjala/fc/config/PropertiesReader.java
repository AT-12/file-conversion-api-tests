package org.fundacionjala.fc.config;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * PropertiesReader class.
 */
public final class PropertiesReader {

    private FileReader reader;
    private Properties property;
    private static final String PATH = "gradle.properties";
    private static PropertiesReader singleInstance;

    private PropertiesReader() {
        try {
            reader = new FileReader(PATH);
            property = new Properties();
            property.load(reader);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * get instance or create a new one.
     * @return PropertiesReader instance.
     */
    public static PropertiesReader getInstance() {
        if (singleInstance == null) {
            singleInstance = new PropertiesReader();
        }
        return singleInstance;
    }

    /**
     * get the BaseUrl from the file.properties.
     * @return base url.
     */
    public String getBaseUrl() {
        return property.getProperty("baseUrl");
    }

    /**
     * get the User from the file.properties.
     * @return User value.
     */
    public String getUser() {
        return property.getProperty("user");
    }

    /**
     * get the password from the file.properties.
     * @return Password value.
     */
    public String getPassword() {
        return property.getProperty("password");
    }

    /**
     * get the audioTemplatesPath from the file.properties.
     * @return AudioTemplatesPath value.
     */
    public String getAudioTemplatesPath() {
        return property.getProperty("audioTemplatesPath");
    }

    /**
     * get the videoTemplatesPath from the file.properties.
     * @return videoTemplatesPath value.
     */
    public String getVideoTemplatesPath() {
        return property.getProperty("videoTemplatesPath");
    }

    /**
     * get the imageTemplatesPath from the file.properties.
     * @return imageTemplatesPath value.
     */
    public String getImageTemplatesPath() {
        return property.getProperty("imageTemplatesPath");
    }

}
