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
import support.Loggable;

public class CareersStepDefs implements Loggable {
    CareersHeader careersHeader = new CareersHeader();
    CareersLogin careersLogin = new CareersLogin();
    CareersPositions careersPositions = new CareersPositions();
    CareersRecruit careersRecruit = new CareersRecruit();
    Map<String, String> candidate = getCandidate("sdet");
    Map<String, String> candidate2 = getCandidate("qa");

    @And("I login as {string}")
    public void iLoginAs(String arg0) {
        switch (arg0) {
            case "recruiter": {
                careersHeader.clickLogin();
                careersLogin.doLogin("owen@example.com", "welcome");
            }
            break;
            case "candidate": {
                careersHeader.clickLogin();
                careersLogin.doLogin("john.doe@example.com", candidate.get("password"));
            }
            break;
        }
    }

    @Then("I verify {string} login")
    public void iVerifyLogin(String arg0) {
        assertThat(careersHeader.isProfileNamePresent()).isEqualTo(true);
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
        List<WebElement> cards = careersRecruit.GetAllCardTitles();
        boolean is = careersRecruit.IsCardFound(cards, getData("position").get("title"));
        assertThat(is).isEqualTo(true);
    }

    @When("I remove new position")
    public void iRemoveNewPosition() {
        String pos = getData("position").get("title");
        careersRecruit.removePosition(pos);
    }

    @And("I verify new position is removed")
    public void iVerifyNewPositionIsRemoved() {
        List<WebElement> cards = careersRecruit.GetAllCardTitles();
        boolean is = careersRecruit.IsCardFound(cards, getData("position").get("title"));
        assertThat(is).isEqualTo(false);
    }

    @When("I update new position")
    public void iUpdateNewPosition() {
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
        careersPositions.clickPosition(position.get("title"));
        pos.clickEditExistingPositionBtn();
        pos.FillOutTitle(position.get("edited title"));
        pos.FillOutDate();
        pos.SubmitForm();
    }

    @Then("I verify new position is updated")
    public void iVerifyNewPositionIsUpdated() {
        careersHeader.clickRecruitBtn();
        List<WebElement> cards = careersRecruit.GetAllCardTitles();
        boolean is = careersRecruit.IsCardFound(cards, getData("position").get("edited title"));
        assertThat(is).isEqualTo(true);
    }

    @And("I submit application to a new position")
    public void iSubmitApplicationToANewPosition() {
        CareersProfile careersProfile = careersHeader.clickApplyBtn();
        careersProfile.enterName(candidate.get("firstName"), candidate.get("lastName"));
        careersProfile.enterEmailPassword(candidate.get("email"), candidate.get("password"));
        careersProfile.enterSummary("Summary text");
        careersProfile.enterAddress(candidate.get("city"), candidate.get("state"));
        careersProfile.submitApplication();
    }

    @Then("I verify new candidate is created")
    public void iVerifyNewCandidateIsCreated() {
        assertThat(careersHeader.getLoggedUserName()).isEqualTo(candidate.get("firstName") + " " + candidate.get("lastName"));
    }

    @When("I delete candidate profile")
    public void iDeleteCandidateProfile() {
        CareersProfile careersProfile = careersHeader.clickUsername();
        careersHeader.clickUsername(); //2nd click to make sure delete button appears on Profile page
        //NOTE: although method clickUsername() returns careersProfile object it is ignored and unused.
        careersProfile.clickDeleteAccountBtn();
    }

    @Then("I verify new candidate is removed")
    public void iVerifyNewCandidateIsRemoved() {
        assertThat(careersHeader.isProfileNamePresent()).isEqualTo(true);
    }

    @When("I update new candidate")
    public void iUpdateNewCandidate() {
        CareersProfile careersProfile = careersHeader.clickUsername();
        careersProfile.clickEditAccountBtn();
        careersProfile.enterName(candidate2.get("firstName"), candidate2.get("lastName"));
        careersProfile.submitApplication();
        careersProfile.refresh();
    }

    @Then("I verify new candidate is updated")
    public void iVerifyNewCandidateIsUpdated() {
        assertThat(careersHeader.getLoggedUserName()).isEqualTo(candidate2.get("firstName") + " " + candidate2.get("lastName"));
    }

    @When("I apply for a new position")
    public void iApplyForANewPosition() {
        careersPositions.clickPostionClickApply("Director, Product Development");

                //deletePosition("Director, Product Development");
    }

    @Then("I see new position marked as applied")
    public void iSeeNewPositionMarkedAsApplied() {
        careersHeader.clickCareersBtn();
        careersHeader.clickMyJobsBtn();
        //careersHeader.clickMyJobsBtn();
        //assertThat(careersPositions.isPostionButtonPresent("Director, Product Development")).isEqualTo(false);
    }

    @And("I see new position in my jobs")
    public void iSeeNewPositionInMyJobs() {
        //getWait(2).until(ExpectedConditions(careersPositions.positionText("Director, Product Development")))
        boolean present = careersPositions.isPositionPresent("Director, Product Development");
        assertThat(present).isEqualTo(true);
    }

    @When("I withdraw from new position")
    public void iWithdrawFromNewPosition() {
        careersPositions.deletePosition("Director, Product Development");
    }

    @Then("I don't see new position in my jobs")
    public void iDonTSeeNewPositionInMyJobs() {
        careersHeader.clickCareersBtn();
        careersHeader.clickMyJobsBtn();
        assertThat(careersPositions.isPostionButtonPresent("Director, Product Development")).isEqualTo(false);
    }
}

