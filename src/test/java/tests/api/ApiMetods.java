package tests.api;

import io.qameta.allure.Step;
import models.lombok.ProjectBody;
import spec.RequestSpecs;
import spec.ResponseSpecs;

import static io.restassured.RestAssured.given;

public class ApiMetods {
    ProjectBody projectBody = new ProjectBody();

    @Step("Создать проект ")
    public ApiMetods successPostObject(String path, String name) {

        projectBody.setName(name);
        given()
                .spec(RequestSpecs.createRequestSpec)
                .body(projectBody)
                .when()
                .post(path)
                .then()
                .spec(ResponseSpecs.successResponseSpec)
                .extract().as(ProjectBody.class);
        return this;
    }
}
