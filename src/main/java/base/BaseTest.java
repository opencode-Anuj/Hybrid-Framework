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
import org.testng.annotations.Parameters; 
import org.testng.ITestContext;
import utils.ConfigReader;


public class BaseTest {

    private Logger log = LogManager.getLogger(BaseTest.class);
    public static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    protected String baseUrl;

    @Parameters("browser") // Add parameter annotation
    @BeforeMethod
    public void setUp(String browser, ITestContext context) { // Add parameter to method
        log.info("Setting up WebDriver and navigating to the base URL.");

        if (browser == null) {
            browser = ConfigReader.getProperty("browser"); // Fallback to config if not in testng.xml
            if(browser == null){
                browser = "chrome"; // Default browser if none specified
            }
        }

        switch (browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver.set(new ChromeDriver());
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver.set(new FirefoxDriver());
                break;
            case "safari":
                WebDriverManager.safaridriver().setup();
                driver.set(new SafariDriver());
                break;
            default:
                log.warn("Invalid browser specified. Defaulting to Chrome.");
                WebDriverManager.chromedriver().setup();
                driver.set(new ChromeDriver());
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