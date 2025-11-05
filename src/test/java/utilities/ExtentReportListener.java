package utilities;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testCase.BaseClass;
import org.openqa.selenium.WebDriver;

public class ExtentReportListener implements ITestListener {

    public static ExtentReports extent;
    public static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public void onStart(ITestContext context) {
        ExtentSparkReporter spark = new ExtentSparkReporter("reports/ExtentReport.html");
        spark.config().setDocumentTitle("Automation Report");
        spark.config().setReportName("Functional Test Results");
        spark.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(spark);
        extent.setSystemInfo("Tester", "Chandan Kumar");
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("Application", "OpenCart");
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
        test.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.get().pass("✅ Test Passed: " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.get().fail("❌ Test Failed: " + result.getName());
        test.get().fail(result.getThrowable());

        BaseClass base = (BaseClass) result.getInstance();
        WebDriver driver = base.getDriver();

        String screenshotPath = CaptureScreenshot.capture(driver, result.getName());
        try {
            test.get().addScreenCaptureFromPath(screenshotPath);
            System.out.println("📸 Screenshot saved at: " + screenshotPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.get().skip("⚠️ Test Skipped: " + result.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
        System.out.println("✅ Extent Report generated successfully!");
    }
}




    


