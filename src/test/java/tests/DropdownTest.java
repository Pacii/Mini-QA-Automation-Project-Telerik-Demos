package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DropdownPage;
import utils.ConfigReader;

import java.util.List;

public class DropdownTest extends BaseTest {

    @Test(description = "Select and clear favourite sport using autocomplete.")
        public void selectAndClearFavouriteSportUsingAutocomplete() {
        DropdownPage dropdown = new DropdownPage(driver);
        dropdown.navigateToDropdownPage();
        String sport = ConfigReader.get("sport1");
        dropdown.typeAndSelectFavouriteSport(sport);
        Assert.assertEquals(dropdown.getSelectedAutocompleteSport(), sport, "Chosen sport should match.");
        dropdown.clearAutoCompleteSport();
        Assert.assertTrue(dropdown.getSelectedAutocompleteSport().isEmpty(), "Input should be empty after clear.");
    }

    @Test(description = "Clear all selections, then select Tennis and Football in multiselect dropdown and verify.")
    public void clearThenSelectMultipleSportsInMultiSelectDropdown() {
        DropdownPage dropdown = new DropdownPage(driver);
        String sport1 = ConfigReader.get("sport1");
        String sport2 = ConfigReader.get("sport2");
        dropdown.navigateToDropdownPage();
        dropdown.openMultiSelectDropdown();
        dropdown.clearAllMultiSelected();
        dropdown.selectMultiSportsByEnter(sport1, sport2);
        List<String> selected = dropdown.getSelectedMultiSports();
        Assert.assertTrue(selected.contains(sport1), sport1 + " should be selected.");
        Assert.assertTrue(selected.contains(sport2), sport2 + " should be selected.");
        Assert.assertEquals(selected.size(), 2, "Only " + sport1 + "and " + sport2 + "should be selcted!");
    }
}