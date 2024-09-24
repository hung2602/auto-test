package core;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Link;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utilities.ScreenShot;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import io.appium.java_client.android.AndroidDriver;
import javax.xml.crypto.Data;

import static core.DataBase.con;
import static core.MyListener.saveScreenshotPNG;
import static core.MyListener.saveTextLog;
import java.sql.Connection;
import org.testng.Assert;

public class BaseTest {
    protected KeywordWeb keyword;
    public BaseTest() {
        keyword = new KeywordWeb();
    }
    public static AndroidDriver driver;
    public DataBase dataBase = new DataBase();

    @BeforeSuite
    public void setFile(){
        PropertiesFile.setPropertiesFile();
    }
    public void setUp(String platFrom, String platformVersion, String name) throws Exception {
        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability("platformName", platFrom);
        dc.setCapability("platformVersion", platformVersion);
        dc.setCapability("deviceName", name);
        dc.setCapability("automationName", "UiAutomator2");
        dc.setCapability("noReset", true);
        dc.setCapability("appWaitForLaunch", false);
        dc.setCapability("app", System.getProperty("user.dir") + "\\app\\onplus-dev-8074005.apk");
        URL url = new URL("http://127.0.0.1:4723/wd/hub");
        driver = new AndroidDriver(url, dc);
//        dataBase.setUpDB("DB_URL","DB_USER","DB_PASSWORD");
    }
    @BeforeTest(alwaysRun = true)
    @Parameters({"platformName","platformVersion","deviceName"})
    public void setUpDevice(String platformName, String platformVersion, String name) throws Exception{
        setUp(platformName, platformVersion, name);
    }
    @AfterTest
    public void afterTest() throws Exception {
//        keyword.closeBrowser();
//        driver.quit();
        if (con != null) {
            con.close();
        }
    }
    @AfterMethod
    public void tearDown(ITestResult testResult) {
        if (testResult.getStatus() == ITestResult.SUCCESS) {
            saveScreenshotPNG();
        }
    }
}
