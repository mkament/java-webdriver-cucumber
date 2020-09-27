package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CareersLogin extends CareersHeader {

    @FindBy(xpath="//input[@placeholder='Please enter an Email']")
    private WebElement usernameField;

    @FindBy(xpath="//input[@placeholder='Please enter a Password']")
    private WebElement passwordField;

    @FindBy(xpath = "//button[@id='loginButton']")
    private WebElement loginBtn;


    public void doLogin(String username, String password) {
        mouseOver(usernameField);
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        loginBtn.click();
    }
}
