package testReport;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class TestReport {

    @Getter
    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    private final WebDriver driver;

    private static List<AssertionError> errors = new ArrayList<>();

    public TestReport(WebDriver driver) {
        this.driver = driver;
    }

    public void logMessage(Status status, String message) {
        extentTest.get().log(status, message);
        log.info(message);
    }

    public void logTestStep(String message) {
        extentTest.get().log(Status.INFO, message);
    }

    public void assertEquals(Object expected, Object actual, String message) {
        try {
            Assertions.assertEquals(expected, actual, message);
            logMessage(Status.PASS, message + " expected: " + expected + " actual: " + actual);
        } catch (AssertionError e) {
            logMessage(Status.FAIL, message + " expected: " + expected + " actual: " + actual);
//            logScreenShot(Status.INFO);
            throw e;
        }
    }

    public void softAssertEquals(Object expected, Object actual, String message) {
        try {
            Assertions.assertEquals(expected, actual, message);
            logMessage(Status.PASS, message + " expected: " + expected + " actual: " + actual);
        } catch (AssertionError e) {
            logMessage(Status.FAIL, message + " expected: " + expected + " actual: " + actual);
//            logScreenShot(Status.INFO);
            errors.add(e);
        }
    }


    public static void assertAllAndFlushReport(ExtentReports report, WebDriver driver){
        if (!errors.isEmpty()) {
            StringBuilder errorMessage = new StringBuilder("Soft assert failed:\n");
            for (AssertionError error : errors) {
                String local = error.getLocalizedMessage();
                errorMessage.append(error.getMessage()).append("\n");
            }
            errors.clear();
            report.flush();
            driver.quit();
            throw new AssertionError(errorMessage.toString());
        }
        report.flush();
        driver.quit();
    }



    public void logHover(WebDriver driver, WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].style.border='3px solid red'", element);
    }

    public void logScreenShot(Status status) {
        synchronized (TestReport.class) {
            extentTest.get().log(status, MediaEntityBuilder.createScreenCaptureFromPath(createScreenShot()).build());
        }
    }

    public void logScreenShot64(Status status) {
        synchronized (TestReport.class) {
            extentTest.get().log(status, MediaEntityBuilder.createScreenCaptureFromBase64String(base64ScreenShot()).build());
        }
    }

    private String createScreenShot() {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String fileName = System.currentTimeMillis() + ".png";
        try {
            String path = Paths.get(System.getProperty("user.dir"), "target", "images", fileName).toString();
            FileUtils.copyFile(screenshot, new File(path));
        } catch (IOException e) {
            logMessage(Status.WARNING, "IOException : " + e);
        }
        //this is done in order to go back to the correct path because of how the report is generated under target dir.
        // basically the path will go from-------------------> TO
        // user.dir + target + reports + test + report.html ->  /tryingoutAqua/target/images/THE_IMAGE.PNG
        return Paths.get("../../", "images", fileName).toString();
    }

    private String base64ScreenShot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
    }

}
