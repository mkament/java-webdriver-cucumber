package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CareersHeader extends Page{
    public CareersHeader() {
        url = "https://skryabin-careers.herokuapp.com/";
    }

    @FindBy(xpath = "//button[contains(text(),'My Jobs')]")
    private WebElement myJobsBtn;

    @FindBy(xpath = "//button[contains(text(),'Careers')]")
    private WebElement CareersBtn;

    @FindBy(xpath="//*[contains(text(),'Login')]")
    private WebElement loginBtn;

    @FindBy(xpath = "//span[@class='logout-box']/a")
    private WebElement usernameCaption;

    @FindBy(xpath = "//button[contains(text(),'Recruit')]")
    private WebElement recruitBtn;

    @FindBy(xpath = "//button[text()='Apply']")
    private WebElement applyBtn;

    @FindBy(xpath = "//button[@id='positionsQuickSearchButton']")
    private WebElement searchButton;

    @FindBy(xpath="//span[@class='logout-box']/a")
    private WebElement loggedUserName;

    public void clickLogin() {
        mouseOver(loginBtn);
        click(loginBtn);
    }

    public void clickMyJobsBtn() {
        mouseOver(myJobsBtn);
        click(myJobsBtn);
    }

    public void clickCareersBtn() {
        mouseOver(CareersBtn);
        click(CareersBtn);
    }

    public CareersProfile clickRecruitBtn() {
        mouseOver(applyBtn);
        click(applyBtn);
        return new CareersProfile();
    }

    public CareersProfile clickApplyBtn() {
        mouseOver(applyBtn);
        click(applyBtn);
        return new CareersProfile ();
    }

    public void clickSearchBtn() {
        mouseOver(searchButton);
        click(searchButton);
    }

    public String getUsername (){
        return usernameCaption.getText();
    }

    public CareersProfile clickUsername(){
        usernameCaption.click();
        return new CareersProfile();
    }

    public boolean isRecruitButtonPresent(){
        if (recruitBtn.isDisplayed()) return true;
        else return false;
    }

    public boolean isProfileNamePresent(){
        if (usernameCaption.isDisplayed()) return true;
        else return false;
    }

    public String getLoggedUserName() {
        return loggedUserName.getText();
    }
}
