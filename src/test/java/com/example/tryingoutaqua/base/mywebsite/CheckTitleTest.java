package com.example.tryingoutaqua.base.mywebsite;

import Listeners.TestInfo;
import helpers.Sleeper;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;

public class CheckTitleTest extends MyWebSiteBase {



    @TestInfo(name = "check Title Test 1", id = 1)
    @Tags({@Tag("all"), @Tag("myPage"), @Tag("parallel")})
    public void checkTitleTest() {
        Sleeper.sleepInSeconds(1);
        testStep("Test 1");
        pages.mainPage
                .checkTitleIsVisible(true)
                .checkTitleTxt("QA Engineer / Games Programmer");
    }

    @TestInfo(name = "check Title Test 2", id = 2)
    @Tags({@Tag("all"), @Tag("myPage"), @Tag("parallel")})
    public void checkTitleTest2() {
        Sleeper.sleepInSeconds(1);
        testStep("Test 2");
        pages.mainPage
                .checkTitleIsVisible(true)
                .checkTitleTxt("QA Engineer / Games Programmer");
    }
}
