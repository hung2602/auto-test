package core;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.net.URL;

import static core.MyListener.saveScreenshotPNG;

public class BaseTest {
    protected KeywordWeb keyword;
    public BaseTest() {
        keyword = new KeywordWeb();
    }
    public static AndroidDriver driver;
    public DataBaseTest dataBase = new DataBaseTest();

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
        dc.setCapability("noReset", false);
        dc.setCapability("appWaitForLaunch", false);
        dc.setCapability("app", System.getProperty("user.dir") + "\\app\\onplus-dev-8074005.apk");
        URL url = new URL("http://127.0.0.1:4723/wd/hub");
        driver = new AndroidDriver(url, dc);
//        dataBase.setUpDB("POSTGRES_DB_URL","POSTGRES_DB_USER","POSTGRES_DB_PASSWORD");
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
        if (dataBase.con != null) {
            dataBase.con.close();
        }
    }
    @AfterMethod
    public void tearDown(ITestResult testResult) {
//        if (testResult.getStatus() == ITestResult.SUCCESS) {
//            saveScreenshotPNG();
//        }
        saveScreenshotPNG();
    }
}
