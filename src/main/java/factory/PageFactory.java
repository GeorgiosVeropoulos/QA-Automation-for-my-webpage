package factory;

import org.openqa.selenium.WebDriver;
import pages.MainPage;
import testCaseReport.TestCaseReport;

public class PageFactory {

    public final MainPage mainPage;


    public PageFactory(WebDriver driver, TestCaseReport testCaseReport) {
        mainPage = new MainPage(driver, testCaseReport);
    }
}
