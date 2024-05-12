package com.example.tryingoutaqua.listeners;

import Listeners.TestInfo;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.example.tryingoutaqua.base.TestBase;
import helpers.PropertiesManager;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.*;
import testReport.TestReport;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Test Listener to be used in TestBase.
 * TODO add more implementations for after Test finishes etc.
 */
@Slf4j
public class TestListener implements BeforeEachCallback, AfterAllCallback, BeforeAllCallback, AfterEachCallback {

    private HashMap<String, Integer> testsRun;
    @Getter
    private static final AtomicReference<ExtentReports> reportRef = new AtomicReference<>();


    @Override
    public void beforeAll(ExtensionContext context) {
        testsRun = new HashMap<>();
        new PropertiesManager();

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


    @Override
    public void beforeEach(ExtensionContext context) {

        String testMethodName = context.getRequiredTestMethod().getName();
        int testId = context.getRequiredTestMethod().getAnnotation(TestInfo.class).id();
        // You can perform any setup actions here
        log.info("Setting up for test: {}", testMethodName);
        TestInfo testInfo = context.getRequiredTestMethod().getAnnotation(TestInfo.class);

        testsRun.put(testMethodName, testId);
        // Store the test method name for later use
        // the idea is to add here code for Tuskr, Testrail etc.
        context.getStore(ExtensionContext.Namespace.GLOBAL).put(testMethodName, testId);
        //We create a test for each Test!
        TestReport.getExtentTest().set(reportRef.get().createTest(testInfo.id() + " : " + testMethodName));

        //We add the Tags to introduce filters with the categories of Extent Report.
        for (String tag : context.getTags()) {
            TestReport.getExtentTest().get().assignCategory(tag);
        }

    }

    @Override
    public void afterEach(ExtensionContext context) {
    }

    @Override
    public void afterAll(ExtensionContext context) {
       log.info(testsRun.values().toString());
        //upload to TestRail, S3
//        log.info("Tests executed were:");
//        for (Map.Entry<String, Integer> entry : testsRun.entrySet()) {
//            log.info("Test: {} : {}", entry.getKey(), entry.getValue());
//        }
    }


}
