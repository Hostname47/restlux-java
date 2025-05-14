package api;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {
    private static final String CONFIG_FILE = "config.properties";

    public static String getBaseApiUrl() {
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream(CONFIG_FILE)) {
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return properties.getProperty("api.base.url", "127.0.0.1/api");
    }
}