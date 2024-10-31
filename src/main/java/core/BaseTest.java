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
import helpers.ReadYaml;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static helpers.LogCat.*;
import static helpers.MyListener.*;
import static helpers.DataBase.con;
import static helpers.MyListener.logDevices;
import static helpers.PathHelper.*;
import static utilities.XmlParse.insertInformDevices;

public class BaseTest {
    private static Logger logger = LogHelper.getLogger();

    protected KeywordWeb keyword;
    public static AndroidDriver driver;
    public static String appName = PathHelper.getFileName("app");
    private final String app = "bs://8223a66b66c48e4e42c5fc779252d85a829d1bdd";
    private final String userName = "cuongvu_FerjhE";
    private final String accessKey = "idKAyrfQhD8DzT2su7Xe";

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
    public void setUp(String platformName, String platformVersion, String name, String cloudPlatform) throws Exception {
        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability("appium:deviceName", name);
        dc.setCapability("appium:os_version", platformVersion);
        dc.setCapability("platformName", platformName);
        String url = "";
        if (cloudPlatform.equals("browserStack")){
            HashMap<String, Object> browserstackOptions = new HashMap<>();
            browserstackOptions.put("userName", userName);
            browserstackOptions.put("accessKey", accessKey);
            dc.setCapability("app", app);
            dc.setCapability("bstack:options", browserstackOptions);
            url = "http://hub.browserstack.com/wd/hub";
        }
        else {
            dc.setCapability("automationName", "UiAutomator2");
            dc.setCapability("noReset", false);
            dc.setCapability("appWaitForLaunch", false);
            dc.setCapability("app", projectPath + "app\\" + appName);
            url ="http://127.0.0.1:4723/wd/hub";
        }
        driver = new AndroidDriver(new URL(url), dc);
    }
    @BeforeTest(alwaysRun = true)
    @Parameters({"platformName","platformVersion","deviceName","cloudPlatform"})
    public void setUpDevice(String platFrom, String platformVersion, String name, String cloudPlatform) throws Exception{
        setUp(platFrom, platformVersion, name, cloudPlatform);
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
            getLog();
            logDevices(projectPath + "mylog.txt");
        }
    }
}
