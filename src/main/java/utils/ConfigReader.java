package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * ConfigReader class for reading properties from a configuration file.
 */
public class ConfigReader {

    private static Properties properties;
    private static Logger log = LogManager.getLogger(ConfigReader.class);

    static {
        properties = new Properties();
        loadProperties();
    }

    /**
     * Loads properties from the config.properties file.
     */
    private static void loadProperties() {
        try (FileInputStream fis = new FileInputStream("src/main/resources/config.properties")) {
            properties.load(fis);
            log.info("Config properties loaded successfully.");
        } catch (IOException e) {
            log.error("Failed to load config properties: " + e.getMessage(), e);
            throw new RuntimeException("Failed to load config properties.", e); // Rethrow as RuntimeException
        }
    }

    /**
     * Retrieves the value of a property based on its key.
     *
     * @param key The key of the property.
     * @return The value of the property, or null if the key is not found.
     */
    public static String getProperty(String key) {
        String value = properties.getProperty(key);
        if (value != null) {
            log.info("Retrieved property: " + key + " = " + value);
        } else {
            log.warn("Property not found: " + key);
        }
        return value;
    }

    /**
     * Retrieves the value of a property as an integer.
     *
     * @param key The key of the property.
     * @return The integer value of the property, or null if the key is not found or not a valid integer.
     */
    public static Integer getIntegerProperty(String key) {
        String value = getProperty(key);
        if (value != null) {
            try {
                return Integer.parseInt(value);
            } catch (NumberFormatException e) {
                log.error("Property '" + key + "' is not a valid integer: " + value);
                return null;
            }
        }
        return null;
    }

    /**
     * Retrieves the value of a property as a boolean.
     *
     * @param key The key of the property.
     * @return The boolean value of the property, or null if the key is not found.
     */
    public static Boolean getBooleanProperty(String key) {
        String value = getProperty(key);
        if (value != null) {
            return Boolean.parseBoolean(value);
        }
        return null;
    }
}