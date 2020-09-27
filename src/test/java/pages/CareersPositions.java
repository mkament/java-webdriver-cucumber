package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static support.TestContext.getDriver;

public class CareersPositions extends CareersHeader {
    CareersHeader careersHeader = new CareersHeader();

    @FindBy(xpath = "//body/div[@id='root']/div[1]")
    WebElement positionsContainingPane;

    //Dynamic selectors
    private WebElement positionDeleteBtn (String position) {
        return getDriver().findElement(By.xpath("//h4[text()='" + position + "']/../../..//button/i"));}

    private WebElement positionText (String position) {
        return getDriver().findElement(By.xpath("//h4[contains(text(),'" + position + "')]"));}

        public void deletePosition(String pos){
        mouseOver(positionText(pos));
        waitForClickable(positionDeleteBtn(pos));
        positionDeleteBtn(pos).click();
        }

    public boolean isPositionPresent(String pos){
        careersHeader.clickSearchBtn(); //this line is just to use up time for Positions Pane to refresh content
        waitForVisible(positionsContainingPane, 10);
        if (positionsContainingPane.getText().contains(pos)) return true;
        else return false;
    }
}
