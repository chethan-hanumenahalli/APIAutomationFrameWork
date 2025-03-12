package com.qa.gorest.manager;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {
    private static Properties properties = new Properties();

    // static block
    static {
        try (InputStream input = ConfigManager.class.getClassLoader().getResourceAsStream("config/config.properties")) {
            if (input != null) {
                properties.load(input);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //Method to load the values from config.properties file
    public static String get(String key) {
        return properties.getProperty(key);
    }

    public static void set(String key, String value) {
         properties.setProperty(key, value);
    }

}
