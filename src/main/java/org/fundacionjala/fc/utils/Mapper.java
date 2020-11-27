package org.fundacionjala.fc.utils;

import java.util.Map;

/**
 * Class Mapper
 */
public class Mapper {

    public Mapper() {

    }

    /**
     *
     * @param value
     * @param storedValues
     * @return
     */
    public static String mapValue(String value, Map<String, String> storedValues) {
        for (Map.Entry<String, String> entry : storedValues.entrySet()) {
            value = value.replace("{"+entry.getKey()+"}",entry.getValue());
        }
        return value;
    }
}
