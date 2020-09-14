package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static support.TestContext.getDriver;

public class QuoteResult {
    //fields
    @FindBy(name="dateOfBirth")
    private WebElement dateOfBirth;

    @FindBy(name="countryOfOrigin")
    private WebElement country;

    @FindBy(name="firstName")
    private WebElement firstName;

    @FindBy(name="lastName")
    private WebElement lastName;

    @FindBy(name="gender")
    private WebElement gender;

    @FindBy(name="allowedToContact")
    private WebElement allowedToContact;

    @FindBy(name="carMake")
    private WebElement car;

    @FindBy(id = "return")
    private WebElement returnBtn;

    @FindBy(name = "username")
    private WebElement username;

    @FindBy(name = "email")
    private WebElement email;

    @FindBy(name = "name")
    private WebElement name;

    @FindBy(name = "phone")
    private WebElement phone;

    @FindBy(name = "address")
    private WebElement address;

    @FindBy(name = "location")
    private WebElement location;

    @FindBy(name = "password")
    private WebElement password;

    @FindBy(name="thirdPartyAgreement")
    private WebElement thirdPartyAgreement;

    @FindBy(name="agreedToPrivacyPolicy")
    private WebElement agreedToPrivacyPolicy;

    public QuoteResult() {
        PageFactory.initElements(getDriver(), this);

    }
    public String getUsername() {
        return username.getText();
    }
    public String getPassword() {
        return password.getText();
    }
    public String getName() {
        return name.getText();
    }
    public String getEmail() {
        return email.getText();
    }
    public String getAddress() {
        return address.getText();
    }
    public String getPhone() {
        return phone.getText();
    }
    public String getLocation() {
        return location.getText();
    }
    public String getThirdPartyAgreement() {
        return thirdPartyAgreement.getText();
    }
    public String getCar() {
        return car.getText();
    }
    public String getAllowedToContact() {
        return allowedToContact.getText();
    }
    public String getGender() {
        return gender.getText();
    }
    public String getCountry() {
        return country.getText();
    }
    public String getFirstName() {
        return firstName.getText();
    }
    public String getLastName() {
        return lastName.getText();
    }
    public String getDateOfBirth() {
        return dateOfBirth.getText();
    }
    public String getAgreedToPrivacyPolicy() {
        return agreedToPrivacyPolicy.getText();
    }
}