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

public class BaseTest {

    protected WebDriver driver;
    protected String baseUrl;
    protected Logger log = LogManager.getLogger(BaseTest.class);

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
                driver = new ChromeDriver();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case "safari":
                WebDriverManager.safaridriver().setup();
                driver = new SafariDriver();
                break;
            default:
                log.warn("Invalid browser specified. Defaulting to Chrome.");
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
        }

        driver.manage().window().maximize();
        baseUrl = ConfigReader.getProperty("baseUrl");
        driver.get(baseUrl);
        log.info("Navigated to: " + baseUrl);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            log.info("Closing the WebDriver.");
            driver.quit();
        }
    }
}