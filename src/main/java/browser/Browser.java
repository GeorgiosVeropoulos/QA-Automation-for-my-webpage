package browser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Properties;


//Creates the necessary driver currently only supports Chrome.
public class Browser {

    private String args = System.getProperty("property");

    public Browser() {
        Path path = Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "env", args);
        System.out.println(path);

        initiateProperties(String.valueOf(path));
    }


    private void initiateProperties(String path) {
        Properties properties = new Properties();

        try (FileInputStream inputStream = new FileInputStream(path)) {
            properties.load(inputStream);
            // Access properties using keys
            properties.forEach((key, value) -> System.setProperty(key.toString(), value.toString()));

            String databaseUrl = properties.getProperty("url");
            String username = properties.getProperty("username");
            String password = properties.getProperty("password");

            // Print the properties
            System.out.println("Database URL: " + databaseUrl);
            System.out.println("Username: " + username);
            System.out.println("Password: " + password);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public WebDriver createDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        return driver;
    }
}
