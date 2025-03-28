package tests;

import base.BaseTest;
import com.aventstack.extentreports.Status;
import listeners.TestListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.ConfigReader;

/**
 * A simple TestNG class to perform a valid login test on saucedemo.com using Page Object Model.
 */
public class LoginTest extends BaseTest {

    private LoginPage loginPage;
    private Logger log = LogManager.getLogger(LoginTest.class);
    private WebDriver driver;

    @BeforeMethod
    public void setupLoginPage() {
        driver = BaseTest.driver.get(); // Get WebDriver from ThreadLocal
        loginPage = new LoginPage(driver);
    }

    /**
     * Tests the login functionality with valid credentials using the LoginPage object.
     */
    @Test
    public void validLoginTest() {
        log.info("Starting validLoginTest.");
        TestListener.test.get().log(Status.INFO, "Starting validLoginTest.");
        try {
            loginPage.login(ConfigReader.getProperty("username"), ConfigReader.getProperty("password"));

            // Verify that the user has successfully logged in by checking the presence of an inventory item.
            Assert.assertTrue(loginPage.isElementDisplayed(By.id("inventory_container")), "Login failed: Inventory container not found.");
            log.info("Login successful. Inventory container is displayed.");
            TestListener.test.get().log(Status.PASS, "Login successful. Inventory container is displayed.");
        } catch (Exception e) {
            log.error("Login test failed: " + e.getMessage(), e);
            TestListener.test.get().log(Status.FAIL, "Login test failed: " + e.getMessage());
            Assert.fail("Login test failed: " + e.getMessage());
        }
    }
}