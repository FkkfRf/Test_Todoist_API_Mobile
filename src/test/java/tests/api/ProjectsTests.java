package tests.api;

import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import models.lombok.ProjectBody;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

import static helpers.DataForTests.projectName;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static spec.RequestSpecs.createRequestSpec;
import static spec.RequestSpecs.getRequestSpec;
import static spec.ResponseSpecs.*;

@Tag("API")
@Owner("FkkfRf")
@Link(url = "https://todoist.com/")
public class ProjectsTests extends BaseTest {
    ApiMetods apiMetods = new ApiMetods();

    @DisplayName("Добавить проект (REST API)")
    @Test
    void addNewProjectTest() {
        ApiMetods projectData = step("Создать проект ", () ->
                apiMetods
                        .postObject(createRequestSpec, successResponseSpec, "/projects", projectName));
        step("Проверить наличие созданного проекта", () ->
                assertEquals(projectName, projectData.projectBody.getName()));
    }

    @DisplayName("Изменить имя последнего созданного проекта. Позитивный. (REST API)")
    @Test
    public void successUpdateNameOfLastCreateProjectTest() {

        ProjectBody projectBody = new ProjectBody();
        projectBody.setName(projectName + "1");

        List<String> idList = step("Получить список id созданных проектов", () -> RestAssured.given()
                .spec(getRequestSpec)
                .when()
                .get("/projects")
                .then()
                .spec(successResponseSpec)
                .body("id", notNullValue())
                .extract().jsonPath().getList("id"));
        System.out.println(idList);

        String projectId = step("Получить Id последнего проекта", () -> Collections.max(idList));

        ApiMetods projectData = step("Изменить имя последнего созданного проекта", () ->
                apiMetods
                        .postObject(createRequestSpec, successResponseSpec, "/projects/" + projectId, projectName + " 1"));
        step("Проверить имя проекта ", () ->
                Assertions.assertEquals(projectName + " 1", projectData.projectBody.getName()));
    }

    @DisplayName("Изменить имя последнего созданного проекта. Негативный (REST API)")
    @Test
    public void unSuccessUpdateNameOfLastCreateProjectTest() {

        ProjectBody projectBody = new ProjectBody();
        projectBody.setName(projectName + "1");

        List<String> idList = step("Получить список id созданных проектов", () ->
                given()
                        .spec(getRequestSpec)
                        .when()
                        .get("/projects")
                        .then()
                        .spec(successResponseSpec)
                        .body("id", notNullValue())
                        .extract().jsonPath().getList("id"));
        System.out.println(idList);
        String projectId = step("Получить Id последнего проекта", () -> Collections.max(idList));

        step("Изменить имя последнего созданного проекта (проектов нет)", () ->
                apiMetods
                        .postObject(createRequestSpec, unSuccessResponseSpec, "/projects/" + projectId, projectName + " 1"));
    }

    @DisplayName("Удалить последний созданный проект (REST API)")
    @Test
    public void successDeleteLastCreateProjectTest() {
        List<String> idList = step("Получить список id созданных проектов", () ->
                given()
                        .spec(getRequestSpec)
                        .when()
                        .get("/projects")
                        .then()
                        .spec(successResponseSpec)
                        .body("id", Matchers.notNullValue())
                        .extract().jsonPath().getList("id"));
        System.out.println(idList);

        String projectId = step("Получить Id последнего проекта", () -> Collections.max(idList));
        step("Удалить последний созданный проект ", () ->
                apiMetods.
                        deleteObject(createRequestSpec, noContentResponseSpec, "/projects/" + projectId));
    }
}


