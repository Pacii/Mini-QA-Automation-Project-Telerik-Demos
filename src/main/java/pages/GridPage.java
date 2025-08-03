package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ConfigReader;

import java.time.Duration;
import java.util.*;

public class GridPage extends BasePage {
    public GridPage(WebDriver driver) {
        super(driver);
    }

    private final By tableRows = By.cssSelector("table.k-table > tbody > tr");
    private final By pagerNext = By.cssSelector("button[aria-label='Go to the next page']");

    public void navigateToGridPage() {
        navigateTo(ConfigReader.get("gridPath"));
    }

    public List<Map<String, String>> getUsaEmployeesAllPages() {
        List<Map<String, String>> usa = new ArrayList<>();
        int pageNum = 1;
        while (true) {
            waitRowsVisible();
            List<WebElement> rows = driver.findElements(tableRows);

            for (WebElement row : rows) {
                List<WebElement> cols = row.findElements(By.tagName("td"));
                if (cols.size() < 10) continue;
                // USA flag check:
                WebElement flagImg = cols.get(3).findElement(By.cssSelector("img.flag"));
                String src = flagImg.getAttribute("src");
                if (src.contains("iVBORw0KGgoAAAANSUhEUgAAAEAAAAAiCAIAAABgN0jY")) { // USA base64
                    Map<String, String> emp = new HashMap<>();
                    emp.put("Name", cols.get(1).getText().trim());
                    emp.put("JobTitle", cols.get(2).getText().trim());
                    emp.put("Phone", cols.get(8).getText().trim());
                    emp.put("Address", cols.get(9).getText().trim());
                    emp.put("Online", cols.get(4).getText().trim());
                    usa.add(emp);
                }
            }

            // Pagination
            List<WebElement> nextBtns = driver.findElements(pagerNext);
            if (nextBtns.isEmpty()) break;
            WebElement nextBtn = nextBtns.get(0);
            String ariaDisabled = nextBtn.getAttribute("aria-disabled");
            if ("true".equals(ariaDisabled)) break;

            nextBtn.click();
            pageNum++;
        }
        System.out.println("INFO: Total USA employees found: " + usa.size());
        return usa;
    }

    public List<Map<String, String>> getOnlineUsaEmployees(List<Map<String, String>> usa) {
        List<Map<String, String>> online = new ArrayList<>();
        for (Map<String, String> emp : usa)
            if ("Online".equalsIgnoreCase(emp.get("Online")))
                online.add(emp);
        System.out.println("INFO: Online USA employees found: " + online.size());
        return online;
    }

    private void waitRowsVisible() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(tableRows));
    }
}
