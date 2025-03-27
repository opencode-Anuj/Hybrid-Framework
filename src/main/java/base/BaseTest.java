package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.ConfigReader;

import java.time.Duration;

public class BaseTest {

    private Logger log = LogManager.getLogger(BaseTest.class);
    public static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    protected String baseUrl;

    @BeforeMethod
    public void setUp() {
        log.info("Setting up WebDriver and navigating to the base URL.");
        String browser = ConfigReader.getProperty("browser");

        if (browser == null) {
            browser = "chrome"; // Default browser
        }

        switch (browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver.set(new ChromeDriver()); // Assign to ThreadLocal first
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver.set(new FirefoxDriver()); // Assign to ThreadLocal first
                break;
            case "safari":
                WebDriverManager.safaridriver().setup();
                driver.set(new SafariDriver()); // Assign to ThreadLocal first
                break;
            default:
                log.warn("Invalid browser specified. Defaulting to Chrome.");
                WebDriverManager.chromedriver().setup();
                driver.set(new ChromeDriver()); // Assign to ThreadLocal first
        }

        driver.get().manage().window().maximize();
        baseUrl = ConfigReader.getProperty("baseUrl");
        driver.get().get(baseUrl);
        log.info("Navigated to: " + baseUrl);
    }

    @AfterMethod
    public void tearDown() {
        if (driver.get() != null) {
            log.info("Closing the WebDriver.");
            driver.get().quit();
            driver.remove();
        }
    }
    // Add this static method to access the WebDriver instance.
    public static WebDriver getDriver() {
        return driver.get();
    }
}