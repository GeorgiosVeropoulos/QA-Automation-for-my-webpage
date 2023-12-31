package com.example.tryingoutaqua.base.mywebsite;

import com.example.tryingoutaqua.base.TestBase;
import org.junit.jupiter.api.Test;

public class CheckTitleTest extends TestBase {


    public CheckTitleTest() {}


    @Test
    public void checkTitleTest() {
        setTestName("Check Title Test", 1);

        pages.mainPage
                .checkTitleIsVisible(true)
                .checkTitleTxt("QA Engineer / Games Programmer");
    }
}
