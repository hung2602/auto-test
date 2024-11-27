package driver;

import helpers.LogHelper;
import io.appium.java_client.android.AndroidDriver;
import org.slf4j.Logger;

public class DriverManager {
    private static Logger logger = LogHelper.getLogger();
    private static final ThreadLocal<AndroidDriver> driver = new ThreadLocal<>();

    private DriverManager() {
    }

    public static AndroidDriver getDriver() {
        return driver.get();
    }

    public static void setDriver(AndroidDriver driver) {
        logger.info("Set driver");
        DriverManager.driver.set(driver);
    }

    public static void quit() {
        logger.info("Quit driver");
        DriverManager.driver.get().quit();
    }
}

