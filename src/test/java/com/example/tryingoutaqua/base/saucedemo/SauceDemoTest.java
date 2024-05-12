package com.example.tryingoutaqua.base.saucedemo;

import Listeners.TestInfo;
import com.example.tryingoutaqua.base.TestBase;
import com.example.tryingoutaqua.base.mywebsite.MyWebSiteBase;
import helpers.Sleeper;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;

public class SauceDemoTest extends MyWebSiteBase {



    @TestInfo(name = "sauce demo Test", id = 3)
    @Tags({@Tag("sauceDemo"), @Tag("sauceDemo2"), @Tag("all")})
    public void sauceDemoTest() {
        testStep("Sauce demo testStep");
        Sleeper.sleepInSeconds(8);
        testReport.logTestStep("empty");
//        testCaseReport.softAssertEquals(1, 2,"is 1 equal to 2?");
//
//        testCaseReport.assertEquals(1, 1, "1 is equal to 1");
//
//        Sleeper.sleepInMillis(TestConstants.FIVE_SECONDS);
    }
}
