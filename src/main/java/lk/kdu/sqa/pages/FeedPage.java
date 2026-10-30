package lk.kdu.sqa.pages;

import lk.kdu.sqa.utils.WaitUtils;
import org.openqa.selenium.*;

public class FeedPage {
    private final WebDriver driver;
    private final WaitUtils wait;

    // Several fallbacks because LinkedIn changes class names often
    private final By searchBox = By.cssSelector(
            "input.search-global-typeahead__input, input[aria-label='Search'], input[placeholder='Search']");

    public FeedPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitUtils(driver);
    }

    public boolean isLoaded() {
        wait.waitForManualVerification("/feed", 120);   // pause for manual code / CAPTCHA
        return wait.urlContains("/feed") && wait.visible(searchBox).isDisplayed();
    }

    public SearchResultsPage search(String term) {
        WebElement box = wait.clickable(searchBox);
        box.click();
        box.clear();
        box.sendKeys(term);
        box.sendKeys(Keys.ENTER);
        wait.urlContains("/search/results");   // wait until LinkedIn opens the results page
        return new SearchResultsPage(driver);
    }
}