package tests.api;

import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import models.lombok.ProjectBody;

import static io.restassured.RestAssured.given;

public class ApiMetods {
    ProjectBody projectBody = new ProjectBody();

    public ApiMetods postObject(RequestSpecification reqSpecification, ResponseSpecification resSpecification, String path, String name) {

        projectBody.setName(name);
        given()
                .spec(reqSpecification)
                .body(projectBody)
                .when()
                .post(path)
                .then()
                .spec(resSpecification)
                .extract().as(ProjectBody.class);
        return this;
    }

    public ApiMetods deleteObject(RequestSpecification reqSpecification, ResponseSpecification resSpecification, String path) {
        given()
                .spec(reqSpecification)
                .when()
                .delete(path)
                .then()
                .spec(resSpecification);
        return this;
    }
}
