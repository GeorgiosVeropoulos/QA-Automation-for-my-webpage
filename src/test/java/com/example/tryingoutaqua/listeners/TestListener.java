package com.example.tryingoutaqua.listeners;

import Listeners.TestInfo;
import com.example.tryingoutaqua.base.TestBase;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.AfterAllCallback;
import testReport.TestReport;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

/**
 * Test Listener to be used in TestBase.
 * TODO add more implementations for after Test finishes etc.
 */
@Slf4j
public class TestListener implements BeforeEachCallback, AfterAllCallback {

    @Override
    public void beforeEach(ExtensionContext context) {
        String testMethodName = context.getRequiredTestMethod().getName();
        // You can perform any setup actions here
        log.info("Setting up for test: {}", testMethodName);
        TestInfo testInfo = context.getRequiredTestMethod().getAnnotation(TestInfo.class);

        // Store the test method name for later use
        // the idea is to add here code for Tuskr, Testrail etc.
        context.getStore(ExtensionContext.Namespace.GLOBAL).put("testMethodName", testMethodName);
        TestReport.setTestName(TestBase.getReportRef().get(), testInfo.id() + " : " + testMethodName);
    }

    @Override
    public void afterAll(ExtensionContext context) throws Exception {
        //upload to TestRail, S3
    }
}
