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
@Owner("FkkfRf")
@Link(url = "https://todoist.com/")
public class ProjectsTests extends BaseTest {
    ApiMetods apiMetods = new ApiMetods();

    @DisplayName("Добавить проект (REST API)")
    @Test
    void addNewProjectTest() {
        ApiMetods projectData = apiMetods
                .successPostObject("/projects", DataForTests.projectName);
        step("Проверить наличие созданного проекта в response", () ->
                assertEquals(DataForTests.projectName, projectData.projectBody.getName()));
    }

    @DisplayName("Изменить имя последнего созданного проекта. Позитивный. (REST API)")
    @Test
    public void sucessUpdateNameOfLastCreateProjectTest() {

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

                ApiMetods projectData = apiMetods
                        .successPostObject("/projects/" + projectId, DataForTests.projectName + " 1");
                step("Проверить имя проекта ", () ->
                        Assertions.assertEquals(DataForTests.projectName + " 1", projectData.projectBody.getName()));

        }

    @DisplayName("Изменить имя последнего созданного проекта. Негативный (REST API)")
    @Test
    public void unSucessUpdateNameOfLastCreateProjectTest() {

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

                step("Изменить имя последнего созданного проекта (проектов нет) ", () ->
                        given()
                                .spec(RequestSpecs.createRequestSpec)
                                .body(projectBody)
                                .when()
                                .post("/projects/" + projectId)
                                .then()
                                .spec(ResponseSpecs.unSuccessResponseSpec));

    }

    @DisplayName("Удалить последний созданный проект (REST API)")
    @Test
    public void successDeleteLastCreateProjectTest() {
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

                step("Удалить последний созданный проект", () -> {
                    given()
                            .spec(RequestSpecs.createRequestSpec)
                            .when()
                            .delete("/projects/" + projectId)
                            .then()
                            .spec(ResponseSpecs.noContentResponseSpec);
                });
    }

}


