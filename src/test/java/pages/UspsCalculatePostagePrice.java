package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import support.TestContext;

import static org.assertj.core.api.Assertions.assertThat;
import static support.TestContext.getDriver;
import static support.TestContext.getWait;

public class UspsCalculatePostagePrice extends UspsHeader{
    @FindBy(xpath="//select[@id='CountryID']")
    private WebElement countrySelect;

    @FindBy(xpath = "//input[contains(@id,'quantity')]")
    private WebElement quantityField;

    @FindBy(xpath = "//input[@type='button']")
    private WebElement calculateBtn;

    @FindBy(xpath = "//div[@id='total']")
    private WebElement actualTotal;

// dynamic fields
    private WebElement shape (String shape) {
        return getDriver().findElement(By.xpath("//input[@value='" + shape + "']"));}

    private WebElement countrySelection (String country) {
        return getDriver().findElement(By.xpath("//select[@id='CountryID']/option[(text()='" + country + "')]"));}

    public void verifyTotal(String price) {
        String total = actualTotal.getText();
        assertThat(total).contains(price);
    }

    public void fillQuantity(String value) {
        quantityField.sendKeys(value);
    }

    public void clickCalculate() {
        calculateBtn.click();
    }

    public void selectCountry(String value) {
        //TestContext.getWait(10).until(ExpectedConditions.elementToBeClickable(country));
        Select stateCountry = new Select(countrySelect);
        //getWait(5).until(ExpectedConditions.elementSelectionStateToBe(countrySelection(value), true));
        stateCountry.selectByVisibleText(value);
    }

    public void clickShape (String shapeString) {
        shape(shapeString).click();
    }
}
