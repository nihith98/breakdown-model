package com.nihith.breakdown.test.model.util;

import com.nihith.breakdown.model.util.EnvironmentUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = EnvironmentUtilTest.class)
public class EnvironmentUtilTest {

    private final Map<String, String> originalEnv = new HashMap<>();
    private final Map<String, String> originalProperties = new HashMap<>();

    @BeforeEach
    public void setUp() {
        // Store original environment variables and properties
        originalEnv.putAll(System.getenv());
        System.getProperties().forEach((key, value) -> originalProperties.put((String) key, (String) value));
        // Clear environment variables and properties for clean test
        clearEnv();
        clearProperties();
    }

    @AfterEach
    public void tearDown() {
        // Restore original environment variables and properties
        clearEnv();
        clearProperties();
        originalEnv.forEach(System::setProperty);
        originalProperties.forEach(System::setProperty);
    }

    private void clearEnv() {
        // Clear environment variables for testing purposes
        try {
            Class<?> processEnvironmentClass = Class.forName("java.lang.ProcessEnvironment");
            java.lang.reflect.Field theEnvironmentField = processEnvironmentClass.getDeclaredField("theEnvironment");
            theEnvironmentField.setAccessible(true);
            Map<String, String> env = (Map<String, String>) theEnvironmentField.get(null);
            env.clear();
            java.lang.reflect.Field theCaseInsensitiveEnvironmentField = processEnvironmentClass.getDeclaredField("theCaseInsensitiveEnvironment");
            theCaseInsensitiveEnvironmentField.setAccessible(true);
            Map<String, String> cienv = (Map<String, String>) theCaseInsensitiveEnvironmentField.get(null);
            cienv.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void clearProperties(){
        System.getProperties().stringPropertyNames().forEach(System::clearProperty);
    }

    @Test
    void getEnvironmentVariable_fromSystemProperty() {
        // Arrange
        String key = "test.property";
        String expectedValue = "propertyValue";
        System.setProperty(key, expectedValue);

        // Act
        String actualValue = EnvironmentUtil.getEnvironmentVariable(key);

        // Assert
        assertEquals(expectedValue, actualValue);
    }



    @Test
    void getEnvironmentVariable_keyNotFound() {
        // Arrange
        String key = "NON_EXISTENT_KEY";

        // Act
        String actualValue = EnvironmentUtil.getEnvironmentVariable(key);

        // Assert
        assertNull(actualValue);
    }


}
