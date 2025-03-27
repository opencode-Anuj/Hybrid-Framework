package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * A simple TestNG class to perform a valid login test on saucedemo.com.
 */
public class LoginTest {

    private WebDriver driver;
    private final String SAUCEDEMO_URL = "https://www.saucedemo.com/";

    /**
     * Sets up the WebDriver before each test method.
     */
    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup(); // Use WebDriverManager for easy driver setup
        driver = new ChromeDriver();
        driver.manage().window().maximize(); // Maximize the browser window
        driver.get(SAUCEDEMO_URL); // Navigate to the saucedemo URL
    }

    /**
     * Tests the login functionality with valid credentials.
     */
    @Test
    public void validLoginTest() {
        // Find the username and password input fields and enter valid credentials
        WebElement usernameField = driver.findElement(By.id("user-name"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login-button"));

        usernameField.sendKeys("standard_user");
        passwordField.sendKeys("secret_sauce");
        loginButton.click();

        // Verify that the user has successfully logged in by checking the presence of an inventory item.
        WebElement inventoryContainer = driver.findElement(By.id("inventory_container"));
        Assert.assertTrue(inventoryContainer.isDisplayed(), "Login failed: Inventory container not found.");
    }

    /**
     * Closes the WebDriver after each test method.
     */
    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit(); // Close the browser
        }
    }
}