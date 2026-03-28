package com.nihith.breakdown.model.util;

import java.util.Optional;

/**
 * Utility class that provides a unified lookup mechanism for environment-based
 * configuration values, checking JVM system properties before falling back to
 * OS environment variables.
 */
public class EnvironmentUtil {

    /**
     * Retrieves the value for the given key by first checking JVM system properties
     * ({@link System#getProperty(String)}) and then falling back to OS environment
     * variables ({@link System#getenv(String)}).
     *
     * @param key the property or environment variable name to look up
     * @return the resolved value, or {@code null} if the key is not found in either source
     */
    public static String getEnvironmentVariable(String key) {

        return Optional.ofNullable(System.getProperty(key)).orElse(System.getenv(key));

    }

}
