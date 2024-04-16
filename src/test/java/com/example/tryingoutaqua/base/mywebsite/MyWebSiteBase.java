package com.example.tryingoutaqua.base.mywebsite;

import com.example.tryingoutaqua.base.TestBase;
import factory.PageFactory;

/**
 * Base class for storing pagefactory and basic methods
 */
public class MyWebSiteBase extends TestBase {

    public PageFactory pages;

    public MyWebSiteBase() {
        pages = new PageFactory(getDriver(), getTestReport());
    }
}
