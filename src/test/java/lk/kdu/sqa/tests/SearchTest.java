package lk.kdu.sqa.tests;

import lk.kdu.sqa.pages.*;
import lk.kdu.sqa.utils.Config;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchTest extends BaseTest {

    @Test(description = "TC-03 Search for 'software testing'")
    public void searchForTopic() {
        new LoginPage(driver).open().login(Config.username(), Config.password());
        FeedPage feed = new FeedPage(driver);
        Assert.assertTrue(feed.isLoaded());

        SearchResultsPage results = feed.search("software testing");
        Assert.assertTrue(driver.getCurrentUrl().contains("/search/results"), "URL should be a search URL");
        Assert.assertTrue(results.isLoaded(), "At least one result should be visible");
    }
}