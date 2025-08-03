package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WindowPage extends BasePage {
    public WindowPage(WebDriver driver) {
        super(driver);
    }

    private By openWindowBtn = By.xpath("//button//span[contains(text(),'Open window')]");
    private By window = By.cssSelector(".k-window");
    private By titleField = By.xpath("//span[contains(@class,'k-window-title')]");
    private By contentField = By.xpath("//div[contains(@class,'k-window-content')]");
    private By maximizeBtn = By.cssSelector("button[kendowindowmaximizeaction]");
    private By closeBtn = By.xpath("//button[@title='Close']");
    private By restoreBtn = By.cssSelector("button[kendowindowrestoreaction]");

    public void openWindow() { click(openWindowBtn); }
    public boolean isWindowVisible() { return isPresent(window); }
    public boolean hasContentField() { return isPresent(contentField); }
    public boolean hasTitleField() { return isPresent(titleField); }
    public void maximizeWindow() { click(maximizeBtn); }
    public boolean isMaximizeBtnVisible() {
        WebElement btn = driver.findElement(maximizeBtn);
        return btn.isDisplayed();
    }
    public void closeWindow() { click(closeBtn); }
    public boolean isRestoreBtnVisible() {return isPresent(restoreBtn);}
    public boolean isWindowClosed() {
        try { Thread.sleep(600); } catch (Exception ignored) {}
        return !isPresent(window);
    }
}
