package pages;

import org.openqa.selenium.*;
import utils.ConfigReader;

public class DialogPage extends BasePage {
    public DialogPage(WebDriver driver) {
        super(driver);
    }

    private By openDialogBtn = By.xpath("//button//span[contains(text(),'Open dialog')]");
    private By dialog = By.cssSelector(".k-dialog");
    private By yesBtn = By.xpath("//button//span[contains(text(),'Yes')]");
    private By noBtn = By.xpath("//button//span[contains(text(),'No')]");
    private By xBtn = By.xpath("//button[@title='Close']");

    public void navigateToDialogPage() {
        navigateTo(ConfigReader.get("dialogPath"));
    }
    public void openDialog() { click(openDialogBtn); }
    public boolean isDialogVisible() { return isPresent(dialog); }
    public boolean hasAllElements() {
        return isPresent(yesBtn) && isPresent(noBtn) && isPresent(xBtn);
    }
    public String getYesBtnBgColor() { return waitVisible(yesBtn).getCssValue("background-color"); }
    public void focusX() {
        WebElement el = waitVisible(xBtn);
        ((JavascriptExecutor)driver).executeScript("arguments[0].focus();", el);
    }
    public void closeDialogByEnter() {
        WebElement el = waitVisible(xBtn);
        el.sendKeys(Keys.ENTER);
    }
    public boolean isDialogClosed() {
        try { Thread.sleep(600); } catch (Exception ignored) {}
        return !isPresent(dialog);
    }
}
