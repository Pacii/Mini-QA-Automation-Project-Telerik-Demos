package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DialogPage;
import pages.WindowPage;

public class DialogTest extends BaseTest {

    @Test(description = "Verify dialog window elements and actions.")
    public void shouldVerifyDialogWindowAndActions() {
        DialogPage dialog = new DialogPage(driver);
        dialog.navigateToDialogPage();
        dialog.openDialog();
        Assert.assertTrue(dialog.isDialogVisible(), "Dialog should be visible after Open Dialog click.");
        Assert.assertTrue(dialog.hasAllElements(), "All expected elements should be present.");
        Assert.assertTrue(dialog.getYesBtnBgColor().contains("rgb"), "Yes button should have background color.");
        dialog.focusX();
        dialog.closeDialogByEnter();
        Assert.assertTrue(dialog.isDialogClosed(), "Dialog should be closed after Enter.");
    }

    @Test(description = "Verify data form window and maximize/close actions.")
    public void shouldValidateDataFormWindowActions() {
        WindowPage window = new WindowPage(driver);
        DialogPage dialog = new DialogPage(driver);
        dialog.navigateToDialogPage();
        window.openWindow();
        Assert.assertTrue(window.isWindowVisible(), "Window should be visible after click.");
        Assert.assertTrue(window.hasContentField(), "Content field should be present.");
        Assert.assertTrue(window.hasTitleField(), "Title field should be present.");
        window.maximizeWindow();
        Assert.assertTrue(window.isRestoreBtnVisible(), "Restore button should be visible after maximizing.");
        Assert.assertFalse(window.isMaximizeBtnVisible(), "Maximize button should not be visible after maximizing.");
        window.closeWindow();
        Assert.assertTrue(window.isWindowClosed(), "Window should be closed after close click.");
    }
}
