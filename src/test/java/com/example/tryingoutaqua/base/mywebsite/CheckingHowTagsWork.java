package com.example.tryingoutaqua.base.mywebsite;

import Listeners.TestInfo;
import helpers.Sleeper;
import org.junit.jupiter.api.Tag;

public class CheckingHowTagsWork extends MyWebSiteBase{


    @TestInfo(name = "test1", id = 1)
    @Tag("testGroup")
    public void test1() {
        testStep("test1");
        Sleeper.sleepInSeconds(2);
    }

    @TestInfo(name = "test2", id = 2)
    @Tag("testGroup")
    public void test2() {
        testStep("test2");
        Sleeper.sleepInSeconds(2);
    }

    @TestInfo(name = "test3", id = 3)
    @Tag("testGroup")
    public void test3() {
        testStep("test3");
        Sleeper.sleepInSeconds(2);
    }

    @TestInfo(name = "test4", id = 4)
    @Tag("testGroup")
    public void test4() {
        testStep("test4");
        Sleeper.sleepInSeconds(2);
    }
}
