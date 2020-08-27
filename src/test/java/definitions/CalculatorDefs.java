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

public class CalculatorDefs {
    @When("I navigate to {string}")
    public void iNavigateTo(String calcType) {
        getDriver().findElement(By.xpath("//a[contains(text(),'" + calcType + "')]")).click();

    }

    @And("I clear all calculator fields")
    public void iClearAllCalculatorFields() {
        getDriver().findElement(By.xpath("//input[@id='cloanamount']")).clear();
        getDriver().findElement(By.xpath("//input[@id='cloanterm']")).clear();
        getDriver().findElement(By.xpath("//input[@id='cinterestrate']")).clear();
        getDriver().findElement(By.xpath("//input[@id='cdownpayment']")).clear();
        getDriver().findElement(By.xpath("//input[@id='ctradeinvalue']")).clear();
        getDriver().findElement(By.xpath("//input[@id='csaletax']")).clear();
        getDriver().findElement(By.xpath("//input[@id='ctitlereg']")).clear();
    }

    @And("I calculate")
    public void iCalculate() {
        getDriver().findElement(By.xpath("//body//input[@value='Calculate']")).click();
    }

    @Then("I verify {string} calculator error")
    public void iVerifyCalculatorError(String error) {
        WebElement result = getDriver().findElement(By.xpath("//font[contains(text(),'" + error + "')]"));
        assertThat(result.getText()).contains(error);
    }

    @And("I enter {string} price, {string} months, {string} interest, {string} downpayment, {string} trade-in, {string} state, {string} percent tax, {string} fees")
    public void iEnterPriceMonthsInterestDownpaymentTradeInStatePercentTaxFees(String price, String term, String rate, String down, String trade, String state, String tax, String fees) {
        Select stateSelect = new Select(getDriver().findElement(By.xpath("//select[@name='cstate']")));
        stateSelect.selectByVisibleText(state);
        getDriver().findElement(By.xpath("//input[@id='cloanamount']")).sendKeys(price);
        getDriver().findElement(By.xpath("//input[@id='cloanterm']")).sendKeys(term);
        getDriver().findElement(By.xpath("//input[@id='cinterestrate']")).sendKeys(rate);
        getDriver().findElement(By.xpath("//input[@id='cdownpayment']")).sendKeys(down);
        getDriver().findElement(By.xpath("//input[@id='ctradeinvalue']")).sendKeys(trade);
        getDriver().findElement(By.xpath("//input[@id='csaletax']")).sendKeys(tax);
        getDriver().findElement(By.xpath("//input[@id='ctitlereg']")).sendKeys(fees);
        //getDriver().findElement(By.xpath("//span[@class='cbmark']")).click();
    }

    @Then("I verify monthly pay is {string}")
    public void iVerifyMonthlyPayIs(String amount) {
        WebElement result = getDriver().findElement(By.xpath("//h2[@class='h2result'][contains(text(),'Monthly Pay')]"));
        assertThat(result.getText()).contains(amount);
    }
}
