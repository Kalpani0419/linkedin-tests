package lk.kdu.sqa.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;
import java.util.function.Function;

public class WaitUtils {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public WaitUtils(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public WebElement visible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // Returns the first matching element that is actually displayed (skips hidden duplicates)
    public WebElement visibleAny(By locator) {
        return wait.until((Function<WebDriver, WebElement>) d ->
                d.findElements(locator).stream()
                        .filter(WebElement::isDisplayed)
                        .findFirst()
                        .orElse(null));
    }

    public WebElement clickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public boolean urlContains(String fragment) {
        return wait.until(ExpectedConditions.urlContains(fragment));
    }

    // Fluent wait: polls every 500 ms and ignores not-found / stale errors
    public WebElement fluentFind(By locator) {
        Wait<WebDriver> fluent = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class, StaleElementReferenceException.class);
        return fluent.until((Function<WebDriver, WebElement>) d -> d.findElement(locator));
    }

    // Waits for the feed page. If LinkedIn asks for a code or CAPTCHA, YOU complete it
    // by hand in the browser. The test never tries to bypass it.
    public void waitForManualVerification(String expectedUrlFragment, int seconds) {
        System.out.println(">>> Waiting up to " + seconds + "s for the feed page. "
                + "If LinkedIn asks for a verification code or CAPTCHA, complete it manually in the browser.");
        new WebDriverWait(driver, Duration.ofSeconds(seconds))
                .until(ExpectedConditions.urlContains(expectedUrlFragment));
    }
}