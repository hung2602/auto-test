package utilities;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import driver.DriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class ScreenShot {
    public static final String PATH_TO_IMG = System.getProperty("user.dir") + File.separator + "img" + File.separator;
    public void takeScreenshot(String filename, String name) throws IOException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        LocalDateTime now = LocalDateTime.now();
        String fileName = filename + "-" + now.format(formatter) + ".png";
        File sourceFile = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(sourceFile, new File(PATH_TO_IMG + fileName));
    }
}
