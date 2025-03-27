package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static Properties properties;
    private static Logger log = LogManager.getLogger(ConfigReader.class);

    static {
        properties = new Properties();
        try (FileInputStream fis = new FileInputStream("src/main/resources/config.properties")) {
            properties.load(fis);
            log.info("Config properties loaded successfully.");
        } catch (IOException e) {
            log.error("Failed to load config properties: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        String value = properties.getProperty(key);
        if (value != null) {
            log.info("Retrieved property: " + key + " = " + value);
        } else {
            log.warn("Property not found: " + key);
        }
        return value;
    }
}