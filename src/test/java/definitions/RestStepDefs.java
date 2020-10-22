package definitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.io.FileUtils;
import support.RestClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static support.TestContext.*;

public class RestStepDefs {

    @Given("I login via REST as {string}")
    public void iLoginViaRESTAs(String role) {
        new RestClient().login(getData(role));
    }

    @When("I create via REST {string} position")
    public void iCreateViaRESTPosition(String type) {
        new RestClient().createPosition(getPosition(type));
    }

    @Then("I verify via REST new {string} position is in the list")
    public void iVerifyViaRESTNewPositionIsInTheList(String type) {
        List<Map<String, Object>> actualPositions = new RestClient().getPositions();
        Object expectedPositionId = getTestDataMap("newPosition").get("id");
        Map<String, String> expectedPosition = getPosition(type);

        boolean isFound = false;
        for (Map<String, Object> actualPosition : actualPositions) {
            if (actualPosition.get("id").equals(expectedPositionId)) {
                isFound = true;

                for(String key : expectedPosition.keySet()) {
                    System.out.println("Verifying " + key);
                    assertThat(actualPosition.get(key)).isEqualTo(expectedPosition.get(key));
                }
                break;
            }
        }
        assertThat(isFound).isTrue();
    }

    @When("I update via REST {string} position")
    public void iUpdateViaRESTPosition(String type) {
        Map<String, String> updatedPosition = getPosition(type + "_updated");
        Object id = getTestDataMap("newPosition").get("id");
        new RestClient().updatePosition(updatedPosition, id);
    }

    @Then("I verify via REST new {string} position is updated")
    public void iVerifyViaRESTNewPositionIsUpdated(String type) {
        Object expectedPositionId = getTestDataMap("newPosition").get("id");
        Map<String, Object> actualPosition = new RestClient().getPosition(expectedPositionId);
        Map<String, String> expectedFields = getPosition(type + "_updated");

        for (String key : expectedFields.keySet()) {
            System.out.println("Verifying " + key);
            assertThat(actualPosition.get(key)).isEqualTo(expectedFields.get(key));
        }
    }

    @When("I delete via REST new position")
    public void iDeleteViaRESTNewPosition() {
        Object expectedPositionId = getTestDataMap("newPosition").get("id");
        new RestClient().deletePositionById(expectedPositionId);
    }

    @Then("I verity via REST new position is deleted")
    public void iVerityViaRESTNewPositionIsDeleted() {
        Object deletedId = getTestDataMap("newPosition").get("id");
        List<Map<String, Object>> actualPositions = new RestClient().getPositions();

        for (Map<String, Object> position : actualPositions) {
            assertThat(position.get("id")).isNotEqualTo(deletedId);
        }
    }

    @When("I create via REST {string} candidate")
    public void iCreateViaRESTCandidate(String candidate_info) {
        new RestClient().createCandidate(getCandidate(candidate_info));
    }

    @When("I add via REST {string} resume to a new candidate")
    public void iAddViaRESTResumeToANewCandidate(String fileType) {
        File resume = getFile("resume", fileType);
        new RestClient().addResume(resume, getTestDataMap("newCandidate").get("id"));
    }

    @Then("I verify via REST that {string} resume has been added")
    public void iVerifyViaRESTThatResumeHasBeenAdded(String fileType) throws IOException {
        ExtractableResponse<Response> response = new RestClient().getResume(getTestDataMap("newCandidate").get("id"));
        String disposition = response.header("content-disposition");
        assertThat(disposition).isEqualTo("attachment; filename=resume." + fileType);

        byte[] byteArray = response.asByteArray();
        String signature = Hex.encodeHexString(byteArray);
        switch (fileType) {
            case "pdf":
                assertThat(signature).startsWith("255044462d");
                break;
            case "doc":
            case "xls":
            case "ppt":
                assertThat(signature).startsWith("D0CF11E0A1B11AE1");
                break;
        }
        saveFile("returnedResume", fileType, byteArray);
        File actualFile = getFile("resume", fileType);
        File expectedFile = getFile("returnedResume", fileType);
        boolean areEqual = FileUtils.contentEquals(actualFile, expectedFile);
        assertThat(areEqual).isTrue();
    }

    @When("I update via REST {string} candidate")
    public void iUpdateViaRESTCandidate(String candidate) {
        Map<String, String> updatedCandidate = getUpdatedCandidateData(candidate );
        Object id = getTestDataMap("newCandidate").get("id");
        new RestClient().updateCandidate(updatedCandidate, id);
    }

    @Then("I verify via REST new {string} candidate is updated")
    public void iVerifyViaRESTNewCandidateIsUpdated(String candidate_info) {
        Object expectedCandidateId = getTestDataMap("newCandidate").get("id");
        Map<String, Object> actualCandidate = new RestClient().getCandidate(expectedCandidateId);
        Map<String, String> expectedFields = getUpdatedCandidateData(candidate_info);
        for (String key : expectedFields.keySet()) {
            System.out.println("Verifying " + key);
            assertThat(actualCandidate.get(key)).isEqualTo(expectedFields.get(key));
        }
    }

    @When("I delete via REST new candidate")
    public void iDeleteViaRESTNewCandidate() {
        Object expectedPositionId = getTestDataMap("newCandidate").get("id");
        new RestClient().deleteCandidateById(expectedPositionId);
    }

    @Then("I verify via REST new candidate is deleted")
    public void iVerifyViaRESTNewCandidateIsDeleted() {
        Object deletedId = getTestDataMap("newCandidate").get("id");
        List<Map<String, Object>> actualPositions = new RestClient().getCandidates();

        for (Map<String, Object> position : actualPositions) {
            assertThat(position.get("id")).isNotEqualTo(deletedId);
        }
    }
}

