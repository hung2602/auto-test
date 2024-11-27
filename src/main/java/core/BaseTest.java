package core;
import com.aventstack.extentreports.gherkin.model.And;
import driver.DriverManager;
import helpers.*;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServerHasNotBeenStartedLocallyException;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import keyword.KeywordWeb;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.testng.ITestResult;
import org.testng.annotations.*;
import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import static helpers.LogCat.*;
import static helpers.MyListener.*;
import static helpers.MyListener.logDevices;
import static helpers.PathHelper.*;
import static utilities.ReadExcel.ExcelOperations;
import static utilities.XmlParse.insertInformDevices;

public class BaseTest {
    protected static AndroidDriver driver;
    protected static Workbook workbook = null;
    private static Logger logger = LogHelper.getLogger();
    protected static KeywordWeb keyword;
    public static String appName = PathHelper.getFileName("app");
    public static String appPath  = projectPath + "app" + File.separator;
    private static String userName = System.getenv("BROWSERSTACK_USERNAME");
    private static String accessKey = System.getenv("BROWSERSTACK_ACCESS_KEY");
    private static String app = System.getenv("BROWSERSTACK_APP");
//    private static String userName = "cuongvu_FerjhE";
//    private static String accessKey = "idKAyrfQhD8DzT2su7Xe";
//    private static String app = "bs://8223a66b66c48e4e42c5fc779252d85a829d1bdd";
    private final static String userNameBrs = PropertiesFile.getPropValue("userNameBrs");
    private final static String accessKeyBrs = PropertiesFile.getPropValue("accessKeyBrs");
    private final static String appId = PropertiesFile.getPropValue("appIdBrs");

    public BaseTest() {
        keyword = new KeywordWeb();
    }

    public void startAppiumService(String port){
        AppiumServiceBuilder builder = new AppiumServiceBuilder();
        builder.withIPAddress("127.0.0.1");
    }

    @BeforeSuite(alwaysRun=true)
    public void setFile(){
        workbook = ExcelOperations();
        PropertiesFile.setPropertiesFile();
        try {
            if (PropertiesFile.getPropValue("OVER_WRITE_REPORT").equals("YES")) {
                FileUtils.deleteDirectory(new File("target" + File.separator + "allure-results"));
                logger.info("Deleted directory allure-results");
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public AndroidDriver setUp(String cloudPlatform, String version, String udid, String port, String deviceName) throws Exception {
        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability("platformName", "Android");
        dc.setCapability("version", version);
        if (cloudPlatform.equals("browserStack")){
            dc.setCapability("deviceName", deviceName);
            if(userNameBrs!=null && accessKeyBrs!=null && appId!=null){
                userName = userNameBrs;
                accessKey = accessKeyBrs;
                app = appId;
            }
            HashMap<String, Object> browserstackOptions = new HashMap<>();
            browserstackOptions.put("userName", userName);
            browserstackOptions.put("accessKey", accessKey);
            dc.setCapability("app", app);
            dc.setCapability("bstack:options", browserstackOptions);
            driver = new AndroidDriver(new URL("http://hub.browserstack.com/wd/hub"), dc);
        }
        else {
            dc.setCapability("udid", udid);
            dc.setCapability("automationName", "UiAutomator2");
//            dc.setCapability("app",  appPath + appName);
            dc.setCapability("noReset", true);
            dc.setCapability("appWaitForLaunch", false);
            dc.setCapability("appPackage", "com.vtvcab.onsports.dev");
            dc.setCapability("appActivity", "com.vtvcab.onsports.feature.main.activity.MainActivity");
            driver = new AndroidDriver(new URL("http://127.0.0.1:" + port + "/wd/hub"), dc);
        }
        return driver;
    }
    @BeforeTest
    @Parameters({"cloudPlatform","version","udid","port","deviceName"})
    public void setUpDevice(String cloudPlatform, String version, String udid, String port, String deviceName) throws Exception{
//        checkIfServerIsRunning(Integer.valueOf(port));
        DriverManager.setDriver(setUp(cloudPlatform, version, udid, port, deviceName));
//        setUpBrowserStack();
    }
    @AfterTest
    public void afterTest() throws Exception {
//        if(con != null){
//            con.close();
//        }
        DriverManager.quit();
    }
    @AfterMethod
    public void tearDown(ITestResult testResult) {
        if (testResult.getStatus() == ITestResult.FAILURE) {
            saveScreenshotPNG();
            getLog();
            logDevices(projectPath + "mylog.txt");
        }
    }
    public boolean checkIfServerIsRunning(int port) {
        boolean isServerRunning = false;
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(port);
            serverSocket.close();
        } catch (IOException e) {
            isServerRunning = true;
        } finally {
            serverSocket = null;
        }
        return isServerRunning;
    }
}
