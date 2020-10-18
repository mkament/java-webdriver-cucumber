package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;

import static support.TestContext.getData;
import static support.TestContext.getWait;

public class CareersRecruit extends CareersHeader {
    private WebElement positionCard(String title) {     // dynamic element
        return getByXpath("//h4[text()='" + title + "']/ancestor::div[contains(@class,'card-body')]");
    }

    private List<WebElement> allPositionCards(String title) {
        return getAllByXpath("//h4[text()='" + title + "']/ancestor::div[contains(@class,'card-body')]");
    }

    private WebElement closeForPosition(String title) {
        return getByXpath("//h4[text()='" + title + "']/ancestor::div[contains(@class,'card')]//button");
    }

    @FindBy(xpath = "//h4[@class='card-title'][text()='New Position']")
    private WebElement linkNewPosition;

    private List<WebElement> allPresentPositionCards(){
        return getAllByXpath("//span[(contains(text(), 'Candidates: ' ))]");
    }


    public boolean isPositionVisible(String title) {
//        List<WebElement> cards = allPositionCards(title);
//        if (cards.isEmpty()) {
//            return false;
//        } else {
//            return cards.get(0).isDisplayed();
//        }
        try {
            return positionCard(title).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public CareersRecruit removePosition(String title) {
        WebElement card = positionCard(title);
        WebElement closeButton = closeForPosition(title);
        mouseOver(card);
        waitForClickable(closeButton);
        click(closeButton);
        waitForDisappear(card);
        return new CareersRecruit();
    }

    public CareersNewPosition clickNewPosition(){
        getWait(10).until(ExpectedConditions.visibilityOf(linkNewPosition));
        linkNewPosition.click();
        return new CareersNewPosition();
    }

    public List <WebElement> GetAllCardTitles (){
        //getWait(10).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//span[(contains(text(), 'Candidates: ' ))]"));
        getWait(10).until(ExpectedConditions.visibilityOfAllElements(allPresentPositionCards()));
        return getAllByXpath("//h4[@class='card-title']");
    }

    public boolean IsCardFound (List <WebElement> l, String pos) {
        //List <WebElement> webElementsList = new CareersRecruit().GetAllCardTitles();
        getWait(10).until(ExpectedConditions.visibilityOfAllElements(allPresentPositionCards()));
        boolean present = false;
        for (WebElement el : l) {
            if (el.getText().equals(pos)) {
                present = true;
            }
        }
        return present;
    }
}