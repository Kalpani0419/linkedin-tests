package lk.kdu.sqa.pages;

import lk.kdu.sqa.utils.WaitUtils;
import org.openqa.selenium.*;

public class SearchResultsPage {
    private final WebDriver driver;
    private final WaitUtils wait;

    private final By resultsContainer = By.cssSelector("main");
    // href-based locator is more stable than class names
    private final By firstResultLink = By.cssSelector(
            "main a[href*='/in/'], main a[href*='/company/'], main a[href*='/school/']");

    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitUtils(driver);
    }

    public boolean isLoaded() {
        return wait.urlContains("/search/results")
                && wait.visible(resultsContainer).isDisplayed()
                && wait.visible(firstResultLink).isDisplayed();
    }

    public String firstResultHref() {
        return wait.visible(firstResultLink).getAttribute("href");
    }

    public void clickFirstResult() {
        wait.clickable(firstResultLink).click();
    }
}