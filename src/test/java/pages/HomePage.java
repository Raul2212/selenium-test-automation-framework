package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.ElementUtils;
import utils.WaitUtils;

public class HomePage {

    private WebDriver driver;
    private ElementUtils elementUtils;
    private WaitUtils waitUtils;

    private By pageTitle = By.className("title");
    private By backpackAddButton = By.xpath("//div[text()='Sauce Labs Backpack']/ancestor::div[@class='inventory_item']//button");
    private By cartIcon = By.className("shopping_cart_link");
    private By cartBadge = By.className("shopping_cart_badge");
    private By menuButton = By.id("react-burger-menu-btn");
    private By logoutLink = By.id("logout_sidebar_link");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.elementUtils = new ElementUtils(driver);
        this.waitUtils = new WaitUtils(driver);
    }

    public String getPageTitle() {
        return elementUtils.doGetText(pageTitle);
    }

    public void addBackpackToCart() {
        elementUtils.doJsClick(backpackAddButton);
        waitUtils.waitForVisibility(cartBadge);
    }

    public void clickCartIcon() {
        elementUtils.doJsClick(cartIcon);
        waitUtils.waitForUrlContains("cart");
    }

    public String getCartBadgeCount() {
        return elementUtils.doGetText(cartBadge);
    }

    public boolean isCartBadgeDisplayed() {
        return elementUtils.isElementDisplayed(cartBadge);
    }

    public void clickMenuButton() {
        elementUtils.doJsClick(menuButton);
        waitUtils.waitForVisibility(logoutLink);
    }

    public void clickLogout() {
        elementUtils.doJsClick(logoutLink);
    }
}