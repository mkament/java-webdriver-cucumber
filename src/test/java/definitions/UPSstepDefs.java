package definitions;

import cucumber.api.java8.En;
import cucumber.api.java.en.*;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static support.TestContext.getData;
import static support.TestContext.*;

public class UPSstepDefs {
    @And("I open Shipping menu")
    public void iOpenShippingMenu() {
        getDriver().findElement(By.xpath("//button[@class='close_btn_thick']/span")).click();
        getDriver().findElement(By.xpath("//a[@id='ups-menuLinks2']")).click();
    }

    @And("I go to Create a Shipment")
    public void iGoToCreateAShipment() {
        getDriver().findElement(By.xpath("//a[contains(text(),'Create a Shipment:')]")).click();
        WebDriverWait wait = new WebDriverWait(getDriver(), 5);
        WebElement result = getDriver().findElement(By.xpath("//h2[contains(text(), 'Hello')]"));
        wait.until(ExpectedConditions.visibilityOf(result));
    }

    @When("I fill out origin shipment fields")
    public void iFillOutOriginShipmentFields() {
        Map<String, String> user = getData("from");
        getDriver().findElement(By.xpath("//input[@id='originname']")).sendKeys(user.get("name"));
        getDriver().findElement(By.xpath("//input[@id='originaddress1']")).sendKeys(user.get("address"));
        getDriver().findElement(By.xpath("//input[@id='originpostal']")).sendKeys(user.get("zip"));

        WebElement result = getDriver().findElement(By.xpath("//input[@id='origincity']"));
        getWait().until(ExpectedConditions.textToBePresentInElementValue(result, user.get("city")));

        WebElement result2 = getDriver().findElement(By.xpath("//select[@id='originstate']/option[contains(text(),'" + user.get("state") +"')]")); //selection in dropdown
        getWait().until(ExpectedConditions.elementSelectionStateToBe(result2, true));
        getDriver().findElement(By.xpath("//input[@id='originemail']")).sendKeys(user.get("email"));
        getDriver().findElement(By.xpath("//input[@id='originphone']")).sendKeys(user.get("phone"));
    }

    @And("I submit the shipment form")
    public void iSubmitTheShipmentForm() throws InterruptedException{
        WebElement target = getDriver().findElement(By.xpath("//button[@id='nbsBackForwardNavigationContinueButton']"));
        getExecutor().executeScript("arguments[0].click();", target);
        Thread.sleep(2000);
    }

    @Then("I verify origin shipment fields submitted And I cancel the shipment form")
    public void iVerifyOriginShipmentFieldsSubmittedAndICancelTheShipmentForm() {
        String actualDocuments = getDriver().findElement(By.xpath("//h2[@class='ups-section_heading ups-centered_header text-center ng-star-inserted']")).getText();
        assertThat(actualDocuments).contains("Where is your shipment going");
        WebElement target = getDriver().findElement(By.xpath("//button[contains(text(),'Cancel')]"));
        getExecutor().executeScript("arguments[0].click();", target);
        getDriver().findElement(By.xpath("//button[@id='nbsCancelShipmentWarningYes']")).click();
    }

    @Then("I verify shipment form is reset")
    public void iVerifyShipmentFormIsReset() {
        String actualDocuments = getDriver().findElement(By.xpath("//h2[@class='ups-section_heading ups-centered_header text-center ng-star-inserted']")).getText();
        assertThat(actualDocuments).contains("Hello");
        String actualDocuments2 = getDriver().findElement(By.xpath("//input[@id='originname']")).getText();
        assertThat(actualDocuments2).contains("");
    }

    @Then("I verify origin shipment fields submitted")
    public void iVerifyOriginShipmentFieldsSubmitted() {
        String actualDocuments = getDriver().findElement(By.xpath("//h2[@class='ups-section_heading ups-centered_header text-center ng-star-inserted']")).getText();
        assertThat(actualDocuments).contains("Where is your shipment going");
    }

    @When("I fill out destination shipment fields")
    public void iFillOutDestinationShipmentFields() {
        Map<String, String> user = getData("to");
        getDriver().findElement(By.xpath("//input[@id='destinationname']")).sendKeys(user.get("name"));
        getDriver().findElement(By.xpath("//input[@id='destinationaddress1']")).sendKeys(user.get("address"));
        getDriver().findElement(By.xpath("//input[@id='destinationpostal']")).sendKeys(user.get("zip"));

        WebElement result = getDriver().findElement(By.xpath("//input[@id='destinationcity']"));
        getWait().until(ExpectedConditions.textToBePresentInElementValue(result, user.get("city")));

        WebElement result2 = getDriver().findElement(By.xpath("//select[@id='destinationstate']/option[contains(text(),'" + user.get("state") +"')]")); //selection in dropdown
        getWait().until(ExpectedConditions.elementSelectionStateToBe(result2, true));
        getDriver().findElement(By.xpath("//input[@id='destinationemail']")).sendKeys(user.get("email"));
        getDriver().findElement(By.xpath("//input[@id='destinationphone']")).sendKeys(user.get("phone"));
    }

    @And("I set packaging type and weight")
    public void iSetPackagingTypeAndWeight() throws InterruptedException{
//        Select parcelType = new Select(getDriver().findElement(By.xpath("//select[@id='nbsPackagePackagingTypeDropdown0']")));
//        parcelType.selectByVisibleText("UPS Express Box - Small");
//        getDriver().findElement(By.id("nbsPackagePackageWeightField0")).sendKeys("1.6");
        getDriver().findElement(By.xpath("//input[@id='nbsPackagePackageWeightField0']")).sendKeys("23");
        getDriver().findElement(By.xpath("//select[@id='nbsPackagePackagingTypeDropdown0']")).click();
//        WebElement type = getDriver().findElement(By.xpath("//option[contains(text(),'UPS Express Box - Small')]"));
//        getActions().moveToElement(type).click().build().perform();
        Select parcelType = new Select(getDriver().findElement(By.xpath("//select[@id='nbsPackagePackagingTypeDropdown0']")));
        parcelType.selectByVisibleText("UPS Express Box - Small");
        Thread.sleep(4000);
//        WebElement target = getDriver().findElement(By.xpath("//input[@id='nbsPackagePackageWeightField0']"));
//        getExecutor().executeScript("arguments[0].value='1.4';", target);
//       getActions().moveToElement(target).click().sendKeys("1.8").perform();
//        Thread.sleep(1000);
//        target.sendKeys(Keys.UP);
//        WebElement target2 = getDriver().findElement(By.xpath("//input[@id='nbsPackageDeclaredValueField0']"));
//        getExecutor().executeScript("arguments[0].value='4.99';", target2);
//        Thread.sleep(3000);
//        WebElement result = getDriver().findElement(By.xpath("//p[contains(text(),'38 in')]")); //property of pkg type
//        getWait(10).until(ExpectedConditions.visibilityOf(result));
    }

    @Then("I verify total charges appear")
    public void iVerifyTotalChargesAppear() {

    }
}
