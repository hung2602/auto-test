package core;
import helpers.PropertiesFile;
import io.appium.java_client.android.AndroidDriver;
import keyword.KeywordWeb;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utilities.LogHelper;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import static helpers.MyListener.saveScreenshotPNG;

public class BaseTest {
    private static Logger logger = LogHelper.getLogger();
    protected KeywordWeb keyword;
    public static AndroidDriver driver;
    public BaseTest() {
        keyword = new KeywordWeb();
    }
    @BeforeSuite
    public void setFile(){
        PropertiesFile.setPropertiesFile();
        try {
            if (PropertiesFile.getPropValue("OVER_WRITE_REPORT").equals("YES")) {
                FileUtils.deleteDirectory(new File("allure-results"));
                logger.info("Deleted directory allure-results");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
    }
    @BeforeTest(alwaysRun = true)
    @Parameters({"platformName","platformVersion","deviceName"})
    public void setUpDevice(String platformName, String platformVersion, String name) throws Exception{
        setUp(platformName, platformVersion, name);
    }
    @AfterTest
    public void afterTest() throws Exception {
//        driver.quit();
//        if (dataBase.con != null) {
//            dataBase.con.close();
//        }
    }
    @AfterMethod
    public void tearDown(ITestResult testResult) {
//        if (testResult.getStatus() == ITestResult.SUCCESS) {
            saveScreenshotPNG();
//        }
    }
}
