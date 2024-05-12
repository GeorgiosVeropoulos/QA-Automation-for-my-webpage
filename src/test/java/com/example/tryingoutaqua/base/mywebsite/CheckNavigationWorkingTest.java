package com.example.tryingoutaqua.base.mywebsite;

import Listeners.TestInfo;
import constants.TestConstants;
import helpers.PropertiesManager;
import helpers.Sleeper;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;

public class CheckNavigationWorkingTest extends MyWebSiteBase {


//    public static final String n = TestConstants.n;

    @TestInfo(name = "check Navigation working", id = 4)
//    @Tags({@Tag("all"), @Tag("myPage")})
    public void checkNavigationWorkingTest() {
//        System.out.println(n);
        testStep("hello world!");


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
