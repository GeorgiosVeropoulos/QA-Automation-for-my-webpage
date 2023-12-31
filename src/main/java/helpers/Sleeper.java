package helpers;


import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


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
        log.info("slept for " + duration + " in milliSeconds");
    }
}
