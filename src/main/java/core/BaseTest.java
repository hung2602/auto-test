package core;
import helpers.PathHelper;
import helpers.PropertiesFile;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
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
import java.time.Duration;
import java.util.HashMap;

import static helpers.MyListener.saveScreenshotPNG;
import static helpers.DataBase.con;
import static helpers.PathHelper.*;
import static utilities.XmlParse.*;

public class BaseTest {
    private static Logger logger = LogHelper.getLogger();

    protected KeywordWeb keyword;
    public static AndroidDriver driver;
    public static IOSDriver iosDriver;
    public static String appName = PathHelper.getFileName("app");
    private String userName = "ccng_C7EsqA";
    private  String accessKey =  "iPqpMsa525Z8L6xzhwGs";
    public BaseTest() {
        keyword = new KeywordWeb();
    }

    @BeforeSuite(alwaysRun=true)
    public void setFile(){
        PropertiesFile.setPropertiesFile();
        try {
            if (PropertiesFile.getPropValue("OVER_WRITE_REPORT").equals("YES")) {
                FileUtils.deleteDirectory(new File("target\\allure-results"));
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
        dc.setCapability("app", projectPath + "app\\" + appName);
        URL url = new URL("http://127.0.0.1:4723/wd/hub");
        driver = new AndroidDriver(url, dc);
    }
    public void setUpBrowseStack(String platformName, String platformVersion, String name) throws Exception {
        HashMap<String, Object> browserstackOptions = new HashMap<>();
        browserstackOptions.put("userName", userName);
        browserstackOptions.put("accessKey", accessKey);
        URL remoteUrl = new URL("http://hub.browserstack.com/wd/hub");
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("appium:deviceName", name);
        desiredCapabilities.setCapability("appium:os_version", platformVersion);
        desiredCapabilities.setCapability("platformName", platformName);
        desiredCapabilities.setCapability("appium:app", "bs://5ec4ce0869051e2bcc8f2ab8b8d0ead1b868b950");
        desiredCapabilities.setCapability("networkLogs", "true");
        desiredCapabilities.setCapability("bstack:options", browserstackOptions);
        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
//        UiAutomator2Options options = new UiAutomator2Options();
//        driver = new AndroidDriver(new URL("http://hub-cloud.browserstack.com/wd/hub"), options);

    }
    @BeforeTest(alwaysRun = true)
    @Parameters({"platformName","platformVersion","deviceName"})
    public void setUpDevice(String platFrom, String platformVersion, String name) throws Exception{
        setUpBrowseStack(platFrom, platformVersion, name);
//        setUp(platFrom, platformVersion, name);
    }
    @AfterTest
    public void afterTest() throws Exception {
        if(con != null){
            con.close();
        }
        driver.quit();
    }
    @AfterMethod
    public void tearDown(ITestResult testResult) {
        if (testResult.getStatus() == ITestResult.FAILURE) {
            saveScreenshotPNG();
        }
    }
}
