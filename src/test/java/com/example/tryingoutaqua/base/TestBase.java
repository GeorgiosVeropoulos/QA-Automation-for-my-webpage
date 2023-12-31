package com.example.tryingoutaqua.base;

import browser.Browser;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import constants.TestConstants;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.openqa.selenium.WebDriver;
import testCaseReport.TestCaseReport;
import factory.PageFactory;


@Slf4j
public class TestBase {

    private Browser browser;
    private WebDriver driver;
    public PageFactory pages;

    public TestCaseReport testCaseReport;


    // Test report
    private static ExtentReports report;

    private static ExtentSparkReporter extentSparkReporter;

    static {
        String reportName = System.getProperty("reportName");
        if (extentSparkReporter == null) {
            System.out.println("extentSparkReporter = " + extentSparkReporter);
            extentSparkReporter = new ExtentSparkReporter(
                    "target/reports/" + reportName + "/report.html");
            extentSparkReporter.config().setTheme(Theme.STANDARD);
            extentSparkReporter.config().setDocumentTitle("MyReport");
            extentSparkReporter.config().setReportName("Extended Test");
        }

        if (report == null) {
            report = new ExtentReports();
            report.attachReporter(extentSparkReporter);
        }
    }

    public TestBase() {
        browser = new Browser();
        driver = browser.createDriver();
        driver.get(TestConstants.URL);
        setUp();

    }

    public void setUp() {
        testCaseReport = new TestCaseReport(driver);
        pages = new PageFactory(driver, testCaseReport);

    }

    public void setTestName(String name, int ID) {
        testCaseReport.setTestName(report, name, ID);
    }

    @AfterEach
    public void closeDriver() {
        testStep("After step : Close driver");
        testCaseReport.logScreenShot(Status.INFO);
        TestCaseReport.assertAllAndFlushReport(report, driver);

    }


    public void testStep(String message) {
        testCaseReport.logTestStep("<b><font size='3'>" + message + "</font></b>");
        log.info(message);
    }

    @AfterAll
    public static void tearDown() {
//        testCaseReport.flushReport();
//        report.flush();
    }

}
