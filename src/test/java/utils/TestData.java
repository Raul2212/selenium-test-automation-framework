package utils;

import org.testng.annotations.DataProvider;

public class TestData {

    @DataProvider(name = "loginData")
    public Object[][] loginDataProvider() {
        return new Object[][] {
                {"standard_user", "secret_sauce", "success"},
                {"standard_user", "wrong123", "failure"},
                {"locked_out_user", "secret_sauce", "failure"}
        };
    }
}