package com.example.tryingoutaqua.listeners;

import Listeners.TestInfo;
import com.example.tryingoutaqua.base.TestBase;
import helpers.PropertiesManager;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.*;
import testReport.TestReport;

import java.util.HashMap;

/**
 * Test Listener to be used in TestBase.
 * TODO add more implementations for after Test finishes etc.
 */
@Slf4j
public class TestListener implements BeforeEachCallback, AfterAllCallback, BeforeAllCallback, AfterEachCallback {

    private HashMap<String, Integer> testsRun;

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        testsRun = new HashMap<>();
        new PropertiesManager();
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
        TestReport.setTestName(TestBase.getReportRef().get(), testInfo.id() + " : " + testMethodName);
    }

    @Override
    public void afterEach(ExtensionContext context) throws Exception {
    }

    @Override
    public void afterAll(ExtensionContext context) throws Exception {
        //upload to TestRail, S3
//        log.info("Tests executed were:");
//        for (Map.Entry<String, Integer> entry : testsRun.entrySet()) {
//            log.info("Test: {} : {}", entry.getKey(), entry.getValue());
//        }
    }


}
