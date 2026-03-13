package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ElementUtils {

    private WebDriver driver;
    private WaitUtils waitUtils;

    public ElementUtils(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
    }

    public void doClick(By locator) {
        waitUtils.waitForClickable(locator).click();
    }

    public void doJsClick(By locator) {
        WebElement element = waitUtils.waitForVisibility(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    public void doSendKeys(By locator, String value) {
        WebElement element = waitUtils.waitForVisibility(locator);
        element.clear();
        element.sendKeys(value);
    }

    public String doGetText(By locator) {
        return waitUtils.waitForVisibility(locator).getText();
    }

    public boolean isElementDisplayed(By locator) {
        return !driver.findElements(locator).isEmpty();
    }
}