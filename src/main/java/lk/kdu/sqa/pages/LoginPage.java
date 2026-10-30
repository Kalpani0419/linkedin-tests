package lk.kdu.sqa.pages;

import lk.kdu.sqa.utils.WaitUtils;
import org.openqa.selenium.*;

public class LoginPage {
    private final WebDriver driver;
    private final WaitUtils wait;

    private final By usernameField = By.cssSelector("input[type='email']");
    private final By passwordField = By.cssSelector("input[type='password']");
    private final By signInButton  = By.xpath("//button[normalize-space(.)='Sign in']");
    private final By errorMessage  = By.xpath("//*[contains(text(),'Wrong email or password') or contains(text(),'not the right password')]");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitUtils(driver);
    }

    public LoginPage open() {
        driver.get("https://www.linkedin.com/login");
        wait.visibleAny(usernameField);
        return this;
    }

    public void login(String user, String pass) {
        WebElement u = wait.visibleAny(usernameField);
        u.clear();
        u.sendKeys(user);
        wait.visibleAny(passwordField).sendKeys(pass);
        wait.visibleAny(signInButton).click();
    }

    public boolean isErrorDisplayed() {
        return wait.visibleAny(errorMessage).isDisplayed();
    }

    public String getErrorText() {
        return wait.visibleAny(errorMessage).getText();
    }
}