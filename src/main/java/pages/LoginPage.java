package pages;

import base.BasePage;
import com.aventstack.extentreports.Status;
import listeners.TestListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private final By usernameField = By.id("user-name");
    private final By passwordField = By.id("password");
    private final By loginButton = By.id("login-button");
    private Logger log = LogManager.getLogger(LoginPage.class);

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void enterUsername(String username) {
        log.info("Entering username: " + username);
        TestListener.test.get().log(Status.INFO, "Entering username: " + username);
        sendKeys(usernameField, username);
    }

    public void enterPassword(String password) {
        log.info("Entering password.");
        TestListener.test.get().log(Status.INFO, "Entering password.");
        sendKeys(passwordField, password);
    }

    public void clickLoginButton() {
        log.info("Clicking login button.");
        TestListener.test.get().log(Status.INFO, "Clicking login button.");
        clickElement(loginButton);
    }

    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }
}