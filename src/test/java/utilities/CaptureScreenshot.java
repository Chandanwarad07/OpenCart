package utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class CaptureScreenshot {

    public static String capture(WebDriver driver, String testName) {

        // Create timestamp
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        // Screenshot path
        String destPath = System.getProperty("user.dir") + "/screenshots/" + testName + "_" + timestamp + ".png";

        // Capture screenshot
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        try {
            FileUtils.copyFile(srcFile, new File(destPath));
        } catch (IOException e) {
            System.out.println("⚠️ Failed to save screenshot: " + e.getMessage());
        }

        return destPath;
    }
}

