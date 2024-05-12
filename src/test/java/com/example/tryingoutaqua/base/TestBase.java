package com.example.tryingoutaqua.base;

import browser.Browser;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.example.tryingoutaqua.listeners.TestListener;
import constants.TestConstants;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.openqa.selenium.WebDriver;
import testReport.TestReport;

/**
 * TestBase that all Tests must extend.
 */
@Slf4j
@ExtendWith(TestListener.class)
@Execution(ExecutionMode.CONCURRENT) //this is only added if you want to run in parallel using the JUnit configuration instead of through Maven. add this in the VM Options -Djunit.jupiter.execution.parallel.enabled=true
public abstract class TestBase {
    private final Browser browser;
    @Getter
    private WebDriver driver;
    @Getter
    public final TestReport testReport;



    //TODO move these into the TestListener instead of setting them up here!
    public TestBase() {
        browser = new Browser();
        driver = browser.getDriver();
        driver.get(TestConstants.URL);
        testReport = new TestReport(driver);

    }


    @AfterEach
    public void closeDriver() {
        testStep("After step : Close driver");
//        testCaseReport.logScreenShot(Status.INFO);
        testReport.logScreenShot64(Status.INFO);
        TestReport.assertAllAndFlushReport(TestListener.getReportRef().get(), getDriver());
        browser.removeDriver();
    }


    public void testStep(String message) {
        testReport.logTestStep("<b><font size='3'>" + message + "</font></b>");
        log.info(message);
    }


}
