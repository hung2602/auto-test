package helpers;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.slf4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static core.BaseTest.driver;
import static helpers.PathHelper.*;
import static helpers.LogCat.*;

public class MyListener implements ITestListener {
    private static final Logger logger = LogHelper.getLogger();

    @Attachment(type = "text/plain")
    public static byte[] logDevices(String filePath) {
        Path path = Paths.get(filePath);
        try {
            return Files.readAllBytes(path);
        } catch (IOException e) {
            return new byte[0];
        }
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
        //Khởi tạo report (Extent và Allure)
    }

    @Override
    public void onFinish(ITestContext result) {
        logger.info("End testing " + result.getName());
    }

    @Override
    public void onTestStart(ITestResult result) {
        logger.info("Running test case " + result.getName());
        removeLog();
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logger.info("Test case " + result.getName() + " is passed.");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        logger.error("Test case " + result.getName() + " is failed.");
        logger.error(result.getThrowable().toString());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        logger.error("Test case " + result.getName() + " is skipped.");
        logger.error(result.getThrowable().toString());
    }
}

