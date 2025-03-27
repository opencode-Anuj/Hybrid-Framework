package base;

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

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Logger log = LogManager.getLogger(BasePage.class);

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(FrameworkConstants.EXPLICIT_WAIT));
    }

    public WebElement waitForElementToBeVisible(By locator) {
        try{
            log.info("Waiting for element to be visible: " + locator);
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        }catch(TimeoutException e){
            log.error("Timeout waiting for element: " + locator, e);
            throw e;
        }
    }

    public WebElement waitForElementToBeClickable(By locator) {
        try{
            log.info("Waiting for element to be clickable: " + locator);
            return wait.until(ExpectedConditions.elementToBeClickable(locator));
        } catch (TimeoutException e){
            log.error("Timeout waiting for element to be clickable: " + locator, e);
            throw e;
        }
    }

    public void clickElement(By locator) {
        try{
            log.info("Clicking element: " + locator);
            waitForElementToBeClickable(locator).click();
        }catch(NoSuchElementException e){
            log.error("Element not found: " + locator, e);
            throw e;
        }
    }

    public void sendKeys(By locator, String text) {
        try{
            log.info("Sending keys '" + text + "' to element: " + locator);
            waitForElementToBeVisible(locator).sendKeys(text);
        }catch(NoSuchElementException e){
            log.error("Element not found: " + locator, e);
            throw e;
        }
    }

    public boolean isElementDisplayed(By locator) {
        try {
            log.info("Checking if element is displayed: " + locator);
            return waitForElementToBeVisible(locator).isDisplayed();
        } catch (NoSuchElementException e) {
            log.warn("Element not found: " + locator);
            return false;
        }
    }
}