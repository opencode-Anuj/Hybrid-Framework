package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtils {

    public static String captureScreenshot(WebDriver driver, String testName) {
        if (driver == null) {
            return null; // return null if driver is null
        }

        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);

        String timestamp = new SimpleDateFormat("dd-MMM-yyyy_HH_mm_ss").format(new Date());
        String destination = "screenshots/" + testName + "_" + timestamp + ".png";

        try {
            FileUtils.copyFile(source, new File(destination));
            System.out.println("Screenshot captured: " + destination);
            return destination; // Return the path
        } catch (IOException e) {
            System.out.println("Exception while taking screenshot: " + e.getMessage());
            return null; // Return null if capture fails
        }
    }
}