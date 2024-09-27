package helpers;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.slf4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utilities.LogHelper;
import static core.BaseTest.driver;

public class MyListener implements ITestListener {
    private static final Logger logger = LogHelper.getLogger();

    @Attachment(value = "{0}", type = "text/plain")
    public static String saveTextLog(String message) {
        return message;
    }
    @Attachment(value = "Page screenshot", type = "image/png")
    public static byte[] saveScreenshotPNG() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
    public String getTestName(ITestResult result) {
        return result.getTestName() != null ? result.getTestName() : result.getMethod().getConstructorOrMethod().getName();
    }
    public String getTestDescription(ITestResult result) {
        return result.getMethod().getDescription() != null ? result.getMethod().getDescription() : getTestName(result);
    }
    @Override
    public void onStart(ITestContext result) {
//        PropertiesHelper.loadAllFiles();
        //Khởi tạo report (Extent và Allure)
    }

    @Override
    public void onFinish(ITestContext result) {
        logger.info("End testing " + result.getName());
    }

    @Override
    public void onTestStart(ITestResult result) {
        logger.info("Running test case " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logger.info("Test case " + result.getName() + " is passed.");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        logger.error("Test case " + result.getName() + " is failed.");
        //CaptureHelper.captureScreenshot(result.getName());
        logger.error(result.getThrowable().toString());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        logger.error("Test case " + result.getName() + " is skipped.");
        logger.error(result.getThrowable().toString());
    }
}

