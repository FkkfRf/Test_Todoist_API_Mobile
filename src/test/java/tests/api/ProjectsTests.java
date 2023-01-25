package tests.api;

import spec.ResponseSpecs;
import helpers.DataForTests;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import models.lombok.ProjectBody;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import spec.RequestSpecs;

import java.util.*;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Tag("API")
public class ProjectsTests extends BaseTest {
    @DisplayName("Добавить проект (REST API)")
    @Owner("FkkfRf")
    @Link(url = "https://todoist.com/")
    @Test
    void addNewProjectTest() {
        ProjectBody projectBody = new ProjectBody();
        projectBody.setName(DataForTests.projectName);

        ProjectBody projectData = step("Создать проект", () ->
                given()
                        .spec(RequestSpecs.createRequestSpec)
                        .body(projectBody)
                        .when()
                        .post("/projects")
                        .then()
                        .spec(ResponseSpecs.successResponseSpec)
                        .extract().as(ProjectBody.class));

        step("Проверить наличие созданного проекта в response", () ->
                assertEquals(DataForTests.projectName, projectData.getName()));
    }

    @DisplayName("Изменить имя последнего созданного проекта (REST API)")
    @Owner("FkkfRf")
    @Link(url = "https://todoist.com/")
    @Test
    public void updateNameOfLastCreateProjectTest() {

        ProjectBody projectBody = new ProjectBody();
        projectBody.setName(DataForTests.projectName + "1");

        List<String> idList = step("Получить список id созданных проектов", () -> RestAssured.given()
                .spec(RequestSpecs.getRequestSpec)
                .when()
                .get("/projects")
                .then()
                .spec(ResponseSpecs.successResponseSpec)
                .body("id", notNullValue())
                .extract().jsonPath().getList("id"));
        System.out.println(idList);

        String projectId = step("Получить Id последнего проекта", () -> Collections.max(idList));

        switch (idList.size()) {
            case 1: {
                step("Изменить имя последнего созданного проекта (проектов нет) ", () ->
                        given()
                                .spec(RequestSpecs.createRequestSpec)
                                .body(projectBody)
                                .when()
                                .post("/projects/" + projectId)
                                .then()
                                .spec(ResponseSpecs.unSuccessResponseSpec));
                break;
            }
            default: {
                projectBody.setName(DataForTests.projectName + " 1");
                ProjectBody projectData = step("Изменить имя последнего созданного проекта (проекты созданы)", () ->
                        given()
                                .spec(RequestSpecs.createRequestSpec)
                                .body(projectBody)
                                .when()
                                .post("/projects/" + projectId)
                                .then()
                                .spec(ResponseSpecs.successResponseSpec)
                                .extract().as(ProjectBody.class));

                step("Проверить имя проекта в response", () ->
                        Assertions.assertEquals(DataForTests.projectName + " 1", projectData.getName()));
                break;
            }
        }
    }

    @DisplayName("Удалить последний созданный проект (REST API)")
    @Owner("FkkfRf")
    @Link(url = "https://todoist.com/")
    @Test
    public void deleteLastCreateProjectTest() {
        List<String> idList = step("Получить список id созданных проектов", () ->
                given()
                        .spec(RequestSpecs.getRequestSpec)
                        .when()
                        .get("/projects")
                        .then()
                        .spec(ResponseSpecs.successResponseSpec)
                        .body("id", Matchers.notNullValue())
                        .extract().jsonPath().getList("id"));
        System.out.println(idList);

        String projectId = step("Получить Id последнего проекта", () -> Collections.max(idList));

        switch (idList.size()) {
            case 1:
                step("Удалить последний созданный проект (проектов нет) ", () -> {
                    given()
                            .spec(RequestSpecs.createRequestSpec)
                            .when()
                            .delete("/projects/" + projectId)
                            .then()
                            .spec(ResponseSpecs.noContentResponseSpec);
                });
                break;
            default:
                step("Удалить последний созданный проект (проекты созданы)", () -> {
                    given()
                            .spec(RequestSpecs.createRequestSpec)
                            .when()
                            .delete("/projects/" + projectId)
                            .then()
                            .spec(ResponseSpecs.noContentResponseSpec);
                });
                break;
        }
    }
}


