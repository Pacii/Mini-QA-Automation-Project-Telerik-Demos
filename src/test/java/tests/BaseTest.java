package tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pages.BasePage;
import factories.DriverFactory;

public class BaseTest {
    protected WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver = DriverFactory.createDriver();
        BasePage.setDriver(driver);
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) driver.quit();
    }
}
