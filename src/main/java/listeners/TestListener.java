package listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import base.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.IAnnotationTransformer;
import org.testng.ITestResult;
import org.testng.annotations.ITestAnnotation;
import reports.ExtentManager;
import utils.ScreenshotUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.ITestListener;

public class TestListener implements ITestListener, IAnnotationTransformer {

    private ExtentReports extent = ExtentManager.getInstance();
    public static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
        test.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.get().log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.get().log(Status.FAIL, "Test Failed: " + result.getThrowable());

        WebDriver driver = BaseTest.getDriver();
        if (driver != null) {
            String screenshotPath = ScreenshotUtils.captureScreenshot(driver, result.getName());
            if (screenshotPath != null) {
                test.get().fail("Test Failed", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
            } else {
                test.get().fail("Test Failed, but screenshot capture failed.");
            }
        } else {
            test.get().fail("Test Failed, but WebDriver was null.");
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.get().log(Status.SKIP, "Test Skipped");
    }

    @Override
    public void onFinish(org.testng.ITestContext context) {
        extent.flush();
    }

    @Override
    public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
        annotation.setRetryAnalyzer(RetryAnalyzer.class);
    }
}