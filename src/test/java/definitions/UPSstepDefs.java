package definitions;

import cucumber.api.java8.En;
import cucumber.api.java.en.*;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.QuoteForm;
import pages.UPSHome;
import pages.UPSShipping;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static support.TestContext.getData;
import static support.TestContext.*;

public class UPSstepDefs {

    UPSHome upsPg = new UPSHome();
    UPSShipping upsShip = new UPSShipping();

    String priceRecord;

    @And("I open Shipping menu")
    public void iOpenShippingMenu() {
        if (getDriver().findElement(By.xpath("//button[@class='close_btn_thick']/span")).isDisplayed()) {
            getDriver().findElement(By.xpath("//button[@class='close_btn_thick']/span")).click();
        }
        getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='ups-menuLinks2']")));
        WebElement element = getDriver().findElement(By.xpath("//a[@id='ups-menuLinks2']"));
        getExecutor().executeScript("arguments[0].click();", element);
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

        WebElement result2 = getDriver().findElement(By.xpath("//select[@id='originstate']/option[contains(text(),'" + user.get("state") + "')]")); //selection in dropdown
        getWait().until(ExpectedConditions.elementSelectionStateToBe(result2, true));
        getDriver().findElement(By.xpath("//input[@id='originemail']")).sendKeys(user.get("email"));
        getDriver().findElement(By.xpath("//input[@id='originphone']")).sendKeys(user.get("phone"));
    }

    @And("I submit the shipment form")
    public void iSubmitTheShipmentForm() throws InterruptedException {
        try {
            WebElement target = getDriver().findElement(By.xpath("//button[@id='nbsBackForwardNavigationContinueButton']"));
            getWait(20).until(ExpectedConditions.visibilityOf(target));
            getExecutor().executeScript("arguments[0].click();", target);
        } catch (RuntimeException e) {
            getWait(20).until(ExpectedConditions.visibilityOf(getDriver().findElement(By.xpath("//button[@id='nbsBackForwardNavigationReviewPrimaryButton']"))));
            getExecutor().executeScript("arguments[0].click();", getDriver().findElement(By.xpath("//button[@id='nbsBackForwardNavigationReviewPrimaryButton']")));
        }
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

        WebElement result2 = getDriver().findElement(By.xpath("//select[@id='destinationstate']/option[contains(text(),'" + user.get("state") + "')]")); //selection in dropdown
        getWait().until(ExpectedConditions.elementSelectionStateToBe(result2, true));
        getDriver().findElement(By.xpath("//input[@id='destinationemail']")).sendKeys(user.get("email"));
        getDriver().findElement(By.xpath("//input[@id='destinationphone']")).sendKeys(user.get("phone"));
    }

    @And("I set packaging type and weight")
    public void iSetPackagingTypeAndWeight() {
//        Select parcelType = new Select(getDriver().findElement(By.xpath("//select[@id='nbsPackagePackagingTypeDropdown0']")));
//        parcelType.selectByVisibleText("UPS Express Box - Small");
//        getDriver().findElement(By.id("nbsPackagePackageWeightField0")).sendKeys("1.6");
//        WebElement type = getDriver().findElement(By.xpath("//option[contains(text(),'UPS Express Box - Small')]"));
//        getActions().moveToElement(type).click().build().perform();
        Select parcelType = new Select(getDriver().findElement(By.xpath("//select[@id='nbsPackagePackagingTypeDropdown0']")));
        parcelType.selectByVisibleText("UPS Express Box - Small");
        getWait(25).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='nbsPackagePackageWeightField0']")));
        getDriver().findElement(By.xpath("//input[@id='nbsPackagePackageWeightField0']")).sendKeys("8");
//        WebElement target = getDriver().findElement(By.xpath("//button[@id='nbsBackForwardNavigationContinueButton']"));
//        getExecutor().executeScript("arguments[0].click();", target);
//        getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='nbsPackagePackageWeightField0']")));
//        getDriver().findElement(By.xpath("//input[@id='nbsPackagePackageWeightField0']")).sendKeys("8");
//        WebElement target = getDriver().findElement(By.xpath("//input[@id='nbsPackagePackageWeightField0']"));
//        getExecutor().executeScript("arguments[0].value='1.4';", target);
//        getActions().moveToElement(target).click().sendKeys("1.8").perform();
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
        WebElement bar = getDriver().findElement(By.xpath("//span[@id='nbsBalanceBarTotalCharges']"));
        getWait().until(ExpectedConditions.textToBePresentInElement(bar, "Total Charges"));
        assertThat(bar.getText()).contains("Total Charges");
        priceRecord = getDriver().findElement(By.xpath("//span[@id='total-charges-spinner']")).getText();
    }

    @And("I select cheapest delivery option")
    public void iSelectCheapestDeliveryOption() throws InterruptedException {
        //getExecutor().executeScript("arguments[0].click()", getDriver().findElement(By.xpath("(//div[contains(text(),'Lowest')])[1]/..//p[contains(@id,'TileTotalCharge')]")));
        getActions().moveToElement(getDriver().findElement(By.xpath("//em[@class='icon ups-icon-plus']"))).perform();
//        WebElement lowestPrice = getDriver().findElement(By.xpath("(//div[contains(text(),'Lowest')])[1]/.."));
//        getWait(20).until(ExpectedConditions.visibilityOf(lowestPrice));
//        getExecutor().executeScript("arguments[0].click()", lowestPrice);
        WebElement lowestPriceButton = getDriver().findElement(By.xpath("//input[@id='nbsServiceTileServiceRadio7']"));
        getExecutor().executeScript("arguments[0].click();", lowestPriceButton);
        //("(//div[contains(text(),'Lowest')])[1]/..//p[contains(@id,'TileTotalCharge')]")));
        //getDriver().findElement(By.xpath("//*[contains(text(),'Lowest Cost')]/..")).click();
    }

    @And("I set description and check Saturday Delivery type")
    public void iSetDescriptionAndCheckSaturdayDeliveryType() {
        getDriver().findElement(By.xpath("//input[@id='nbsShipmentDescription']")).sendKeys("Year supply of lipstick");
        getWait(15).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//saturday-delivery-option[@class='ng-star-inserted']//label")));
        getDriver().findElement(By.xpath("//saturday-delivery-option[@class='ng-star-inserted']//label")).click();

    }

    @Then("I verify total charges changed")
    public void iVerifyTotalChargesChanged() {
        String currentPrice = getDriver().findElement(By.xpath("//span[@id='total-charges-spinner']")).getText();
        System.out.println("Original price: " + priceRecord);
        System.out.println(("Modified price: " + currentPrice));
        assertThat(priceRecord != currentPrice);
    }

    @And("I select Paypal payment type")
    public void iSelectPaypalPaymentType() {
        getWait(10).until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='tile-4']//label[@class='ups-tile_button_content']")));
        getDriver().findElement(By.xpath("//div[@id='tile-4']//label[@class='ups-tile_button_content']")).click();
    }

    @Then("I review all recorded details on the review page")
    public void iReviewAllRecordedDetailsOnTheReviewPage() {
        String resultForm = getDriver().findElement(By.xpath("//main[@id='ups-main']")).getText();
        Map<String, String> from = getData("from");
        Map<String, String> to = getData("to");
        assertThat(resultForm.contains(to.get("name")));
        assertThat(resultForm.contains(to.get("address")));
        assertThat(resultForm.contains(to.get("zip")));
        assertThat(resultForm.contains(to.get("email")));
        assertThat(resultForm.contains(to.get("phone")));

        assertThat(resultForm.contains(from.get("name")));
        assertThat(resultForm.contains(from.get("address")));
        assertThat(resultForm.contains(from.get("zip")));

        assertThat(resultForm.contains("Express Box - Small"));
        assertThat(resultForm.contains("8 lbs"));

        assertThat(resultForm.contains("Saturday Delivery"));

        assertThat(resultForm.contains("PayPal"));
    }

    @And("I cancel the shipment form")
    public void iCancelTheShipmentForm() {
        WebElement cancelButton = getDriver().findElement(By.xpath("//button[@id='nbsBackForwardNavigationCancelShipmentButton']"));
        getExecutor().executeScript("arguments[0].click();", cancelButton);
        getDriver().findElement(By.xpath("//button[@id='nbsCancelShipmentWarningYes']")).click();
    }

    @And("I open Shipping menu oop")
    public void iOpenShippingMenuOop() {
        upsPg.closeCookiesDialog();
        upsPg.clickShippingLink();
    }

    @And("I go to Create a Shipment oop")
    public void iGoToCreateAShipmentOop() {
        upsPg.goToShippingPage();
    }

    @When("I fill out origin shipment fields oop")
    public void iFillOutOriginShipmentFieldsOop() {
        Map<String, String> data = getData("from");
        upsShip.populateShippingField("originname", data.get("name"));
        upsShip.populateShippingField("originaddress1", data.get("address"));
        upsShip.populateShippingField("originpostal", data.get("zip"));

        upsShip.waitForElementValue("origincity", data.get("city"));
        //selection in dropdown
        upsShip.waitForStateSelection(data.get("state"));

        upsShip.populateShippingField("originemail", data.get("email"));
        upsShip.populateShippingField("originphone", data.get("phone"));
    }

    @And("I submit the shipment form oop")
    public void iSubmitTheShipmentFormOop() {
        upsShip.clickForwardButton();
    }

    @Then("I verify origin shipment fields submitted oop")
    public void iVerifyOriginShipmentFieldsSubmittedOop() {
        assertThat(upsShip.getPageHeaderText()).contains("Where is your shipment going");
    }

    @And("I cancel the shipment form oop")
    public void iCancelTheShipmentFormOop() {
    upsShip.clickCancelButton();

    }

    @Then("I verify shipment form is reset oop")
    public void iVerifyShipmentFormIsResetOop() {
        assertThat(upsShip.getPageHeaderText()).contains("Hello");
        assertThat(upsShip.getInputElementValue("originname")).contains("");
    }
}
