package pages;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import testReport.TestReport;

public class MainPage extends Page {

    @FindBy(xpath = "//*[@id='title']")
    public WebElement mainTitle;

    @FindBy(id = "about-me")
    public WebElement aboutMeBtn;

    @FindBy(id = "my-work")
    public WebElement myWorkBtn;

    @FindBy(id = "worked-at")
    public WebElement workedAtBtn;

    @FindBy(id = "info")
    public WebElement infoBtn;

    public MainPage(WebDriver driver, TestReport testReport) {
        super(driver, testReport);
    }

    public MainPage checkTitleIsVisible(boolean isVisible) {
        testReport.assertEquals(isVisible, waitForElementVisibility(mainTitle), "main title is displayed");
        return this;
    }

    public MainPage checkTitleTxt(String text) {
        waitForElementVisibility(mainTitle);
        testReport.assertEquals(text, mainTitle.getText(), "main title has same Text");
        return this;
    }

    public MainPage clickAboutMeBtn() {
        click(aboutMeBtn);
        return this;
    }



    public MainPage clickMyWorkBtn() {
        click(myWorkBtn);
        return this;
    }

    public MainPage clickWorkedAtBtn() {
        click(workedAtBtn);
        return this;
    }

    public MainPage clickInfoBtn() {
        click(infoBtn);
        return this;
    }

    public MainPage checkAboutMeBtnAfterClickIsActive(boolean isActive) {
        testReport.assertEquals(isActive, aboutMeBtn.getAttribute("class").contains("active"),
                "about me btn is active");
        checkScrollIsDone();
        testReport.logScreenShot(Status.INFO);
        return this;
    }

    public MainPage checkMyWorkBtnAfterClickIsActive(boolean isActive) {
        testReport.assertEquals(isActive, myWorkBtn.getAttribute("class").contains("active"),
                "My Work btn is active");
        checkScrollIsDone();
        testReport.logScreenShot(Status.INFO);
        return this;
    }

    public MainPage checkWorkedAtBtnAfterClickIsActive(boolean isActive) {
        testReport.assertEquals(isActive, workedAtBtn.getAttribute("class").contains("active"),
                "Worked At btn is active");
        checkScrollIsDone();
        testReport.logScreenShot(Status.INFO);
        return this;
    }

    public MainPage checkInfoBtnAfterClickIsActive(boolean isActive) {
        testReport.assertEquals(isActive, infoBtn.getAttribute("class").contains("active"),
                "info btn is active");
        checkScrollIsDone();
        testReport.logScreenShot(Status.INFO);


        return this;
    }


}
