package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.ElementUtils;

public class LoginPage {

    private WebDriver driver;
    private ElementUtils elementUtils;
    private static final Logger logger = LogManager.getLogger(LoginPage.class);

    private By usernameField = By.id("user-name");
    private By passwordField = By.id("password");
    private By loginButton = By.id("login-button");
    private By errorMessage = By.xpath("//h3[@data-test='error']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.elementUtils = new ElementUtils(driver);
    }

    public void enterUsername(String username) {
        logger.info("Entering username");
        elementUtils.doSendKeys(usernameField, username);
    }

    public void enterPassword(String password) {
        logger.info("Entering password");
        elementUtils.doSendKeys(passwordField, password);
    }

    public void clickLogin() {
        logger.info("Clicking login button");
        elementUtils.doClick(loginButton);
    }

    public void login(String username, String password) {
        logger.info("Performing login");
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }

    public String getErrorMessage() {
        logger.info("Fetching login error message");
        return elementUtils.doGetText(errorMessage);
    }
}