package api;

import base.BaseTest;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import api.models.lombok.ProjectBody;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static api.spec.RequestSpecs.createRequestSpec;
import static api.spec.RequestSpecs.getRequestSpec;
import static api.spec.ResponseSpecs.*;
import static testsdata.DataForTests.projectName;

public class ProjectsTests extends BaseTest {
    private List<String> idList = new ArrayList<>();
    private String projectId;

    @DisplayName("Добавить проект (REST API)")
    @Owner("FkkfRf")
    @Link(url = "https://todoist.com/")
    @Test
    void addNewProjectTest() {
        ProjectBody projectBody = new ProjectBody();
        AtomicReference<ProjectBody> projectData = new AtomicReference<ProjectBody>();

        step("Создать проект", () -> {
            projectBody.setName(projectName);
            ProjectBody projectDataValue = given()
                    .spec(createRequestSpec)
                    .body(projectBody)
                    .when()
                    .post("/projects")
                    .then()
                    .spec(successResponseSpec)
                    .extract().as(ProjectBody.class);

            projectData.set(projectDataValue);
        });
        step("Проверить наличие созданного проекта в response", () ->
                assertEquals(projectName, projectData.get().getName()));
    }

    @DisplayName("Изменить имя последнего созданного проекта (REST API)")
    @Owner("FkkfRf")
    @Link(url = "https://todoist.com/")
    @Test
    public void updateNameOfLastCreateProjectTest() {
        ProjectBody projectBody = new ProjectBody();
        AtomicReference<ProjectBody> projectData = new AtomicReference<ProjectBody>();

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

        step("Получить Id последнего проекта", () ->
                projectId = Collections.max(idList));

        switch (idList.size()) {
            case 1:
                step("Изменить имя последнего созданного проекта (проектов нет) ", () -> {
                    projectBody.setName(projectName + "1");
                    given()
                            .spec(createRequestSpec)
                            .body(projectBody)
                            .when()
                            .post("/projects/" + projectId)
                            .then()
                            .spec(unSuccessResponseSpec);
                });
                break;

            default:
                step("Изменить имя последнего созданного проекта (проекты созданы)", () -> {
                    projectBody.setName(projectName + " 1");
                    ProjectBody projectDataValue = given()
                            .spec(createRequestSpec)
                            .body(projectBody)
                            .when()
                            .post("/projects/" + projectId)
                            .then()
                            .spec(successResponseSpec)
                            .extract().as(ProjectBody.class);

                    projectData.set(projectDataValue);
                });
                step("Проверить имя проекта в response", () ->
                        assertEquals(projectName + " 1", projectData.get().getName()));
                break;
        }
    }

    @DisplayName("Удалить последний созданный проект (REST API)")
    @Owner("FkkfRf")
    @Link(url = "https://todoist.com/")
    @Test
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

        step("Получить Id последнего проекта", () ->
                projectId = Collections.max(idList));

        switch (idList.size()) {
            case 1:
                step("Удалить последний созданный проект (проектов нет) ", () -> {
                    given()
                            .spec(createRequestSpec)
                            .when()
                            .delete("/projects/" + projectId)
                            .then()
                            .spec(noContentResponseSpec);
                });
                break;
            default:
                step("Удалить последний созданный проект (проекты созданы)", () -> {
                    given()
                            .spec(createRequestSpec)
                            .when()
                            .delete("/projects/" + projectId)
                            .then()
                            .spec(noContentResponseSpec);
                });
                break;
        }
    }
}


