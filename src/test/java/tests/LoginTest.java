package tests;

// LoginTest.java (Updated)
import io.github.bonigarcia.wdm.WebDriverManager;
import pages.LoginPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * A simple TestNG class to perform a valid login test on saucedemo.com using Page Object Model.
 */
public class LoginTest {

    private WebDriver driver;
    private final String SAUCEDEMO_URL = "https://www.saucedemo.com/";
    private LoginPage loginPage;

    /**
     * Sets up the WebDriver and initializes the LoginPage object before each test method.
     */
    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(SAUCEDEMO_URL);
        loginPage = new LoginPage(driver); // Initialize the LoginPage
    }

    /**
     * Tests the login functionality with valid credentials using the LoginPage object.
     */
    @Test
    public void validLoginTest() {
        loginPage.login("standard_user", "secret_sauce");

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
            driver.quit();
        }
    }
}