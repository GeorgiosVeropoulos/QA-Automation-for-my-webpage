package com.example.tryingoutaqua.base.mywebsite;

import com.example.tryingoutaqua.base.TestBase;
import helpers.Sleeper;
import org.junit.jupiter.api.Test;

public class CheckNavigationWorkingTest extends TestBase {

    public CheckNavigationWorkingTest() {}

    @Test
    public void checkNavigationWorkingTest() {
        setTestName("Check navigation working ", 3);


        testStep("Do clicks for all navigation btns");
        pages.mainPage.clickInfoBtn()
                .checkInfoBtnAfterClickIsActive(true)
                .clickMyWorkBtn()
                .checkMyWorkBtnAfterClickIsActive(true)
                .clickAboutMeBtn()
                .checkAboutMeBtnAfterClickIsActive(true)
                .clickWorkedAtBtn()
                .checkWorkedAtBtnAfterClickIsActive(true);

        testCaseReport.softAssertEquals(1,1,"1 is equal to 1");
    }

}
