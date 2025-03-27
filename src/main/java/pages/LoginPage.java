package pages;

// LoginPage.java
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Page object representing the login page of saucedemo.com.
 */
public class LoginPage {

    private WebDriver driver;

    @FindBy(id = "user-name")
    private WebElement usernameField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    /**
     * Constructor to initialize the LoginPage with the WebDriver.
     *
     * @param driver The WebDriver instance.
     */
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Enters the username into the username field.
     *
     * @param username The username to enter.
     */
    public void enterUsername(String username) {
        usernameField.sendKeys(username);
    }

    /**
     * Enters the password into the password field.
     *
     * @param password The password to enter.
     */
    public void enterPassword(String password) {
        passwordField.sendKeys(password);
    }

    /**
     * Clicks the login button.
     */
    public void clickLoginButton() {
        loginButton.click();
    }

    /**
     * Performs a complete login action with the given credentials.
     *
     * @param username The username to use for login.
     * @param password The password to use for login.
     */
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }
}

