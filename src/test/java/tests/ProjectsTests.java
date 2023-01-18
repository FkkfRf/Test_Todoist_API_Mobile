package tests;

import base.BaseTest;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import models.lombok.ProjectBody;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static spec.RequestSpecs.createRequestSpec;
import static spec.RequestSpecs.getRequestSpec;
import static spec.ResponseSpecs.*;

public class ProjectsTests extends BaseTest {
    private List<String> idList = new ArrayList<>();

    @DisplayName("Добавить проект (REST API)")
    @Owner("FkkfRf")
    @Link(url = "https://todoist.com/")
    @Test
    void addNewProjectTest() {
        ProjectBody projectBody = new ProjectBody();

        final ProjectBody[] projectData = {new ProjectBody()};
        projectBody.setName("Проект F");

        step("Создать проект", () -> {
            projectData[0] = given()
                    .spec(createRequestSpec)
                    .body(projectBody)
                    .when()
                    .post("/projects")
                    .then()
                    .spec(successResponseSpec)
                    .extract().as(ProjectBody.class);
        });
        step("Проверить наличие созданного проекта в response", () ->
                assertEquals("Проект F", projectData[0].getName()));
    }

    @Test
    @DisplayName("Изменить имя последнего созданного проекта (REST API)")
    public void updateNameOfLastCreateProjectTest() {
        ProjectBody projectBody = new ProjectBody();
        final ProjectBody[] projectData = {new ProjectBody()};

        step("Получить список id созданных проектов", () -> {
            idList = given()
                    .spec(getRequestSpec)
                    .when()
                    .get("/projects")
                    .then()
                    .spec(successResponseSpec)
                    .body("id", notNullValue())
                    .extract().jsonPath().getList("id");
            System.out.println(idList);
        });

        String max = Collections.max(idList);
        switch (idList.size()) {
            case 1:
                step("Изменить имя последнего созданного проекта (проектов нет) ", () -> {
                    projectBody.setName("Изменённый проект");
                    given()
                            .spec(createRequestSpec)
                            .body(projectBody)
                            .when()
                            .post("/projects/" + max)
                            .then()
                            .spec(unSuccessResponseSpec);
                });
                break;

            default:
                step("Изменить имя последнего созданного проекта (проекты созданы)", () -> {
                    projectBody.setName("Изменённый проект");
                    projectData[0] = given()
                            .spec(createRequestSpec)
                            .body(projectBody)
                            .when()
                            .post("/projects/" + max)
                            .then()
                            .spec(successResponseSpec)
                            .extract().as(ProjectBody.class);
                });
                step("Проверить имя проекта в response", () ->
                        assertEquals("Изменённый проект", projectData[0].getName()));
                break;
        }
    }

    @Test
    @DisplayName("Удалить последний созданный проект (REST API)")
    public void deleteLastCreateProjectTest() {
        step("Получить список id созданных проектов", () -> {
            idList = given()
                    .spec(getRequestSpec)
                    .when()
                    .get("/projects")
                    .then()
                    .spec(successResponseSpec)
                    .body("id", notNullValue())
                    .extract().jsonPath().getList("id");
            System.out.println(idList);
        });

        String max = Collections.max(idList);
        switch (idList.size()) {
            case 1:
                step("Удалить последний созданный проект (проектов нет) ", () -> {
                    given()
                            .spec(createRequestSpec)
                            .when()
                            .delete("/projects/" + max)
                            .then()
                            .spec(noContentResponseSpec);
                });
                break;
            default:
                step("Удалить последний созданный проект (проекты созданы)", () -> {
                    given()
                            .spec(createRequestSpec)
                            .when()
                            .delete("/projects/" + max)
                            .then()
                            .spec(noContentResponseSpec);
                });
                break;
        }
    }
}


