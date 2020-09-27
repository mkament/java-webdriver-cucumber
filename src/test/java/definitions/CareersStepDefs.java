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
import static org.assertj.core.api.Assertions.assertThat;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;
import static support.TestContext.*;
import cucumber.api.java.en.And;

public class CareersStepDefs {
    CareersHeader careersHeader = new CareersHeader();
    CareersLogin careersLogin = new CareersLogin();
    CareersPositions careersPositions = new CareersPositions();

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
}
