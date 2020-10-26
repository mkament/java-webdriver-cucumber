package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import support.Loggable;
import support.Screenshot;
import java.util.List;
import java.util.logging.Level;

import static support.TestContext.*;
//import static support.TestContext.getWait;

public class Page implements Loggable, Screenshot{

    // fields
    protected String url;
    protected String title;

    // constructor
    public Page() {
        PageFactory.initElements(getDriver(), this);
    }

    public void open() {
        System.out.println(url);
        getDriver().get(url);
    }

    public void refresh() {
        getDriver().navigate().refresh();
    }

    public boolean areErrorsPresent() {
        LogEntries entries = getDriver().manage().logs().get(LogType.BROWSER);
        for (LogEntry entry : entries) {
            if (entry.getLevel().equals(Level.SEVERE)) {
                logError(entry.toString());
                return true;
            }
        }
        return false;
    }

    protected WebElement getByXpath(String xpath) {
        return getDriver().findElement(By.xpath(xpath));
    }

    protected List<WebElement> getAllByXpath(String xpath) {
        return getDriver().findElements(By.xpath(xpath));
    }

    protected void mouseOver(WebElement element) {
        getActions().moveToElement(element).perform();
    }

    protected void waitForVisible(WebElement element) {
        getWait().until(ExpectedConditions.visibilityOf(element));
    }

    protected void waitForVisible(WebElement element, int i) {
        getWait(i).until(ExpectedConditions.visibilityOf(element));
    }

    protected void waitForDisappear(WebElement element) {
        getWait().until(ExpectedConditions.invisibilityOf(element));
    }

    protected void waitUntilContainsText(WebElement element) {
        getWait().until(driver -> !element.getText().isEmpty());
    }

    protected void waitForClickable(WebElement element) {
        getWait().until(ExpectedConditions.elementToBeClickable(element));
    }

    protected void waitToBeSelected(WebElement element) {
        getWait().until(ExpectedConditions.elementToBeSelected(element));
    }

    protected void click(WebElement element) {
        waitForClickable(element);
        try {
            element.click();
        } catch (ElementClickInterceptedException e) {
            System.err.println("Exception clicking on element! Clicking with JS...");
            clickWithJS(element);
        }
    }

    protected void sendKeys(WebElement element, String value) {
        waitForVisible(element);
        element.sendKeys(value);
    }

    protected void clickWithJS(WebElement element) {
        getExecutor().executeScript("arguments[0].click();", element);
    }

    protected void clickWithActions(WebElement element) {getActions().click(element).perform();};

}