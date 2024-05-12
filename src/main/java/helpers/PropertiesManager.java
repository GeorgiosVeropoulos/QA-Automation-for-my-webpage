package helpers;

import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

@Slf4j
public class PropertiesManager extends Properties {

    private static volatile Properties properties;
    private static final Object lock = new Object();

    public PropertiesManager() {
        Path path = Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "env", System.getProperty("property"));
        loadProperties(path);
    }


    public static String getEnvironmentProperty(String property) {
        return properties.getProperty(property);
    }


    private static void loadProperties(Path path) {
        synchronized (lock) {
            if (properties != null) {
                return;
            }
            log.info("Starting to load properties!");
            properties = new Properties();
            try (FileInputStream inputStream = new FileInputStream(path.toString())) {
                properties.load(inputStream);
                properties.forEach((key, value) -> System.setProperty(key.toString(), value.toString()));

                // Print the properties
                String databaseUrl = properties.getProperty("url");
                String username = properties.getProperty("username");
                String password = properties.getProperty("password");
                System.out.println("Database URL: " + databaseUrl);
                System.out.println("Username: " + username);
                System.out.println("Password: " + password);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
