package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import static support.TestContext.*;

public class UPSHome {

    private String url;
    // elements

    @FindBy(xpath = "//button[@class='close_btn_thick']/span")
    private WebElement cookiesDialog;

    @FindBy(xpath = "//a[@id='ups-menuLinks2']")
    private WebElement shippingLink;

    @FindBy(xpath = "//a[contains(text(),'Create a Shipment:')]")
    private WebElement createShipmentSubmenu;

    @FindBy(xpath = "//h2[contains(text(), 'Hello')]")
    private WebElement shippingPageTitle;

    // constructor
    public UPSHome () {
        PageFactory.initElements(getDriver(), this);
        url = "https://www.ups.com/us/en/Home.page";
    }

    // methods
    public void open() {
        getDriver().get(url);
    }

    public void closeCookiesDialog (){
        if (cookiesDialog.isDisplayed()) {
            cookiesDialog.click();
        }
    }

    public void clickShippingLink (){
        getWait().until(ExpectedConditions.visibilityOf(shippingLink));
        getExecutor().executeScript("arguments[0].click();", shippingLink);
    }
    public void goToShippingPage (){
        createShipmentSubmenu.click();
        getWait().until(ExpectedConditions.visibilityOf(shippingPageTitle));
    }
}
