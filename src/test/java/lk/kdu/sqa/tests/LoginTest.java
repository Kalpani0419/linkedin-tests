package lk.kdu.sqa.tests;

import lk.kdu.sqa.pages.*;
import lk.kdu.sqa.utils.Config;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test(description = "TC-01 Valid login")
    public void validLogin() {
        new LoginPage(driver).open().login(Config.username(), Config.password());
        FeedPage feed = new FeedPage(driver);
        Assert.assertTrue(feed.isLoaded(), "Feed page and search box should be visible");
        Assert.assertTrue(driver.getCurrentUrl().contains("/feed"));
    }

    @Test(description = "TC-02 Invalid password")
    public void invalidLogin() {
        LoginPage login = new LoginPage(driver).open();
        login.login(Config.username(), "WrongPassword@123");
        Assert.assertTrue(login.isErrorDisplayed(), "Error message should be shown");
        System.out.println("Error text: " + login.getErrorText());
    }
}