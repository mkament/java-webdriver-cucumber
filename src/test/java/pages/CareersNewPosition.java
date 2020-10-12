package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import static support.TestContext.getWait;

public class CareersNewPosition extends CareersRecruit{
    @FindBy(xpath = "//label[@for='positionTitle']/../input")
    WebElement titleField;

    @FindBy(xpath = "//textarea")
    WebElement descriptionField;

    @FindBy(xpath = "//label[@for='positionAddress']/../input")
    WebElement addressField;

    @FindBy(xpath = "//label[@for='positionCity']/../input")
    WebElement cityField;

    @FindBy(xpath = "//select")
    WebElement stateSelect;

    @FindBy(xpath = "//label[@for='positionZip']/../input")
    WebElement zipField;

    @FindBy(xpath = "//input[@id='positionDateOpen']")
    WebElement dateDropdown;

    @FindBy(xpath = "//div[text()='Today']")
    WebElement calendarLinkToday;

    @FindBy(xpath = "//button[@id='positionSubmit']")
    WebElement submitButton;

    public void FillOutTitle(String str){
        getWait(10).until(ExpectedConditions.visibilityOf(titleField));
        titleField.sendKeys(str);
    }

    public void FillOutDescription(String str){
        getWait(10).until(ExpectedConditions.visibilityOf(descriptionField));
        descriptionField.sendKeys(str);
    }

    public void FillOutAddress(String str){
        addressField.sendKeys(str);
    }

    public void FillOutCity(String str){
        cityField.sendKeys(str);
    }

    public void SelectState(String value){
        new Select(stateSelect).selectByVisibleText(value);
    }

    public void FillOutZip(String str){
        zipField.sendKeys(str);
    }

    public void FillOutDate(){
        dateDropdown.click();
        calendarLinkToday.click();
    }

    public void SubmitForm(){
        submitButton.click();
    }
}
