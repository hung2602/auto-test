package core;
import helpers.PathHelper;
import helpers.PropertiesFile;
import io.appium.java_client.android.AndroidDriver;
import keyword.KeywordWeb;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.testng.ITestResult;
import org.testng.annotations.*;
import helpers.LogHelper;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import static helpers.MyListener.saveScreenshotPNG;
import static helpers.DataBase.con;
import static helpers.PathHelper.*;
import static utilities.XmlParse.*;

public class BaseTest {
    private static Logger logger = LogHelper.getLogger();

    protected KeywordWeb keyword;
    public static AndroidDriver driver;
    public static String appName = PathHelper.getFileName("app");
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
        insertInformDevices("PLAT_FORM_VERSION","ID_DEVICE");
    }
    public void setUp(String platFrom, String platformVersion, String name) throws Exception {
        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability("platformName", platFrom);
        dc.setCapability("platformVersion", platformVersion);
        dc.setCapability("deviceName", name);
        dc.setCapability("automationName", "UiAutomator2");
        dc.setCapability("noReset", true);
        dc.setCapability("appWaitForLaunch", false);
        dc.setCapability("app", projectPath + "app\\" + appName);
        URL url = new URL("http://127.0.0.1:4723/wd/hub");
        driver = new AndroidDriver(url, dc);

    }
    @BeforeTest(alwaysRun = true)
    @Parameters({"platformName","platformVersion","deviceName"})
    public void setUpDevice(String platFrom, String platformVersion, String name) throws Exception{
        setUp(platFrom, platformVersion, name);
    }
    @AfterTest
    public void afterTest() throws Exception {
//        driver.quit();
    }
    @AfterMethod
    public void tearDown(ITestResult testResult) {
//        if (testResult.getStatus() == ITestResult.SUCCESS) {
            saveScreenshotPNG();
//        }
    }
}
