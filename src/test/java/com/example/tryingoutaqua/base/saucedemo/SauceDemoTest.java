package com.example.tryingoutaqua;

import Listeners.TestInfo;
import com.example.tryingoutaqua.base.TestBase;
import constants.TestConstants;
import helpers.Sleeper;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import javax.annotation.concurrent.ThreadSafe;

public class SauceDemoTest extends TestBase {



    @TestInfo(name = "sauce demo Test", id = 3)
    @Tags({@Tag("sauceDemo"), @Tag("sauceDemo2"), @Tag("all")})
    public void sauceDemoTest() {
        testStep("Sauce demo testStep");
        Sleeper.sleepInSeconds(8);
//        testCaseReport.softAssertEquals(1, 2,"is 1 equal to 2?");
//
//        testCaseReport.assertEquals(1, 1, "1 is equal to 1");
//
//        Sleeper.sleepInMillis(TestConstants.FIVE_SECONDS);
    }
}
