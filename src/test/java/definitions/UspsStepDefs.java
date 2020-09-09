package definitions;

import cucumber.api.java8.En;
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

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;
import static support.TestContext.*;

public class UspsStepDefs {

    @Given("I open {string} page")
    public void iOpenPage(String page) {
        switch (page.toLowerCase()) {
            case "google":
                getDriver().get("https://www.google.com/");
                break;
            case "quote":
                getDriver().get("https://skryabin.com/market/quote.html");
                break;
            case "usps":
                getDriver().get("https://www.usps.com/");
                break;
            case "converter":
                getDriver().get("https://www.unitconverters.net/");
                break;
            case "USPS calculate":
                getDriver().get("https://postcalc.usps.com/");
            default:
                System.out.println("Unsupported page: " + page);

        }
    }

    @When("I go to Lookup ZIP page by address")
    public void iGoToLookupZIPPageByAddress() throws InterruptedException {
        //getDriver().findElement(By.xpath("//a[@id='mail-ship-width']")).click();
        new Actions(getDriver()).moveToElement(getDriver().findElement(By.xpath("//a[@id='mail-ship-width']"))).perform();
        Thread.sleep(1000);
        getDriver().findElement(By.xpath("//li[@class='tool-zip']//a[contains(text(),'Look Up a ZIP Code')]")).click();
        getDriver().findElement(By.xpath("//a[@data-location = 'byaddress'][@class='zip-code-address zip-code-home']")).click();
        Thread.sleep(1000);
    }

    @And("I fill out {string} street, {string} city, {string} state")
    public void iFillOutStreetCityState(String street, String city, String state) {
        getDriver().findElement(By.xpath("//input[@id='tAddress']")).sendKeys(street);
        getDriver().findElement(By.xpath("//input[@id='tCity']")).sendKeys(city);
        getDriver().findElement(By.xpath("//select[@id='tState']")).click();
        String xPathState = "//select[@id='tState']//option[@value='" + state + "']";
        getDriver().findElement(By.xpath(xPathState)).click();
        getDriver().findElement(By.xpath("//a[@id='zip-by-address']")).click();
    }

    @Then("I validate {string} zip code exists in the result")
    public void iValidateZipCodeExistsInTheResult(String zip) {
        String zipCode = getDriver().findElement(By.xpath("//div[@id='zipByAddressDiv']")).getText();
        assertThat(zipCode).contains(zip);
    }

    @When("I go to Calculate Price Page")
    public void iGoToCalculatePricePage() {
        new Actions(getDriver()).moveToElement(getDriver().findElement(By.xpath("//a[@id='mail-ship-width']"))).perform();
        WebDriverWait wait = new WebDriverWait(getDriver(), 5);
        WebElement result = getDriver().findElement(By.xpath("//li[@class='menuheader']//a[@href='https://postcalc.usps.com/']"));
        wait.until(ExpectedConditions.elementToBeClickable(result));

        getDriver().findElement(By.xpath("//li[@class='menuheader']//a[@href='https://postcalc.usps.com/']")).click();
    }

    @And("I select {string} with {string} shape")
    public void iSelectWithShape(String country, String shape) {
        Select stateCountry = new Select(getDriver().findElement(By.xpath("//select[@id='CountryID']")));
        stateCountry.selectByVisibleText(country);
        getDriver().findElement(By.xpath("//input[@value='" + shape + "']")).click();

    }

    @And("I define {string} quantity")
    public void iDefineQuantity(String quantity) {
        getDriver().findElement(By.xpath("//input[contains(@id,'quantity')]")).sendKeys(quantity);
        getDriver().findElement(By.xpath("//input[@type='button']")).click();
    }

    @Then("I calculate the price and validate cost is {string}")
    public void iCalculateThePriceAndValidateCostIs(String amount) {
        String actualTotal = getDriver().findElement(By.xpath("//div[@id='total']")).getText();
        assertThat(actualTotal).contains(amount);
    }

    @When("I perform {string} search")
    public void iPerformSearch(String searchString) {
        getDriver().findElement(By.xpath("//a[contains(text(),'Search USPS.com')]")).click();
        getDriver().findElement(By.xpath("//input[@id='global-header--search-track-search']")).click();
        new Actions(getDriver()).moveToElement(getDriver().findElement(By.xpath("//input[@id='global-header--search-track-search']"))).perform();
        getDriver().findElement(By.xpath("//input[@id='global-header--search-track-search']")).sendKeys(searchString);
        new Actions(getDriver()).moveToElement(getDriver().findElement(By.xpath("//form[@action='https://www.usps.com/search/results.htm?PNO=1&keyword=']//input[@value='Search'][@type='submit'][@tabindex='-1']"))).perform();
        getDriver().findElement(By.xpath("//form[@action='https://www.usps.com/search/results.htm?PNO=1&keyword=']//input[@value='Search'][@type='submit'][@tabindex='-1']")).click();
    }

    @And("I set {string} in filters")
    public void iSetInFilters(String searchItem) {
        WebElement submenu = getDriver().findElement(By.xpath("//a[@class='dn-attr-a'][contains(text(),'" + searchItem + "')]"));
        //new Actions(getDriver()).moveToElement(submenu).click().perform();
        JavascriptExecutor executor = (JavascriptExecutor) getDriver();
        executor.executeScript("arguments[0].click();", submenu);
    }

    @Then("I verify that {string} results found")
    public void iVerifyThatResultsFound(String findsCount) {
        String actualDocuments = getDriver().findElement(By.xpath("//span[@id='searchResultsHeading']")).getText();
        System.out.println(actualDocuments);
        assertThat(actualDocuments).contains(findsCount);
    }

    @When("I select {string} in results")
    public void iSelectInResults(String item) throws InterruptedException {
         WebElement spinner = getDriver().findElement(By.xpath("//div[@class='white-spinner-container']"));
        getWait().until(invisibilityOf(spinner));
        WebDriverWait wait = new WebDriverWait(getDriver(), 6);
        WebElement result = getDriver().findElement(By.xpath("//span[contains(text(),'" +item+"')]"));
        wait.until(ExpectedConditions.elementToBeClickable(result));
        getActions().moveToElement(result).click().perform();
        //getDriver().findElement(By.xpath("//span[contains(text(),'" + item + "')]")).click();

    }

    @And("I click {string} button")
    public void iClickButton(String item) {
        WebDriverWait wait = new WebDriverWait(getDriver(), 5);
        WebElement result = getDriver().findElement(By.xpath("//a[contains(text(),'" + item + "')]"));
        wait.until(ExpectedConditions.elementToBeClickable(result));
        getDriver().findElement(By.xpath("//a[contains(text(),'" + item + "')]")).click();
    }

    @Then("I validate that Sign In is required")
    public void iValidateThatSignInIsRequired() throws InterruptedException {
//        WebDriverWait wait = new WebDriverWait(getDriver(), 5);
//        WebElement result = getDriver().findElement(By.xpath("//h1[@id='sign-in-to-your-account-header']"));
//        wait.until(ExpectedConditions.visibilityOf(result));
        Thread.sleep(6000);
        for (String winHandle : getDriver().getWindowHandles()) {
            getDriver().switchTo().window(winHandle);
        }
        Thread.sleep(1000);
        String actualDocuments = getDriver().findElement(By.xpath("//h1[contains(text(),'Sign In')]")).getText();
        System.out.println(actualDocuments);
        assertThat(actualDocuments).contains("Sign In");
    }

    @When("I go to {string} tab")
    public void iGoToTab(String item) {
        getDriver().findElement(By.xpath("//a[@class='menuitem'][contains(text(),'" + item + "')]")).click();
    }

    @And("I perform {string} help search")
    public void iPerformHelpSearch(String text) throws InterruptedException {
        getDriver().findElement(By.xpath("//input[@placeholder='Search for a topic']")).sendKeys(text);
        Thread.sleep(1000);
        getDriver().findElement(By.xpath("//div/span[@class='search-input-group']/button[@title='Search']")).click();
    }

    @Then("I verify that no results of {string} available in help search")
    public void iVerifyThatNoResultsOfAvailableInHelpSearch(String results) {
        String actualDocuments = getDriver().findElement(By.xpath("//div[@class='listContent']")).getText();
        System.out.println(actualDocuments);
        assertThat(actualDocuments).doesNotContain(results);

        //By result = By.xpath;
        //new WebDriverWait(getDriver(), 30).until(ExpectedConditions.visibilityOfElementLocated(result));
    }


    @When("I navigate to Find a Location page")
    public void iNavigateToFindALocationPage() {
        new Actions(getDriver()).moveToElement(getDriver().findElement(By.xpath("//a[@id='mail-ship-width']"))).perform();
        getDriver().findElement(By.xpath("//a[contains(text(),'Find a USPS Location')]")).click();
    }

    @And("I filter by {string} location types, {string} services, {string} available services")
    public void iFilterByLocationTypesServicesAvailableServices(String locationType, String services, String availableServices) {
//        Select location = new Select(getDriver().findElement(By.xpath("//select[@id='CountryID']")));
//        location.selectByVisibleText(locationType);
//        location.
        getDriver().findElement(By.xpath("//button[@id='post-offices-select']")).click();
        getDriver().findElement(By.xpath("(//button[@id='post-offices-select']/../ul//a[text()='" + locationType + "'])[2]")).click();
        getDriver().findElement(By.xpath("//button[@id='service-type-select']")).click();
        getDriver().findElement(By.xpath("//ul//a[contains(text(),'" + services + "')]")).click();
        getDriver().findElement(By.xpath("//button[@id='available-service-select']")).click();
        getDriver().findElement(By.xpath("//ul//a[contains(text(),'" + availableServices + "')]")).click();
    }

    @And("I provide data as {string} street, {string} city, {string} state")
    public void iProvideDataAsStreetCityState(String street, String city, String state) throws InterruptedException {
        getDriver().findElement(By.xpath("//input[@id='search-input']")).click();
        getDriver().findElement(By.xpath("//input[@id='addressLineOne']")).sendKeys(street);
        getDriver().findElement(By.xpath("//input[@id='cityOrZipCode']")).sendKeys(city);

        Select stateSelect = new Select(getDriver().findElement(By.xpath("//select[@id='servicesStateSelect']")));
        stateSelect.selectByValue(state);

        getDriver().findElement(By.xpath("//a[contains(text(), 'Go to Results')]")).click();
        Thread.sleep(3000);

    }


    @Then("I verify phone number is {string}")
    public void iVerifyPhoneNumberIs(String phone) {
        getDriver().findElement(By.xpath("(//span[@class='listArrow'])[1]")).click();
        String actualDocuments = getDriver().findElement(By.xpath("//div[contains (@class,'address-phone')]")).getText();
        System.out.println(actualDocuments);
        assertThat(actualDocuments).contains(phone);
    }

    @When("I go to {string} under {string}")
    public void iGoToUnder(String subMenu, String menu) throws InterruptedException {
        new Actions(getDriver()).moveToElement(getDriver().findElement(By.xpath("//a[@role='menuitem'][contains(text(),'" + menu + "')]"))).perform();
        new Actions(getDriver()).moveToElement(getDriver().findElement(By.xpath("//li/a[@role='menuitem'][text()='" + subMenu + "']"))).perform();
        getDriver().findElement(By.xpath("//li/a[@role='menuitem'][text()='" + subMenu + "']")).click();
//        Don't use contains when matching COMPLETE inner text!!!
    }

    @And("I search for {string}")
    public void iSearchFor(String address) {
        getDriver().findElement(By.xpath("//input[@id='address']")).clear();
        getDriver().findElement(By.xpath("//input[@id='address']")).sendKeys(address);
        getDriver().findElement(By.xpath("//button[contains(@class,'search-form-field-icon-search')]//span[@class='icon']")).click();
    }

    @And("I click {string} on the map")
    public void iClickOnTheMap(String mapPoint) {
        // explicit wait
        WebDriverWait wait = new WebDriverWait(getDriver(), 18);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[@class='order-summary-header']")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='route-table-toggle'][contains(text(),'" + mapPoint + "')]")));

        WebElement target = getDriver().findElement(By.xpath("//a[@class='route-table-toggle'][contains(text(),'" + mapPoint + "')]"));
        //new Actions(getDriver()).moveToElement(target).click(target).perform();
        JavascriptExecutor executor = (JavascriptExecutor) getDriver();
        executor.executeScript("arguments[0].click();", target);
    }

    @When("I click {string} on the table")
    public void iClickOnTheTable(String selection) {
        // explicit wait
        WebDriverWait wait = new WebDriverWait(getDriver(), 18);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='totalsArea'][contains(text(),'" + selection + "')]")));
        getDriver().findElement(By.xpath("//a[@class='totalsArea'][contains(text(),'" + selection + "')]")).click();
    }

    @And("I close modal window")
    public void iCloseModalWindow() throws InterruptedException {
        // explicit wait
        WebDriverWait wait = new WebDriverWait(getDriver(), 18);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(text(),'To complete your order')]")));
        getDriver().findElement(By.xpath("//div[@id='modal-box-closeModal']")).click();
        Thread.sleep(1000);
    }

    @Then("I verify that summary of all rows of Cost column is equal Approximate Cost in Order Summary")
    public void iVerifyThatSummaryOfAllRowsOfCostColumnIsEqualApproximateCostInOrderSummary() {
        double calculatedTotal = 0.00;
        String totalCountString = getDriver().findElement(By.xpath("//a[contains(@class, 'totalsArea')]")).getText();
        int totalCount = Integer.parseInt(totalCountString.replaceAll("\\D*", "")); //count of records

        List<WebElement> rows = getDriver().findElements(By.xpath("//td[@idx='7']"));
        // dealing with infinite scroll
        while (rows.size() < totalCount) {
            int lastIndex = rows.size() - 1;
            getActions().moveToElement(rows.get(lastIndex)).perform();
            rows = getDriver().findElements(By.xpath("//td[@idx='7']"));
        }
        System.out.println("Actual elements size: " + rows.size());
        for (WebElement item : rows) {
            calculatedTotal += Double.valueOf(item.getText().substring(1));
        }
//        Locale locale = new Locale("en", "US");
//        NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
//        double actualTotal = 0;
//        for (WebElement cost : costList) {
//            double costTotal = formatter.parse(cost.getText()).doubleValue();
//            actualTotal += costTotal;
        double total = Double.valueOf(getDriver().findElement(By.xpath("//span[@class='approx-cost']")).getText());
        assertThat(Math.round(calculatedTotal)).isEqualTo(Math.round(total));
        //assertThat(actualTotal).isCloseTo(expectedTotal, Percentage.withPercentage(1));
    }

    @And("I enter {string} into store search")
    public void iEnterIntoStoreSearch(String searchString) {
        getDriver().findElement(By.xpath("//input[@id='store-search']")).click();
        getDriver().findElement(By.xpath("//input[@id='store-search']")).sendKeys(searchString);

    }

    @Then("I search and validate no products found")
    public void iSearchAndValidateNoProductsFound() {
//        WebElement spinner = getDriver().findElement(By.xpath("//div[@class='white-spinner-container']"));
//        getWait().until(invisibilityOf(spinner));
        getDriver().findElement(By.xpath("//input[@id='store-search-btn']")).click();
        String resultForm = getDriver().findElement(By.xpath("//div[@class='no-results-found']//p")).getText();
        assertThat(resultForm).contains("search did not match any");
    }

    @And("choose mail service Priority Mail")
    public void chooseMailServicePriorityMail() {
        WebElement checkbox = getDriver().findElement(By.xpath("//h4[contains(text(),'Mail Service')]/..//label[contains(text(), 'Priority Mail (')]"));
        getWait(10).until(ExpectedConditions.visibilityOf(checkbox));
        getActions().moveToElement(checkbox).perform();
        checkbox.click();
    }

    @Then("I verify {int} items found")
    public void iVerifyItemsFound(int number) {
        String resultForm = getDriver().findElement(By.xpath("//h2[@class='col-md-3 application-header align-self-center results-per-page']")).getText();
        assertThat(resultForm).contains(number + " of " + number + " Results");
    }

    @When("I unselect Stamps checkbox")
    public void iUnselectStampsCheckbox() {
        getDriver().findElement(By.xpath("//h4[contains(text(),'Category')]/..//label[contains(text(), 'Stamps (')]")).click();
    }

    @And("select Vertical stamp Shape")
    public void selectVerticalStampShape() {
        getActions().moveToElement(getDriver().findElement(By.xpath("//h4[contains(text(),'Stamp Shape')]/..//label[contains(text(), 'Vertical (')]"))).perform();
        getDriver().findElement(By.xpath("//h4[contains(text(),'Stamp Shape')]/..//label[contains(text(), 'Vertical (')]")).click();
    }

    @And("I click Blue color")
    public void iClickBlueColor() {
        getActions().moveToElement(getDriver().findElement(By.xpath("//div[contains(@class, 'color')]//div[contains(@onclick, 'blue')]"))).perform();
        getDriver().findElement(By.xpath("//div[contains(@class, 'color')]//div[contains(@onclick, 'blue')]")).click();
    }

    @Then("I verify {string} and {string} filters")
    public void iVerifyAndFilters(String color, String shape) {
        String property1 = getDriver().findElement(By.xpath("//span[contains(text(),'Vertical')]")).getText();
        assertThat(property1).contains(shape);
        String property2 = getDriver().findElement(By.xpath("//div[@class='cartridge-viewport']//span[contains(text(),'Blue')]")).getText();
        assertThat(property2).contains(color);

    }

    @And("I verify that items below {double} dollars exists")
    public void iVerifyThatItemsBelowDollarsExists(double num) {
        List<WebElement> rows = getDriver().findElements(By.xpath("//div[@class='results-product-preview-price']/p"));
        double lowest = Double.MAX_VALUE;
        for (WebElement item : rows) {
            if (item.getText().length()>8){
                continue;
            }
            else if (lowest > Double.parseDouble(item.getText().replaceAll("[^\\d.]", ""))) {
                lowest = Double.parseDouble((item.getText().replaceAll("[^\\d.]", "")));
                System.out.println(lowest);
            }
        }
        assertThat(lowest).isLessThan(num);
    }

    @And("verify {string} service exists")
    public void verifyServiceExists(String str) {
        String passportType = getDriver().findElement(By.xpath("//select[@id='passportappointmentType']/option[text()='" +str+ "']")).getText();
        assertThat(passportType).isEqualTo(str);
    }

    @And("I reserve new PO box for {string}")
    public void iReserveNewPOBoxFor(String text) {
        WebElement zip = getDriver().findElement(By.xpath("//input[@id='searchInput']"));
        getWait().until(ExpectedConditions.visibilityOf(zip));
        zip.click();
        zip.clear();
        zip.sendKeys(text);
        WebElement searchBtn = getDriver().findElement(By.xpath("//span[@class='icon-search']"));
        new Actions(getDriver()).moveToElement(searchBtn).click().perform();
    }

    @Then("I verify that {string} present")
    public void iVerifyThatPresent(String postOffice) {
        System.out.println(postOffice.split(" ")[1]);
        WebElement officeLocation = getDriver().findElement(By.xpath("//div[@class='locations']//h2/span[contains(text(),'" +(postOffice.split(" ")[1])+ "')]"));
        getWait().until(ExpectedConditions.elementToBeClickable(officeLocation));
        officeLocation.click();
    }

    @And("I verify that {string} PO Box is available in {string}")
    public void iVerifyThatPOBoxIsAvailableIn(String size, String office) {
        String boxSize = getDriver().findElement(By.xpath("//label[@for='boxXL']")).getText();
        String location = getDriver().findElement(By.xpath("//div[@id='boxLocation']/h2")).getText();
        assertThat(location).isEqualTo(office);
        assertThat(boxSize).contains(size);
    }
}
