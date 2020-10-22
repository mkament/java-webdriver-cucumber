package support;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.File;
import java.util.List;
import java.util.Map;

import static support.TestContext.*;

public class RestClient {

    private String baseUrl = "https://skryabin.com/recruit/api/v1/";
    private static String loginToken;
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String JSON = "application/json";
    public static final String AUTH = "Authorization";

    public void login(Map<String, String> user) {
        // prepare
        RequestSpecification request = RestAssured.given()
                .log().all()
                .baseUri(baseUrl)
                .basePath("login")
                .header(CONTENT_TYPE, JSON)
                .body(user);

        // execute
        Response response = request.when()
                .post();

        // verify and extract data
        Map<String, Object> result = response.then()
                .log().all()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getMap("");

        loginToken = "Bearer " + result.get("token");
        System.out.println(loginToken);

    }

    public Map<String, Object> createPosition(Map<String, String> position) {
        // prepare
        RequestSpecification request = RestAssured.given()
                .log().all()
                .baseUri(baseUrl)
                .basePath("positions")
                .header(CONTENT_TYPE, JSON)
                .header(AUTH, loginToken)
                .body(position);

        // execute
        Response response = request.when()
                .post();

        // verify and extract
        Map<String, Object> result = response.then()
                .log().all()
                .statusCode(201)
                .extract()
                .jsonPath()
                .getMap("");

        setTestData("newPosition", result);

        return result;
    }

    public List<Map<String, Object>> getPositions() {
        return RestAssured.given()
                .log().all()
                .baseUri(baseUrl)
                .basePath("positions")
                .when()
                .get()
                .then()// returns String, Object map likely ?
                .log().all()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getList("");
//                RequestSpecification request = RestAssured.given().log().all().baseUri(baseUrl).basePath("positions");
//                Response response = request.when().get();
//                List<Map<String, Object>> result = response.then().log().all().statusCode(200).extract().jsonPath().getList("");
    }

    public Map<String, Object> updatePosition(Map<String, String> fields, Object id) {
        return RestAssured.given()
                .log().all()
                .baseUri(baseUrl)
                .basePath("positions/" + id)
                .header(CONTENT_TYPE, JSON)
                .header(AUTH, loginToken)
                .body(fields)
                .when()
                .patch()
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getMap("");
    }

    public Map<String, Object> getPosition(Object id) {
        return RestAssured.given()
                .log().all()
                .baseUri(baseUrl)
                .basePath("positions/" + id)
                .when()
                .get()
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getMap("");
    }

    public void deletePositionById(Object id) {
        RestAssured.given()
                .log().all()
                .baseUri(baseUrl)
                .basePath("positions/" + id)
                .header(AUTH, loginToken)
                .when()
                .delete()
                .then()
                .log().all()
                .statusCode(204);
    }

    public Map<String, Object> createCandidate(Map<String, String> position) {
        // prepare
        RequestSpecification request = RestAssured.given()
                .log().all()
                .baseUri(baseUrl)
                .basePath("candidates")
                .header(CONTENT_TYPE, JSON)
                .header(AUTH, loginToken)
                .body(position);

        // execute
        Response response = request.when()
                .post();

        // verify and extract
         Map<String, Object>result = response.then()
                .log().all()
                .statusCode(201)
                .extract()
                .jsonPath()
                .getMap("");

        setTestData("newCandidate", result);

        return result;
    }

    public void addResume(File resume, Object candidateId) {
        RestAssured.given()
                .log().all()
                .baseUri(baseUrl)
                .basePath("candidates/" + candidateId + "/resume")
                .header(AUTH, loginToken)
                .multiPart("resume", resume)
                .when()
                .post()
                .then()
                .log().all()
                .statusCode(201);
    }

    public ExtractableResponse<Response> getResume(Object candidateId) {
        return RestAssured.given()
                .log().all()
                .baseUri(baseUrl)
                .basePath("candidates/" + candidateId + "/resume")
                .header(AUTH, loginToken)
                .when()
                .get()
                .then()
                .log().all()
                .statusCode(200)
                .extract();
    }
    public Map<String, Object> updateCandidate(Map<String, String> fields, Object id) {
        return RestAssured.given()
                .log().all()
                .baseUri(baseUrl)
                .basePath("candidates/" + id)
                .header(CONTENT_TYPE, JSON)
                .header(AUTH, loginToken)
                .body(fields)
                .when()
                .patch()
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getMap("");
    }

    public Map<String, Object> getCandidate(Object id) {
        return RestAssured.given()
                .log().all()
                .baseUri(baseUrl)
                .basePath("candidates/" + id)
                .when()
                .get()
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getMap("");
    }
    public void deleteCandidateById(Object id) {
        RestAssured.given()
                .log().all()
                .baseUri(baseUrl)
                .basePath("candidates/" + id)
                .header(AUTH, loginToken)
                .when()
                .delete()
                .then()
                .log().all()
                .statusCode(204);
    }

    public List<Map<String, Object>> getCandidates() {
        return RestAssured.given()
                .log().all()
                .baseUri(baseUrl)
                .basePath("candidates")
                .when()
                .get()
                .then()// returns String, Object map likely ?
                .log().all()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getList("");
    }
}