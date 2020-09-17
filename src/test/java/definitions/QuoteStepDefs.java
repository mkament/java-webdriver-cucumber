package definitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.QuoteForm;
import pages.QuoteResult;
import pages.UPSHome;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static support.TestContext.getData;

public class QuoteStepDefs {

    QuoteForm form = new QuoteForm();
    QuoteResult result = new QuoteResult();
    UPSHome upsPg = new UPSHome();

    @Given("I open {string} page oop")
    public void iOpenPage(String page) throws InterruptedException {
        switch (page) {
            case "quote":
                form.open();
                break;
            case "ups":
                upsPg.open();
                break;
            default:
                throw new RuntimeException("Unknown page: " + page);
        }
    }

    @When("I fill out required fields for {string} oop")
    public void iFillOutRequiredFieldsForOop(String role) {
        Map<String, String> user = getData(role);
        form.fillUsername(user.get("username"));
        form.fillEmail(user.get("email"));
        form.fillBothPasswords(user.get("password"));
        form.fillName(user.get("firstName"), user.get("lastName"));
        form.agreeWithPrivacyPolicy();
    }

    @And("I submit the form oop")
    public void iSubmitTheFormOop() {
        form.submit();
    }

    @Then("I verify required fields for {string} oop")
    public void iVerifyRequiredFieldsForOop(String role) {
        Map<String, String> user = getData(role);
        assertThat(result.getUsername()).isEqualTo(user.get("username"));
        assertThat(result.getName()).isEqualTo(user.get("firstName") + " " + user.get("lastName"));
        assertThat(result.getPassword()).isEqualTo("[entered]");
        assertThat(result.getEmail()).isEqualTo(user.get("email"));
        //assertThat(result.getAgreedToPrivacyPolicy().equals("true")).isTrue();
        assertThat(result.isAgreedToPrivacyPolicy()).isTrue();
    }

    @When("I fill out optional fields for {string} oop")
    public void iFillOutOptionalFieldsForOop(String role) {
        Map<String, String> user = getData(role);
        form.fillCountry(user.get("countryOfOrigin"));
        form.fillPhone(user.get("phone"));
        form.fillDateOfBirth(user.get("dateOfBirth"));
        form.clickAllowedToContact();
        form.clickGender(user.get("gender"));
        form.fillAddress(user.get("address"));
        form.clickThirdPartyButton();
        form.fillContactInfo(user.get("contactPersonName"), user.get("contactPersonPhone"));

    }

    @When("I fill out name field with first name {string} and last name {string}")
    public void iFillOutNameFieldWithFirstNameAndLastName(String first, String last) {
        form.fillName(first, last);
    }

    @Then("I verify {string} field value {string}")
    public void iVerifyFieldValue(String field, String name) {
        assertThat(form.getElementText(field)).isEqualTo(name);
    }

    @When("I fill out name field with first name {string}, middle name {string}, last name {string}")
    public void iFillOutNameFieldWithFirstNameMiddleNameLastName(String first, String middle, String last) {
        form.fillName(first, middle, last);
    }

    @Then("I verify optional fields for {string} oop")
    public void iVerifyOptionalFieldsForOop(String role) {
        Map<String, String> user = getData(role);
        assertThat(result.getPhone()).isEqualTo(user.get("phone"));
        assertThat(result.getAddress()).isEqualTo(user.get("address"));
        //assertThat(result.getLocation()).contains(user.get("zip"));
        assertThat(result.getDateOfBirth()).isEqualTo(user.get("dateOfBirth"));
        assertThat(result.getCountry()).isEqualTo(user.get("country"));
        assertThat(result.getGender()).isEqualTo(user.get("gender"));
        assertThat(result.getAllowedToContact().equals("true"));
        assertThat(result.getCar().equals(user.get("car")));
        assertThat(result.getThirdPartyAgreement().equals("accepted"));
        assertThat(result.getResultText()).contains(user.get("contactPersonName"));
        assertThat(result.getResultText()).contains(user.get("contactPersonPhone"));
    }

    @Then("I see {string} error message {string}")
    public void iSeeErrorMessage(String field, String error) {
        String actualError = form.getErrorFieldText(field);
        assertThat(actualError).isEqualTo(error);
//        switch (field) {
//            case "username":
//                assertThat(form.getUsernameErrorValue()).isEqualTo(error);
//                break;
//            case "email":
//                assertThat(form.getEmailErrorValue()).isEqualTo(error);
//                break;
//            case "password":
//                assertThat(form.getPasswordErrorValue()).isEqualTo(error);
//                break;
//            case "confirmPassword":
//                assertThat(form.getPasswordConfirmErrorValue()).isEqualTo(error);
//                break;
//            case "name":
//                assertThat(form.getNameErrorValue()).isEqualTo(error);
//                break;
//            case "agreedToPrivacyPolicy":
//                assertThat(form.getAgreedToPrivacyPolicyErrorValue()).isEqualTo(error);
//                break;
//            default:
//                throw new RuntimeException("Unknown field: " + field + " for expected error: " + error);
//        }
    }

    @When("I fill out {string} field with {string}")
    public void iFillOutFieldWith(String field, String input) {
        switch (field) {
            case "username":
                form.fillUsername(input);
                break;
            case "email":
                form.fillEmail(input);
                break;
            case "password":
                form.fillPassword(input);
                break;
            case "confirmPassword":
                form.fillPasswordConfirm(input);
                break;
            default:
                throw new RuntimeException("Unknown field: " + field + " for expected input: " + input);

        }
    }

    @Then("I don't see {string} error message")
    public void iDonTSeeErrorMessage(String field) {
        switch (field) {
            case "password":
                assertThat(form.getPasswordErrorValue()).isEqualTo("");
                break;
            case "confirmPassword":
                assertThat(form.getPasswordErrorValue()).isEqualTo("");
                break;
            case "username":
                assertThat(form.getUsernameErrorValue()).isEqualTo("");
                break;
            case "email":
                assertThat(form.getEmailErrorValue()).isEqualTo("");
                break;
            default:
                throw new RuntimeException("Unable to find field: " + field );
    }
}
}