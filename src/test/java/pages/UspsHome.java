package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static support.TestContext.getActions;

public class UspsHome extends UspsHeader {

    public UspsHome() {
        url = "https://www.usps.com/";
    }

}