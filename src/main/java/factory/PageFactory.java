package factory;

import org.openqa.selenium.WebDriver;
import pages.MainPage;
import testReport.TestReport;

public class PageFactory {

    public final MainPage mainPage;


    public PageFactory(WebDriver driver, TestReport testReport) {
        mainPage = new MainPage(driver, testReport);
    }
}
