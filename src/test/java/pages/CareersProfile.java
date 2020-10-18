package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class CareersProfile extends CareersHeader{

    @FindBy(xpath = "//button[contains(text(),'Delete')]")
    private WebElement DeleteAccountBtn;

    @FindBy(xpath = "//button[contains(text(),'Edit')]")
    private WebElement EditAccountBtn;

    @FindBy (xpath = "//input[contains(@placeholder, 'First')]")
    private WebElement firstNameField;

    @FindBy (xpath = "//input[contains(@placeholder, 'Last')]")
    private WebElement lastNameField;

    @FindBy (xpath = "//input[contains(@placeholder, 'Email')]")
    private WebElement emailField;

    @FindBy (xpath = "//input[contains(@placeholder, 'er Password')]")
    private WebElement passwordField;

    @FindBy (xpath = "//input[contains(@placeholder, 'rm Password')]")
    private WebElement confirmPasswordField;

    @FindBy (xpath = "//textarea[contains(@placeholder, 'Summary')]")
    private WebElement summaryField;

    @FindBy (xpath = "//input[contains(@placeholder, 'City')]")
    private WebElement cityField;

    @FindBy (xpath = "//select")
    private WebElement stateSelect;

    @FindBy (xpath = "//button[@id='candidateSubmit']")
    private WebElement SubmitBtn;

    public void enterName (String first, String last){
        firstNameField.clear();
        firstNameField.sendKeys(first);
        lastNameField.clear();
        lastNameField.sendKeys(last);
    }

    public void enterEmailPassword(String email, String password){
        emailField.clear();
        emailField.sendKeys(email);
        passwordField.sendKeys(password);
        confirmPasswordField.sendKeys(password);
    }

    public void enterSummary(String summary){
        summaryField.clear();
        summaryField.sendKeys(summary);
    }

    public void enterAddress(String city, String state){
        cityField.clear();
        cityField.sendKeys(city);
        new Select(stateSelect).selectByValue(state);
    }

    public void submitApplication(){
        SubmitBtn.click();
    }

    public void clickDeleteAccountBtn(){
        DeleteAccountBtn.click();
    }

    public void clickEditAccountBtn(){
        EditAccountBtn.click();
    }
}
