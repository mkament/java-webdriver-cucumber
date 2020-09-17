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

public class UPSShipping {

    private String url;
    // elements

    @FindBy(xpath = "//button[@class='close_btn_thick']/span")
    private WebElement cookiesDialog;

    @FindBy(xpath = "//button[@id='nbsBackForwardNavigationContinueButton']")
    private WebElement nextButton;

    @FindBy(xpath = "//button[@id='nbsBackForwardNavigationReviewPrimaryButton']")
    private WebElement proceedButton;

    @FindBy(xpath = "//button[@id='nbsBackForwardNavigationCancelShipmentButton']")
    private WebElement cancelButton;

    @FindBy(xpath = "//button[@id='nbsCancelShipmentWarningYes']")
    private WebElement cancelShipmentWarning;

    @FindBy(xpath = "//h2[@class='ups-section_heading ups-centered_header text-center ng-star-inserted']")
    private WebElement pageHeader;

    // THE DYNAMIC FIELD ***************************************************************************
    private WebElement inputElement(String fieldName) {
        return getDriver().findElement(By.xpath("//input[@id='" +fieldName+ "']"));
    }

    // THE DYNAMIC FIELD ***************************************************************************
    private WebElement stateSelection(String stateName) {
        return getDriver().findElement(By.xpath("//select[@id='originstate']/option[contains(text(),'" + stateName + "')]"));
    }

    //CONSTRUCTOR
    public UPSShipping() {
        PageFactory.initElements(getDriver(), this);
    }

    //Methods
    public String getInputElementValue(String fieldName) {
        return inputElement(fieldName).getText();
    }

    public void populateShippingField (String fieldName, String text){
        inputElement(fieldName).sendKeys(text);
    }
    public void waitForElementValue(String el, String val){
        //WebElement result = getDriver().findElement(By.xpath("//input[@id='origincity']"));
        getWait().until(ExpectedConditions.textToBePresentInElementValue(inputElement(el), val));
    }

    public void waitForStateSelection(String state){
        getWait().until(ExpectedConditions.elementSelectionStateToBe(stateSelection(state), true));
    }

    public void clickForwardButton (){
        try {
            getWait(20).until(ExpectedConditions.visibilityOf(nextButton));
            getExecutor().executeScript("arguments[0].click();", nextButton);
            System.out.println("TRY RUN");
        } catch (RuntimeException e) {
            getWait(20).until(ExpectedConditions.visibilityOf(proceedButton));
            getExecutor().executeScript("arguments[0].click();", proceedButton);
            System.out.println("CATCH RUN");
        }
    }

    public void clickCancelButton (){
        getExecutor().executeScript("arguments[0].click();", cancelButton);
        cancelShipmentWarning.click();
    }
    public String getPageHeaderText(){
        return pageHeader.getText();
    }
//    public boolean isErrorFieldDisplayed(String fieldName) {
//        boolean isDisplayed;
//        try {
//            isDisplayed = errorElement(fieldName).isDisplayed();
//        } catch (NoSuchElementException e) {
//            isDisplayed = false;
//        }
//        return isDisplayed;
//    }
}
