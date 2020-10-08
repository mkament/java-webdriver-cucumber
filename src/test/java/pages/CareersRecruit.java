package pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;
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

}