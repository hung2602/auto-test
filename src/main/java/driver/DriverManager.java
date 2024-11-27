package driver;

import io.appium.java_client.android.AndroidDriver;

public class DriverManager {

    private static final ThreadLocal<AndroidDriver> driver = new ThreadLocal<>();

    private DriverManager() {
    }

    public static AndroidDriver getDriver() {
        return driver.get();
    }

    public static void setDriver(AndroidDriver driver) {
        DriverManager.driver.set(driver);
    }

    public static void quit() {
        DriverManager.driver.get().quit();
    }
}

