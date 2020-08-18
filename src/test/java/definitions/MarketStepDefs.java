package definitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import support.TestContext;

import static support.TestContext.getDriver;

public class MarketStepDefs {
    @Given("I go to {string} page")
    public void iGoToPage(String page) {
        if (page.equals("quote")) {
            getDriver().get("https://skryabin.com/market/quote.html");
        } else if (page.equals("google")) {
            getDriver().get("https://www.google.com/");
        } else if (page.equals("yahoo")) {
            getDriver().get("https://www.yahoo.com/");
        } else {
            throw new RuntimeException("Unsupported page! " + page);
        }

    }

    @And("I print page details")
    public void iPrintPageDetails() {
        System.out.println(getDriver().getTitle());
        System.out.println(getDriver().getCurrentUrl());
        System.out.println(getDriver().getWindowHandles());
        System.out.println(getDriver().getPageSource());
    }

    @And("I go back and forward, then refresh")
    public void iGoBackAndForwardThenRefresh() {
        getDriver().navigate().back();
        getDriver().navigate().forward();
        getDriver().navigate().refresh();

    }

    @When("I fill out required fields")
    public void iFillOutRequiredFields() {
        getDriver().findElement(By.xpath("//input[@name='username']")).sendKeys("Zond");
        getDriver().findElement(By.xpath("//input[@name='email']")).sendKeys("dmv@onebox.com");
        getDriver().findElement(By.xpath("//input[@id='password']")).sendKeys("ZPT1234~");
        getDriver().findElement(By.xpath("//input[@id='confirmPassword']")).sendKeys("ZPT1234~");
        getDriver().findElement(By.xpath("//input[@id='name']")).sendKeys("King Kong");
        getDriver().findElement(By.xpath("//input[@name='phone']")).sendKeys("+13433243488459");
        getDriver().findElement(By.xpath("//input[@id='dateOfBirth']")).sendKeys("01/01/1901");
        getDriver().findElement(By.xpath("//select[@name='countryOfOrigin']")).click();
        getDriver().findElement(By.xpath("//option[contains(text(),'Belarus')]")).click();
        getDriver().findElement(By.xpath("//input[@name='allowedToContact']")).click();
        getDriver().findElement(By.xpath("//label[@class='checkbox_radio']/input[@value='male']")).click();
        getDriver().findElement(By.xpath("//input[@name='allowedToContact']")).click();
        getDriver().findElement(By.xpath("//textarea[@id='address']")).sendKeys("55 Mueller Drive, San Juan, CA 99321");
        getDriver().findElement(By.xpath("//option[contains(text(),'Toyota')]")).click();
        getDriver().findElement(By.xpath("//button[@id='thirdPartyButton']")).click();
        System.out.println("ALERT TEXT FOLLOWS: ");
        System.out.println(getDriver().switchTo().alert().getText());
        getDriver().switchTo().alert().accept();
        getDriver().findElement(By.xpath("//input[@name='agreedToPrivacyPolicy'][@type='checkbox']")).click();
    }

    @And("I submit the form")
    public void iSubmitTheForm() {
        getDriver().findElement(By.xpath("//button[@id='formSubmit']")).click();
    }

    @Then("I verify required fields")
    public void iVerifyRequiredFields() {
        String username = getDriver().findElement(By.xpath("//b[@name='username']")).getText();
        System.out.println("VERIFICATION, USERNAME TEXT: " + username);

    }

    @And("I change resolution to {string}")
    public void iChangeResolutionTo(String resolution) throws InterruptedException {
        Dimension size = new Dimension(400, 768);
        if (resolution.equals("phone")) {
            getDriver().manage().window().setSize(size);
        } else if (resolution.equals("desktop")) {
            size = new Dimension(400, 768);
            getDriver().manage().window().setSize(size);
        } else {
            throw new RuntimeException("Unsupported page! " + resolution);
        }
        Thread.sleep(3000);

    }

    @When("I verify email field behavior")
    public void iVerifyEmailFieldBehavior() throws InterruptedException {
        // Within the step, clear the field, enter in email field wrong email format,
        // then delete just one character, then clear element contents, then enter
        // email with correct format
        getDriver().findElement(By.xpath("//input[@name='email']")).clear();
        getDriver().findElement(By.xpath("//input[@name='email']")).sendKeys("dmvonebox.com");
        getDriver().findElement(By.xpath("//input[@name='email']")).sendKeys(Keys.BACK_SPACE);
        Thread.sleep(3000);
        getDriver().findElement(By.xpath("//input[@name='email']")).clear();
        getDriver().findElement(By.xpath("//input[@name='email']")).sendKeys("dmv@onebox.com");
    }
}