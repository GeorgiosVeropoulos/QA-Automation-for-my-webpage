package pages;

import com.aventstack.extentreports.Status;
import constants.TestConstants;
import helpers.Sleeper;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import testCaseReport.TestCaseReport;

import java.time.Duration;

import static constants.TestConstants.DEFAULT_WAIT;

/** Page class for other pages to extend from.
 *  Constructor needs both the active driver and testCaseReport in order to log everything.
 *  Contains methods for basic functionality in automation.
 *
 */

public class Page {

    public TestCaseReport testCaseReport;
    public WebDriver driver;


    public Page(WebDriver driver, TestCaseReport testCaseReport) {
        this.driver = driver;
        this.testCaseReport = testCaseReport;
        PageFactory.initElements(driver, this);
    }


    public void click(WebElement element) {
        waitForElementClickable(element);
        element.click();
        testCaseReport.logMessage(Status.INFO, "element clicked " + getByFromElement(element));
    }

    public void scrollToElement(WebElement element) {
        waitForElementVisibility(element);
        Actions actions = new Actions(driver);
        actions.scrollToElement(element).build().perform();
    }


    public boolean waitForElementClickable(WebElement element) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
        long currentTime = System.currentTimeMillis();
        FluentWait<WebDriver> fluentWait = new FluentWait<>(driver);
        try {
            fluentWait.withTimeout(Duration.ofMillis(DEFAULT_WAIT))
                    .pollingEvery(Duration.ofMillis(500L))
                    .ignoring(StaleElementReferenceException.class)
                    .ignoring(NullPointerException.class)
                    .ignoring(NoSuchElementException.class)
                    .until(ExpectedConditions.elementToBeClickable(element));
        } catch (TimeoutException timeoutException) {
            testCaseReport.logMessage(Status.INFO, element + " wasn't clickable after 30 seconds");
        }
        long endTime = System.currentTimeMillis();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(DEFAULT_WAIT));
        return endTime - currentTime <= DEFAULT_WAIT;
    }

    public boolean waitForElementVisibility(WebElement element) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
        long currentTime = System.currentTimeMillis();
        FluentWait<WebDriver> fluentWait = new FluentWait<>(driver);
        try {
            fluentWait.withTimeout(Duration.ofMillis(DEFAULT_WAIT))
                    .pollingEvery(Duration.ofMillis(500L))
                    .ignoring(StaleElementReferenceException.class)
                    .ignoring(NullPointerException.class)
                    .ignoring(NoSuchElementException.class)
                    .until(ExpectedConditions.visibilityOf(element));
        } catch (TimeoutException timeoutException) {
            testCaseReport.logMessage(Status.INFO, element + " wasn't visible after 30 seconds");
        }
        long endTime = System.currentTimeMillis();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(DEFAULT_WAIT));
        return endTime - currentTime <= DEFAULT_WAIT;
    }


    private By getByFromElement(WebElement element) {
        By by = null;
        //[[ChromeDriver: chrome on XP (d85e7e220b2ec51b7faf42210816285e)] -> xpath: //input[@title='Search']]
        String[] pathVariables = (element.toString().split("->")[1].replaceFirst("(?s)(.*)\\]", "$1" + "")).split(":");

        String selector = pathVariables[0].trim();
        String value = pathVariables[1].trim();

        switch (selector) {
            case "id":
                by = By.id(value);
                break;
            case "className":
                by = By.className(value);
                break;
            case "tagName":
                by = By.tagName(value);
                break;
            case "xpath":
                by = By.xpath(value);
                break;
            case "css selector":
                by = By.cssSelector(value);
                break;
            case "linkText":
                by = By.linkText(value);
                break;
            case "name":
                by = By.name(value);
                break;
            case "partialLinkText":
                by = By.partialLinkText(value);
                break;
            default:
                throw new IllegalStateException("locator : " + selector + " not found!!!");
        }
        return by;
    }

    public void checkScrollIsDone() {
        long initialScrollPos = (Long) ((JavascriptExecutor) driver).executeScript("return window.scrollY;");
        // Periodically check the scroll position
        for (int i = 0; i < 10; i++) {  // Adjust the number of iterations as needed
            Sleeper.sleepInMillis(TestConstants.HALF_SECOND);
            long currentScrollPos = (Long) ((JavascriptExecutor) driver).executeScript("return window.scrollY;");
            if (currentScrollPos == initialScrollPos) {
                testCaseReport.logMessage(Status.INFO, "scrolling was done!");
                break;
            }
            initialScrollPos = currentScrollPos;
        }
    }
}
