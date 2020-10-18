package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static support.TestContext.getDriver;

public class CareersPositions extends CareersHeader {
    CareersHeader careersHeader = new CareersHeader();


    @FindBy (xpath = "//button[contains(text(),'Apply')]")
    private WebElement PositionApplyBtn;

    private WebElement positionCard(String title) {     // dynamic element
        return getByXpath("//h4[text()='" + title + "']/ancestor::div[contains(@class,'card-body')]");
    }

    @FindBy(xpath = "//body/div[@id='root']/div[1]")
    private  WebElement positionsContainingPane;

    @FindBy(xpath = "//h4[contains(text(),'No positions found')]")
    private WebElement noPositionsFound;

    //Dynamic selectors
    private WebElement positionDeleteBtn(String position) {
        return getDriver().findElement(By.xpath("//h4[text()='" + position + "']/../../..//button/i"));
    }

    private WebElement positionText(String position) {
        return getDriver().findElement(By.xpath("//h4[contains(text(),'" + position + "')]"));
    }

    public void deletePosition(String pos) {
        waitForVisible(positionText(pos));
        mouseOver(positionText(pos));
        waitForClickable(positionDeleteBtn(pos));
        positionDeleteBtn(pos).click();
        waitForDisappear(positionText(pos));
    }

    public boolean isPositionPresent(String pos) {
        //careersHeader.clickSearchBtn(); //this line is just to use up time for Positions Pane to refresh content
        waitForVisible(positionsContainingPane, 10);
        if (positionsContainingPane.getText().contains(pos)) return true;
        else return false;
    }
    public void clickPosition(String pos) {
        waitForClickable(positionText(pos));
        mouseOver(positionText(pos));

        positionText(pos).click();
    }

    public boolean isPostionButtonPresent(String pos) {
        try {
            return positionDeleteBtn(pos).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
    public void clickPostionClickApply(String pos){
        waitForClickable(positionText(pos));
        positionText(pos).click();
        waitForClickable(PositionApplyBtn);
        PositionApplyBtn.click();
        //waitForDisappear(positionCard(pos));
    }
}
