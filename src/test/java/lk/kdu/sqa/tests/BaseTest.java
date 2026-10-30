package lk.kdu.sqa.tests;

import lk.kdu.sqa.utils.ScreenshotUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.*;
import org.testng.ITestResult;
import org.testng.annotations.*;

public class BaseTest {
    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized", "--disable-notifications");
        driver = new ChromeDriver(options);   // Selenium Manager downloads the driver
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        if (driver != null) {
            if (result.getStatus() == ITestResult.FAILURE) {
                String path = ScreenshotUtils.capture(driver, result.getName());
                System.out.println("Failure screenshot: " + path);
            }
            driver.quit();
        }
    }
}