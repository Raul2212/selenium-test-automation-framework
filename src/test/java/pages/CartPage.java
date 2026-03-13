package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.ElementUtils;
import utils.WaitUtils;

public class CartPage {

    private WebDriver driver;
    private ElementUtils elementUtils;
    private WaitUtils waitUtils;

    private By cartTitle = By.className("title");
    private By backpackItem = By.xpath("//div[text()='Sauce Labs Backpack']");
    private By removeButton = By.xpath("//div[text()='Sauce Labs Backpack']/ancestor::div[@class='cart_item']//button");

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.elementUtils = new ElementUtils(driver);
        this.waitUtils = new WaitUtils(driver);
    }

    public String getCartPageTitle() {
        return elementUtils.doGetText(cartTitle);
    }

    public boolean isBackpackDisplayedInCart() {
        return elementUtils.isElementDisplayed(backpackItem);
    }

    public void removeProductFromCart() {
        elementUtils.doJsClick(removeButton);
        waitUtils.waitForInvisibility(backpackItem);
    }

    public boolean isCartEmpty() {
        return !elementUtils.isElementDisplayed(backpackItem);
    }
}