package com.example.tryingoutaqua.base.mywebsite;

import Listeners.TestInfo;
import com.example.tryingoutaqua.base.TestBase;
import helpers.Sleeper;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

public class CheckNavigationWorkingTest extends MyWebSiteBase {


    @TestInfo(name = "check Navigation working", id = 5)
    @Tags({@Tag("all"), @Tag("myPage")})
    public void checkNavigationWorkingTest() {


        testStep("Do clicks for all navigation btns");
        Sleeper.sleepInSeconds(2);
//        pages.mainPage.clickInfoBtn()
//                .checkInfoBtnAfterClickIsActive(true)
//                .clickMyWorkBtn()
//                .checkMyWorkBtnAfterClickIsActive(true)
//                .clickAboutMeBtn()
//                .checkAboutMeBtnAfterClickIsActive(true)
//                .clickWorkedAtBtn()
//                .checkWorkedAtBtnAfterClickIsActive(true);
//
//        testCaseReport.softAssertEquals(1,1,"1 is equal to 1");
    }

}
