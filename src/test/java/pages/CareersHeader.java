package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CareersHeader extends Page{
    public CareersHeader() {
        url = "https://skryabin-careers.herokuapp.com/";
    }

    @FindBy(xpath="//*[contains(text(),'Login')]")
    private WebElement loginBtn;

    @FindBy(xpath = "//span[@class='logout-box']/a")
    private WebElement usernameCaption;

    @FindBy(xpath = "//button[contains(text(),'Recruit')]")
    private WebElement recruitBtn;

    @FindBy(xpath = "//button[@id='positionsQuickSearchButton']")
    private WebElement searchButton;

    public void clickLogin() {
        mouseOver(loginBtn);
        click(loginBtn);
    }

    public void clickRecruitBtn() {
        mouseOver(recruitBtn);
        click(recruitBtn);
    }

    public void clickSearchBtn() {
        mouseOver(searchButton);
        click(searchButton);
    }

    public String getUsername (){
        return usernameCaption.getText();
    }

    public boolean isRecruitButtonPresent(){
        if (recruitBtn.isDisplayed()) return true;
        else return false;
    }
}