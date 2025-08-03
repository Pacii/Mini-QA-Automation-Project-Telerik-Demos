package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.DropdownPage;

import java.util.List;

public class DropdownTest extends BaseTest {
    DropdownPage dropdown = new DropdownPage();
    BasePage bp = new BasePage();

    private String dropdownPath = "/kendo-angular-ui/demos/dropdowns/overview?theme=default-main";

    @Test(description = "Select and clear favourite sport using autocomplete.")
        public void shouldSelectAndClearFavouriteSportUsingAutocomplete() {
        bp.navigateTo(dropdownPath);
        String sport = "Cricket";
        dropdown.typeAndSelectFavouriteSport(sport);
        Assert.assertEquals(dropdown.getSelectedAutocompleteSport(), sport, "Chosen sport should match.");
        dropdown.clearAutoCompleteSport();
        Assert.assertTrue(dropdown.getSelectedAutocompleteSport().isEmpty(), "Input should be empty after clear.");
    }

    @Test(description = "Clear all selections, then select Tennis and Football in multiselect dropdown and verify.")
    public void shouldClearThenSelectMultipleSportsInMultiSelectDropdown() {
        String sport1 = "Tennis";
        String sport2 = "Football";
        bp.navigateTo(dropdownPath);
        dropdown.openMultiSelectDropdown();
        dropdown.clearAllMultiSelected();
        dropdown.selectMultiSportsByEnter(sport1, sport2);
        List<String> selected = dropdown.getSelectedMultiSports();
        Assert.assertTrue(selected.contains(sport1), sport1 + " should be selected.");
        Assert.assertTrue(selected.contains(sport2), sport2 + " should be selected.");
        Assert.assertEquals(selected.size(), 2, "Only " + sport1 + "and " + sport2 + "should be selcted!");
    }
}