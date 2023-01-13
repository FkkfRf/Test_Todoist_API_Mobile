package tests;

import base.BaseTestAPI;
import models.lombok.CreateProject;
import models.lombok.LoginBody;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static spec.RequestSpecs.createRequestSpec;
import static spec.RequestSpecs.loginRequestSpec;
import static spec.ResponseSpecs.*;

public class TodoistTests extends BaseTestAPI {
    @Test
    @DisplayName("Add project")
    void addNewProjectTest() {
        CreateProject createProject = new CreateProject();
        final CreateProject[] projectData = new CreateProject[1];
        createProject.setName("Проект Э");

        step("Create project", () -> {
            projectData[0] = given()
                    .spec(createRequestSpec)
                    .body(createProject)
                    .when()
                    .post("/projects")
                    .then()
                    .spec(createSuccessResponseSpec)
                    .extract().as(CreateProject.class);
        });
        //step("Check project id in response", () ->
        //assertEquals(projectName, projectData[0].getName()));
    }
    @Test
    void loginSuccessTest() {

        LoginBody loginBody = new LoginBody();
        loginBody.setEmail(loginEmail);
        loginBody.setPassword(loginPassword);

        given()
                .spec(loginRequestSpec)
                .body(loginBody)
                .when()
                .post()
                .then()
                .spec(loginSuccessResponseSpec);
    }

    @Test
    void loginUnSuccessTest() {
        LoginBody loginBody = new LoginBody();
        loginBody.setEmail(loginEmail + " ");
        loginBody.setPassword(loginPassword + " ");

        given()
                .spec(loginRequestSpec)
                .body(loginBody)
                .when()
                .post()
                .then()
                .spec(loginUnSuccessResponseSpec);

    }


}

