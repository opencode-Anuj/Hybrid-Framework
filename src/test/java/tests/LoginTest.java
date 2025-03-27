package tests;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
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

    @BeforeMethod
    public void setupLoginPage() {
        loginPage = new LoginPage(driver);
    }

    /**
     * Tests the login functionality with valid credentials using the LoginPage object.
     */
    @Test
    public void validLoginTest() {
        loginPage.login(ConfigReader.getProperty("username"), ConfigReader.getProperty("password"));

        // Verify that the user has successfully logged in by checking the presence of an inventory item.
        WebElement inventoryContainer = driver.findElement(By.id("inventory_container"));
        Assert.assertTrue(inventoryContainer.isDisplayed(), "Login failed: Inventory container not found.");
    }
}