package com.example.tryingoutaqua.base;

import browser.Browser;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.example.tryingoutaqua.listeners.TestListener;
import constants.TestConstants;
import helpers.PropertiesManager;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.extension.ExecutionCondition;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.openqa.selenium.WebDriver;
import testCaseReport.TestCaseReport;
import factory.PageFactory;

import java.util.concurrent.atomic.AtomicReference;

/**
 * TestBase that all Tests must extend.
 */
@Slf4j
@ExtendWith(TestListener.class)
@Execution(ExecutionMode.CONCURRENT) //this is only added if you want to run in parallel using the JUnit configuration instead of through Maven. add this in the VM Options -Djunit.jupiter.execution.parallel.enabled=true
public abstract class TestBase {
    private Browser browser;
    @Getter
    private WebDriver driver;
    @Getter
    private TestCaseReport testCaseReport;


    // Test report
    @Getter
    private static final AtomicReference<ExtentReports> reportRef = new AtomicReference<>();
    private static final Object lock = new Object();

    private void initReport() {
        synchronized (lock) {
            if (reportRef.get() == null) {
                String reportName = System.getProperty("reportName");
                ExtentReports report = new ExtentReports();
                ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter("target/reports/" + reportName + "/report.html");
                System.out.println("Report created" + report);
                extentSparkReporter.config().setTheme(Theme.STANDARD);
                extentSparkReporter.config().setDocumentTitle("QA-Automation-Report");
                extentSparkReporter.config().thumbnailForBase64(true);
                extentSparkReporter.config().setReportName("Amazing Report Name");
                report.attachReporter(extentSparkReporter);
                reportRef.set(report);
            }
        }
    }


    //TODO move these into the TestListener instead of setting them up here!
    public TestBase() {
        new PropertiesManager();
        initReport();
        browser = new Browser();
        driver = browser.getDriver();
        driver.get(TestConstants.URL);
        testCaseReport = new TestCaseReport(driver);
    }


    @AfterEach
    public void closeDriver() {
        testStep("After step : Close driver");
//        testCaseReport.logScreenShot(Status.INFO);
        testCaseReport.logScreenShot64(Status.INFO);
        TestCaseReport.assertAllAndFlushReport(reportRef.get(), getDriver());
        browser.removeDriver();
    }


    public void testStep(String message) {
        testCaseReport.logTestStep("<b><font size='3'>" + message + "</font></b>");
        log.info(message);
    }


}
