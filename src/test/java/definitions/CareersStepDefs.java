package definitions;
import cucumber.api.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.*;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;
import static support.TestContext.*;
import cucumber.api.java.en.And;

public class CareersStepDefs {
    CareersHeader careersHeader = new CareersHeader();
    CareersLogin careersLogin = new CareersLogin();
    CareersPositions careersPositions = new CareersPositions();
    CareersRecruit careersRecruit = new CareersRecruit();

    @And("I login as {string}")
    public void iLoginAs(String arg0) {

        careersHeader.clickLogin();
        careersLogin.doLogin("owen@example.com", "welcome");
    }

    @Then("I verify {string} login")
    public void iVerifyLogin(String arg0) {
        assertThat(careersHeader.isRecruitButtonPresent()).isEqualTo(true);
    }

    @When("I remove {string} position")
    public void iRemovePosition(String pos) {
        careersHeader.clickRecruitBtn();
        careersPositions.deletePosition(pos);
    }

    @And("I verify {string} position is removed")
    public void iVerifyPositionIsRemoved(String pos) {
        assertThat(careersPositions.isPositionPresent(pos)).isEqualTo(false);
    }

    @When("I create new position")
    public void iCreateNewPosition() {
        Map<String, String> position = getData("position");
        careersHeader.clickRecruitBtn();
        CareersNewPosition pos = careersRecruit.clickNewPosition();
        pos.FillOutDescription(position.get("description"));
        pos.FillOutTitle(position.get("title"));
        pos.FillOutAddress(position.get("address"));
        pos.FillOutCity(position.get("city"));
        pos.SelectState(position.get("state"));
        pos.FillOutZip(position.get("zip"));
        pos.FillOutDate();
        pos.SubmitForm();
    }

    @Then("I verify new position is created")
    public void iVerifyNewPositionIsCreated() {
        List <WebElement> cards = careersRecruit.GetAllCardTitles();
        boolean is = careersRecruit.IsCardFound(cards);
        assertThat(is).isEqualTo(true);
    }

    @When("I remove new position")
    public void iRemoveNewPosition() {
        String pos = getData("position").get("title");
        careersRecruit.removePosition(pos);
    }

    @And("I verify new position is removed")
    public void iVerifyNewPositionIsRemoved() {
        List <WebElement> cards = careersRecruit.GetAllCardTitles();
        boolean is = careersRecruit.IsCardFound(cards);
        assertThat(is).isEqualTo(false);
    }
}
