package core;
import helpers.PathHelper;
import helpers.PropertiesFile;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import keyword.KeywordWeb;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.testng.ITestResult;
import org.testng.annotations.*;
import helpers.LogHelper;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

import static helpers.MyListener.saveScreenshotPNG;
import static helpers.DataBase.con;
import static helpers.PathHelper.*;
import static utilities.XmlParse.*;

public class BaseTest {
    private static Logger logger = LogHelper.getLogger();

    protected KeywordWeb keyword;
    public static AndroidDriver driver;
    public static String appName = PathHelper.getFileName("app");
    private String userName = "duy_u6ydwV";
    private  String accessKey =  "2BffbhMipdqx47LAydRi";
    public BaseTest() {
        keyword = new KeywordWeb();
    }

    @BeforeSuite(alwaysRun=true)
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
//        insertInformDevices("PLAT_FORM_VERSION","ID_DEVICE");
    }
    public void setUp(String platFrom, String platformVersion, String name) throws Exception {
        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability("platformName", platFrom);
        dc.setCapability("platformVersion", platformVersion);
        dc.setCapability("deviceName", name);
        dc.setCapability("automationName", "UiAutomator2");
        dc.setCapability("noReset", false);
        dc.setCapability("appWaitForLaunch", false);
        URL url = new URL("http://127.0.0.1:4723/wd/hub");
        driver = new AndroidDriver(url, dc);
    }
    public void setUpBrowseStack() throws Exception {
        HashMap<String, Object> browserstackOptions = new HashMap<String, Object>();
        browserstackOptions.put("userName", userName);
        browserstackOptions.put("accessKey", accessKey);
        browserstackOptions.put("buildName", "$buildName");

        URL remoteUrl = new URL("http://hub.browserstack.com/wd/hub");
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("appium:deviceName", "Google Pixel 7");
        desiredCapabilities.setCapability("appium:os_version", "13.0");
        desiredCapabilities.setCapability("appium:app", "bs://8ee19b5f6eee596f88a01e52651594974b7f08fc");
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("networkLogs", "true");
        desiredCapabilities.setCapability("bstack:options", browserstackOptions);
        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
    }
    @BeforeTest(alwaysRun = true)
    @Parameters({"platformName","platformVersion","deviceName"})
    public void setUpDevice(String platFrom, String platformVersion, String name) throws Exception{
        setUpBrowseStack();
//        setUp(platFrom, platformVersion, name);
    }
    @AfterTest
    public void afterTest() throws Exception {
        if(con != null){
            con.close();
        }
//        driver.quit();
    }
    @AfterMethod
    public void tearDown(ITestResult testResult) {
        if (testResult.getStatus() == ITestResult.FAILURE) {
            saveScreenshotPNG();
        }
    }
}
