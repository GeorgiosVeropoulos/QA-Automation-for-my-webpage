package com.example.tryingoutaqua;

import com.example.tryingoutaqua.base.TestBase;
import constants.TestConstants;
import helpers.Sleeper;
import org.junit.jupiter.api.Test;

public class SauceDemoTest extends TestBase {


    @Test
    public void sauceDemoTest() {
        setTestName("sauce demo test", 2);
        testStep("Sauce demo testStep");

        testCaseReport.softAssertEquals(1, 2,"is 1 equal to 2?");

        testCaseReport.assertEquals(1, 1, "1 is equal to 1");

        Sleeper.sleepInMillis(TestConstants.FIVE_SECONDS);
    }
}
