package com.nihith.breakdown.model.util;

import java.util.Optional;

public class EnvironmentUtil {

    public static String getEnvironmentVariable(String key) {

        return Optional.ofNullable(System.getProperty(key)).orElse(System.getenv(key));

    }

}
