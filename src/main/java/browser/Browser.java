package browser;

import helpers.PropertiesManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import static constants.TestConstants.DEFAULT_WAIT;


//Creates the necessary driver currently only supports Chrome.
@Slf4j
public class Browser {
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    public Browser() {
        initializeWebDriver();
    }

    private void initializeWebDriver() {
        setUpBrowser(selectBrowserFromProperties());
        driverThreadLocal.get().manage().window().maximize();
        driverThreadLocal.get().manage().timeouts().pageLoadTimeout(Duration.ofMillis(DEFAULT_WAIT));
        driverThreadLocal.get().manage().timeouts().implicitlyWait(Duration.ofMillis(DEFAULT_WAIT));
    }


    /**
     * Assigns the Browser to be used by the Tests.
     * @param browserType
     */
    private void setUpBrowser(BrowserType browserType) {
        switch (browserType) {
            case CHROME -> {
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--remote-allow-origins=*");
                if (Boolean.parseBoolean(PropertiesManager.getEnvironmentProperty("headless"))) {
                    options.addArguments("--no-sandbox");
//                    options.addArguments("--disable-dev-shm-usage");
                    options.addArguments("--headless=new");
                }

                Map<String, Object> prefs = new HashMap<>();
                prefs.put("plugins.plugins_disabled", new String[]{"Chrome PDF Viewer"});
                options.setExperimentalOption("prefs", prefs);
                driverThreadLocal.set(new ChromeDriver(options));
                log.info("Browser to be used was CHROME!");
            }
            case FIREFOX -> {
                FirefoxOptions options = new FirefoxOptions();
                driverThreadLocal.set(new FirefoxDriver(options));
                log.info("Browser to be used was FIREFOX!");
            }
            default -> {
                log.error("No compatible browser found!");
            }
        }

    }

    /**
     * selects the browser to use based on the properties.
     * @return the BrowserType to be used.
     */
    private BrowserType selectBrowserFromProperties() {
        String browser = System.getProperty("browser", "CHROME");
        return switch (browser.toUpperCase()) {
            case "CHROME", "CH" -> BrowserType.CHROME;
            case "FIREFOX", "FX" -> BrowserType.FIREFOX;
            default -> {
                log.info("Browser set wasn't correct or not currently supported defaulting to CHROME");
                yield BrowserType.CHROME;
            }
        };
    }

    enum BrowserType {
        CHROME,
        FIREFOX
    }


    public WebDriver getDriver() {
        return driverThreadLocal.get();
    }

    public void removeDriver() {
        if (driverThreadLocal.get() != null) {
            driverThreadLocal.remove();
        }
    }
}
