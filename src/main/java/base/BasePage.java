package base;

import com.aventstack.extentreports.Status;
import listeners.TestListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.TimeoutException;
import constants.FrameworkConstants;

import java.time.Duration;

/**
 * BasePage class provides common methods for interacting with web elements.
 */
public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Logger log = LogManager.getLogger(BasePage.class);

    /**
     * Constructor for BasePage.
     *
     * @param driver WebDriver instance.
     */
    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(FrameworkConstants.EXPLICIT_WAIT));
    }

    /**
     * Waits for an element to be visible.
     *
     * @param locator By locator of the element.
     * @return WebElement if found, null otherwise.
     * @throws TimeoutException if the element is not visible within the specified time.
     */
    public WebElement waitForElementToBeVisible(By locator) {
        try {
            log.info("Waiting for element to be visible: " + locator);
            TestListener.test.get().log(Status.INFO, "Waiting for element to be visible: " + locator);
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (TimeoutException e) {
            log.error("Timeout waiting for element: " + locator, e);
            TestListener.test.get().log(Status.FAIL, "Timeout waiting for element: " + locator + " Exception: " + e);
            throw e;
        }
    }

    /**
     * Waits for an element to be clickable.
     *
     * @param locator By locator of the element.
     * @return WebElement if found, null otherwise.
     * @throws TimeoutException if the element is not clickable within the specified time.
     */
    public WebElement waitForElementToBeClickable(By locator) {
        try {
            log.info("Waiting for element to be clickable: " + locator);
            TestListener.test.get().log(Status.INFO, "Waiting for element to be clickable: " + locator);
            return wait.until(ExpectedConditions.elementToBeClickable(locator));
        } catch (TimeoutException e) {
            log.error("Timeout waiting for element to be clickable: " + locator, e);
            TestListener.test.get().log(Status.FAIL, "Timeout waiting for element to be clickable: " + locator + " Exception: " + e);
            throw e;
        }
    }

    /**
     * Clicks an element.
     *
     * @param locator By locator of the element.
     * @throws NoSuchElementException if the element is not found.
     */
    public void clickElement(By locator) {
        try {
            log.info("Clicking element: " + locator);
            TestListener.test.get().log(Status.INFO, "Clicking element: " + locator);
            WebElement element = waitForElementToBeClickable(locator);
            if (element != null) {
                element.click();
            } else {
                throw new NoSuchElementException("Element is not clickable: " + locator);
            }
        } catch (NoSuchElementException e) {
            log.error("Element not found or not clickable: " + locator, e);
            TestListener.test.get().log(Status.FAIL, "Element not found or not clickable: " + locator + " Exception: " + e);
            throw e;
        }
    }

    /**
     * Sends keys to an element.
     *
     * @param locator By locator of the element.
     * @param text    Text to send.
     * @throws NoSuchElementException if the element is not found.
     */
    public void sendKeys(By locator, String text) {
        try {
            log.info("Sending keys '" + text + "' to element: " + locator);
            TestListener.test.get().log(Status.INFO, "Sending keys '" + text + "' to element: " + locator);
            WebElement element = waitForElementToBeVisible(locator);
            if (element != null) {
                element.sendKeys(text);
            } else {
                throw new NoSuchElementException("Element not found: " + locator);
            }
        } catch (NoSuchElementException e) {
            log.error("Element not found: " + locator, e);
            TestListener.test.get().log(Status.FAIL, "Element not found: " + locator + " Exception: " + e);
            throw e;
        }
    }

    /**
     * Checks if an element is displayed.
     *
     * @param locator By locator of the element.
     * @return true if the element is displayed, false otherwise.
     */
    public boolean isElementDisplayed(By locator) {
        try {
            log.info("Checking if element is displayed: " + locator);
            TestListener.test.get().log(Status.INFO, "Checking if element is displayed: " + locator);
            return waitForElementToBeVisible(locator).isDisplayed();
        } catch (NoSuchElementException e) {
            log.warn("Element not found: " + locator);
            TestListener.test.get().log(Status.WARNING, "Element not found: " + locator);
            return false;
        }
    }
}