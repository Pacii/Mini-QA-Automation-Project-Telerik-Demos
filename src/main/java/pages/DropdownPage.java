package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.ConfigReader;

import java.util.*;

public class DropdownPage extends BasePage {
    public DropdownPage(WebDriver driver) {
        super(driver);
    }

    private By autocompleteInput = By.xpath("//kendo-autocomplete//input");
    private By clearAutocompleteBtn = By.xpath("//kendo-autocomplete//span[contains(@class,'k-clear-value')]");
    private By multiSelectInput = By.xpath("//kendo-multiselect//input");
    private By selectedMulti = By.cssSelector("kendo-multiselect .k-chip-list .k-chip");
    private By closeSelectionButton = By.xpath("//div[contains(@class,'k-input-values')]//span[contains(@aria-label,'delete')]");

    public void navigateToDropdownPage() {
        navigateTo(ConfigReader.get("dropdownPath"));
    }

    public void typeAndSelectFavouriteSport(String sport) {
        WebElement input = waitVisible(autocompleteInput);
        input.sendKeys(sport);
        input.sendKeys(Keys.ENTER);
    }

    public String getSelectedAutocompleteSport() {
        return waitVisible(autocompleteInput).getAttribute("value");
    }

    public void clearAutoCompleteSport() { click(clearAutocompleteBtn); }

    public void openMultiSelectDropdown() { click(multiSelectInput); }

    public List<String> getSelectedMultiSports() {
        List<WebElement> chips = waitAllVisible(selectedMulti);
        List<String> selected = new ArrayList<>();
        for (WebElement chip : chips) {
            selected.add(chip.getText());
        }
        return selected;
    }

    public void clearAllMultiSelected() {
        List<WebElement> xButtons = driver.findElements(closeSelectionButton);
        for (int i = xButtons.size() - 1; i >= 0; i--) {
            xButtons.get(i).click();
        }
    }

    public void selectMultiSportsByEnter(String... sports) {
        WebElement input = waitVisible(multiSelectInput);
        for (String sport : sports) {
            input.click();
            input.clear();
            input.sendKeys(sport);
            input.sendKeys(Keys.ENTER);
        }
    }
}
