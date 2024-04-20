package helpers;

import browser.Browser;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import testReport.TestReport;

public class EssentialGenerator {


    public static Browser browser;
    @Getter
    public static WebDriver driver;
    @Getter
    private TestReport testReport;


    public static WebDriver generateBrowser() {
        browser = new Browser();
        driver = browser.getDriver();
        return driver;
    }

    public static TestReport initNewTest() {
        return new TestReport(driver);
    }

}
