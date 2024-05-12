package constants;

import helpers.PropertiesManager;

public class TestConstants {

    public static final int HALF_SECOND = 500;
    public static final int FIVE_SECONDS = 5_000;
    public static final int TEN_SECONDS = 10_000;
    public static final int DEFAULT_WAIT = 30_000;

    public static final String URL = PropertiesManager.getEnvironmentProperty("url");

    public static final String n = PropertiesManager.getEnvironmentProperty("n");
//    public static final String BACK_TO_TARGET_ROOT = "../../";
}
