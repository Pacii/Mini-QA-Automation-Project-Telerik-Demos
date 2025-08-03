package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import utils.ConfigReader;

import java.time.Duration;
import java.util.List;

public abstract class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected final String BASE_URL = ConfigReader.get("baseUrl");

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void navigateTo(String subPath) {
        driver.get(BASE_URL + subPath);
    }

    public WebElement waitVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement waitClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public List<WebElement> waitAllVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    public void click(By locator) {
        waitClickable(locator).click();
    }

    public boolean isPresent(By locator) {
        return driver.findElements(locator).size() > 0;
    }
}
