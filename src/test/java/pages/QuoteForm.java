package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import static support.TestContext.*;

public class QuoteForm {
    // fields

    private String url;
    private String title;

    @FindBy(xpath = "//label[@for='username']/../input")
    private WebElement username;

    @FindBy(name = "email")
    private WebElement email;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(name = "confirmPassword")
    private WebElement confirmPassword;

    @FindBy(xpath = "//label[@for='name']/../input")
    private WebElement name;

    @FindBy(id = "firstName")
    private WebElement firstName;

    @FindBy(id = "lastName")
    private WebElement lastName;

    @FindBy(id = "middleName")
    private WebElement middleName;

    @FindBy(xpath = "//span[text()='Save']")
    private WebElement saveButton;

    @FindBy(xpath = "//span[text()='Cancel']")
    private WebElement cancelDialogButton;

    @FindBy(name = "agreedToPrivacyPolicy")
    private WebElement privacy;

    @FindBy(name = "phone")
    private WebElement phone;

    @FindBy(id = "dateOfBirth")
    private WebElement dateOfBirth;

    @FindBy(name = "countryOfOrigin")
    private WebElement countryOfOrigin;

    @FindBy(xpath = "//input[@value='female']")
    private WebElement female;

    @FindBy(xpath = "//input[@value='male']")
    private WebElement male;

    @FindBy(id = "formSubmit")
    private WebElement submit;

//    @FindBy(id = "contactPersonName")
//    private WebElement contactPersonName;
//
//    @FindBy(id = "contactPersonPhone")
//    private WebElement contactPersonPhone;

    @FindBy(name = "allowedToContact")
    private WebElement allowedToContact;

    @FindBy(id = "thirdPartyButton")
    private WebElement thirdPartyButton;

    @FindBy(id = "address")
    private WebElement address;

    @FindBy(name = "car")
    private WebElement car;

    @FindBy(id = "username-error")
    private WebElement usernameError;

    @FindBy(id = "email-error")
    private WebElement emailError;

    @FindBy(id = "password-error")
    private WebElement passwordError;

    @FindBy(id = "confirmPassword-error")
    private WebElement passwordConfirmError;

    @FindBy(id = "name-error")
    private WebElement nameError;

    @FindBy(id = "agreedToPrivacyPolicy-error")
    private WebElement agreedToPrivacyPolicyError;

    // constructor
    public QuoteForm() {
        PageFactory.initElements(getDriver(), this);
        url = "https://skryabin.com/market/quote.html";
        title = "Get a Quote";
    }

    // methods

    public void open() {
        getDriver().get(url);
    }

    public void fillUsername(String value) {
        username.clear();
        username.sendKeys(value);
    }

    public void fillEmail(String value) {
        email.sendKeys(value);
    }

    public void fillBothPasswords(String value) {
        password.sendKeys(value);
        confirmPassword.sendKeys(value);
    }
    public void fillPassword(String value) {
        password.clear();
        password.sendKeys(value);
    }
    public void fillPasswordConfirm(String value) {
        confirmPassword.sendKeys(value);
    }

    public void fillName(String firstNameValue, String lastNameValue) {
        name.click();
        firstName.sendKeys(firstNameValue);
        lastName.sendKeys(lastNameValue);
        saveButton.click();
    }

    public void fillName(String firstNameValue, String middleNameValue, String lastNameValue) {
        name.click();
        firstName.sendKeys(firstNameValue);
        middleName.sendKeys(middleNameValue);
        lastName.sendKeys(lastNameValue);
        saveButton.click();
    }

    public void agreeWithPrivacyPolicy() {
        if (!privacy.isSelected()) {
            privacy.click();
        }
    }

    public void fillCountry(String country) {
        Select selectCountry = new Select(countryOfOrigin);
//        getWait(10).until(ExpectedConditions.elementToBeSelected(selectCountry);
//        getActions().moveToElement(selectCountry).perform();
        //selectCountry.selectByValue(country);
        selectCountry.selectByVisibleText(country);
    }

    public void submit() {
        submit.click();
    }

    public String getElementText(String str) {
        switch (str) {
            case "name":
//                getExecutor().executeScript("arguments[0].click();", name);
//                cancelDialogButton.click();
                getWait(25).until(ExpectedConditions.visibilityOf(name));
                return this.name.getAttribute("value");
            default:
                throw new RuntimeException("Unknown WebElement: " + str);
        }
    }

    public void fillPhone(String value) {
        phone.sendKeys(value);
    }

    public void fillDateOfBirth(String date) {
        //dateOfBirth.click();
        dateOfBirth.sendKeys(date);
        getActions().moveToElement(dateOfBirth).sendKeys(Keys.TAB).perform();
    }

    public void clickGender(String sex) {
        switch (sex) {
            case "female":
                female.click();
                break;
            case "male":
                male.click();
                break;
            case "Mermaid King-Queen":
                System.out.println("Ladies and gentlemen, boys and girls, and now, please welcome the fabulous Mermaid King-Queen!!!");
                break;
            case "Gender number 114":
                System.out.println("Hello there!");
                break;
            default:
                throw new RuntimeException("Unknown selection: " + sex);
        }

    }


    public void clickAllowedToContact() {
        if (!allowedToContact.isSelected()) {
            allowedToContact.click();
        }
    }

    public void fillAddress(String value) {
        address.sendKeys(value);
    }

    public void fillCar(String value) {
        Select selectCar = new Select(car);
        selectCar.selectByValue(value);
    }

    public String getUsernameErrorValue() {
        return usernameError.getText();
    }

    public String getEmailErrorValue() {
        return emailError.getText();
    }

    public String getPasswordErrorValue() {
        return passwordError.getText();
    }

    public String getPasswordConfirmErrorValue() {
        return passwordConfirmError.getText();
    }

    public String getNameErrorValue() {
        return nameError.getText();
    }

    public String getAgreedToPrivacyPolicyErrorValue() {
        return agreedToPrivacyPolicyError.getText();
    }

    public void clickThirdPartyButton() {
        thirdPartyButton.click();
        getDriver().switchTo().alert().accept();
    }
}