package org.fundacionjala.fc.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * PropertiesReader class.
 */
public final class Environment {

    private static final String PATH = "gradle.properties";
    private static final Logger LOGGER = LogManager.getLogger(org.mozilla.javascript.tools.shell.Environment.class);
    private static Environment singleInstance;
    private FileReader reader;
    private Properties property;

    private Environment() {
        try {
            reader = new FileReader(PATH);
            property = new Properties();
            property.load(reader);
        } catch (FileNotFoundException e) {
            LOGGER.error("Error when reading file");
            LOGGER.error(e.getMessage());
        } catch (IOException e) {
            LOGGER.error("Error getting properties");
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * get instance or create a new one.
     * @return PropertiesReader instance.
     */
    public static Environment getInstance() {
        if (singleInstance == null) {
            singleInstance = new Environment();
        }
        return singleInstance;
    }

    /**
     * get the BaseUrl from the file.properties.
     * @return base url.
     */
    public String getBaseUrl() {
        return getEnvProperty("baseUrl");
    }

    /**
     * get the User from the file.properties.
     * @return User value.
     */
    public String getUsername() {
        return getEnvProperty("username");
    }

    /**
     * get the password from the file.properties.
     * @return Password value.
     */
    public String getPassword() {
        return getEnvProperty("password");
    }

    /**
     * get the audioTemplatesPath from the file.properties.
     * @return AudioTemplatesPath value.
     */
    public String getAudioTemplatesPath() {
        return getEnvProperty("audioTemplatesPath");
    }

    /**
     * get the videoTemplatesPath from the file.properties.
     * @return videoTemplatesPath value.
     */
    public String getVideoTemplatesPath() {
        return getEnvProperty("videoTemplatesPath");
    }

    /**
     * get the imageTemplatesPath from the file.properties.
     * @return imageTemplatesPath value.
     */
    public String getImageTemplatesPath() {
        return getEnvProperty("imageTemplatesPath");
    }

    /**
     * get the cucumberThreadCount from the file.properties.
     * @return cucumberThreadCount value.
     */
    public String getCucumberThreadCount() {
        return getEnvProperty("cucumberThreadCount");
    }

    private String getEnvProperty(final String env) {
        String localProperty = System.getProperty(env);
        if (localProperty == null) {
            return this.property.getProperty(env);
        }
        return localProperty;
    }
}
