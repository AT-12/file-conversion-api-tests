package org.fundacionjala.fc.utils;

import java.util.Map;

public final class Mapper {

    private static Mapper singleInstance;
    /**
     * Mapper constructor.
     */
    private Mapper() {
    }

    /**
     * get instance or create a new one.
     * @return PropertiesReader instance.
     */
    public static Mapper getInstance() {
        if (singleInstance == null) {
            singleInstance = new Mapper();
        }
        return singleInstance;
    }
    /**
     *
     * @param value
     * @param storedValues
     * @return The new endPoint with params replaced.
     */
    public static String mapValue(final String value, final Map<String, String> storedValues) {
        String val = value;
        for (Map.Entry<String, String> entry : storedValues.entrySet()) {
            val = val.replace("{" + entry.getKey() + "}", entry.getValue());
        }
        return val;
    }
}
