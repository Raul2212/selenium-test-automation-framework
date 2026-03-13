package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.HomePage;
import pages.LoginPage;
import utils.TestData;

public class LoginTest extends BaseTest {

    @Test(dataProvider = "loginData", dataProviderClass = TestData.class)
    public void verifyLoginTest(String username, String password, String expectedResult) {
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);

        loginPage.login(username, password);

        if (expectedResult.equalsIgnoreCase("success")) {
            Assert.assertEquals(homePage.getPageTitle(), "Products");
        } else {
            Assert.assertTrue(loginPage.getErrorMessage().length() > 0, "Expected error message was not displayed");
        }
    }

    @Test
    public void logoutTest() {
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);

        loginPage.login("standard_user", "secret_sauce");
        Assert.assertEquals(homePage.getPageTitle(), "Products");

        homePage.clickMenuButton();
        homePage.clickLogout();

        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");
    }
}