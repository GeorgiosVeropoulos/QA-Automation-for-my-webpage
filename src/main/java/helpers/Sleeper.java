package helpers;


import lombok.extern.slf4j.Slf4j;


@Slf4j
public class Sleeper {

    public static void sleepInSeconds(int duration) {
        sleepInMillis(duration * 1000);
    }

    public static void sleepInMillis(int duration) {
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            log.info(e.getMessage());
        }
        log.info("slept for {} in milliSeconds", duration);
    }
}
