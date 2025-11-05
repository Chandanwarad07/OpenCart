package utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.apache.commons.io.FileUtils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testCase.BaseClass;

public class ExtentReportManager implements ITestListener {

    public ExtentSparkReporter sparkReporter;
    public ExtentReports extent;
    public static ExtentTest test;

    String repName;

    public void onStart(ITestContext testContext) {
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        repName = "Test-Report-" + timeStamp + ".html";

        sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/reports/" + repName);
        sparkReporter.config().setDocumentTitle("Opencart Automation Report");
        sparkReporter.config().setReportName("Opencart Functional Testing");
        sparkReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Application", "Opencart");
        extent.setSystemInfo("Module", "Admin");
        extent.setSystemInfo("Sub Module", "Customers");
        extent.setSystemInfo("Tester Name", "Chandan Kumar");
        extent.setSystemInfo("Environment", "QA");
    }

    public void onTestSuccess(ITestResult result) {
        test = extent.createTest(result.getName());
        test.log(Status.PASS, "Test Passed");
    }

    public void onTestFailure(ITestResult result) {
        test = extent.createTest(result.getName());
        test.log(Status.FAIL, "Test Failed: " + result.getThrowable());

        // Capture screenshot on failure
        Object testClass = result.getInstance();
        WebDriver driver = ((BaseClass) testClass).getDriver(); // <-- get driver from BaseClass

        try {
            String screenshotPath = captureScreenshot(driver, result.getName());
            test.addScreenCaptureFromPath(screenshotPath); // attach screenshot in report
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onTestSkipped(ITestResult result) {
        test = extent.createTest(result.getName());
        test.log(Status.SKIP, "Test Skipped");
    }

    public void onFinish(ITestContext testContext) {
        extent.flush();
    }

    // =========================
    // 📸 Screenshot Method
    // =========================
    public String captureScreenshot(WebDriver driver, String tname) throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

        String targetFilePath = System.getProperty("user.dir") + "/screenshots/" + tname + "_" + timeStamp + ".png";
        File targetFile = new File(targetFilePath);

        FileUtils.copyFile(sourceFile, targetFile);
        return targetFilePath;
    }
}

