package definitions;

import cucumber.api.java8.En;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Given;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.assertj.core.api.Assertions.assertThat;
import static support.TestContext.getDriver;

public class UnitCovertersDefs {
    @When("I navigate to {string} tab")
    public void iHavigateToTab(String tab) {
        getDriver().findElement(By.xpath("//div[@id='menu']//a[contains(text(),'" +tab+ "')]")).click();
    }

    @And("I enter {string} and {string} and also {string} info")
    public void iEnterAndAndAlsoInfo(String amount, String setting1, String setting2) {
        getDriver().findElement(By.xpath("//input[@name='fromVal']")).clear();
        getDriver().findElement(By.xpath("//select[@id='calFrom']//option[contains(text(),'"+ setting1 + "')]")).click();
        getDriver().findElement(By.xpath("//select[@id='calTo']//option[contains(text(),'"+ setting2 + "')]")).click();
        getDriver().findElement(By.xpath("//input[@name='fromVal']")).sendKeys(amount);
    }

    @Then("I verify that the expected result is {string}")
    public void iVerifyThatTheExpectedResultIs(String expected) {
        String result = getDriver().findElement(By.xpath("//div[@id='calResults']")).getText();
        assertThat(result).contains(expected);
    }
}

