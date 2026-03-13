package tests;

import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.CartPage;
import pages.HomePage;
import pages.LoginPage;
import utils.ConfigReader;

public class CartTest extends BaseTest {

    private LoginPage loginPage;
    private HomePage homePage;
    private CartPage cartPage;
    private WebDriverWait wait;

    @BeforeMethod
    public void loginBeforeCartTests() {
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        cartPage = new CartPage(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        loginPage.login(
                ConfigReader.getProperty("username"),
                ConfigReader.getProperty("password")
        );

        wait.until(ExpectedConditions.urlContains("inventory"));
        Assert.assertEquals(homePage.getPageTitle(), "Products");
    }

    @Test(priority = 1)
    public void addProductToCartTest() {
        homePage.addBackpackToCart();

        Assert.assertTrue(homePage.isCartBadgeDisplayed(), "Cart badge is not displayed");
        Assert.assertEquals(homePage.getCartBadgeCount(), "1");
    }

    @Test(priority = 2)
    public void removeProductFromCartTest() {
        homePage.addBackpackToCart();
        Assert.assertEquals(homePage.getCartBadgeCount(), "1");

        homePage.clickCartIcon();

        Assert.assertEquals(cartPage.getCartPageTitle(), "Your Cart");
        Assert.assertTrue(cartPage.isBackpackDisplayedInCart(), "Product is not present in cart");

        cartPage.removeProductFromCart();
        Assert.assertTrue(cartPage.isCartEmpty(), "Cart is not empty after removing product");
    }

    @Test(priority = 3)
    public void completeEndToEndFlowTest() {
        homePage.addBackpackToCart();
        Assert.assertEquals(homePage.getCartBadgeCount(), "1");

        homePage.clickCartIcon();

        Assert.assertEquals(cartPage.getCartPageTitle(), "Your Cart");
        Assert.assertTrue(cartPage.isBackpackDisplayedInCart(), "Product is not present in cart");

        cartPage.removeProductFromCart();
        Assert.assertTrue(cartPage.isCartEmpty(), "Cart is not empty after removing product");

        driver.navigate().back();
        wait.until(ExpectedConditions.urlContains("inventory"));

        homePage.clickMenuButton();
        homePage.clickLogout();

        wait.until(ExpectedConditions.urlToBe("https://www.saucedemo.com/"));
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");
    }
}