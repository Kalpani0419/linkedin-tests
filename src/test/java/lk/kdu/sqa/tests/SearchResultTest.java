package lk.kdu.sqa.tests;

import lk.kdu.sqa.pages.*;
import lk.kdu.sqa.utils.Config;
import lk.kdu.sqa.utils.WaitUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchResultTest extends BaseTest {

    @Test(description = "TC-04 Open first search result")
    public void openFirstResult() {
        new LoginPage(driver).open().login(Config.username(), Config.password());
        FeedPage feed = new FeedPage(driver);
        Assert.assertTrue(feed.isLoaded());

        SearchResultsPage results = feed.search("Microsoft");
        Assert.assertTrue(results.isLoaded());

        String href = results.firstResultHref();   // e.g. https://www.linkedin.com/company/microsoft/
        String path = href.replace("https://www.linkedin.com", "").split("\\?")[0];
        results.clickFirstResult();

        Assert.assertTrue(new WaitUtils(driver).urlContains(path),
                "Opened page URL should contain " + path);
    }
}