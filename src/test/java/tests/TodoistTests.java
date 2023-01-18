package tests;

import base.BaseTestAPI;
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

public class TodoistTests extends BaseTestAPI {

    @Test
    @DisplayName("Добавить проект")
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
                assertEquals("Проект Ю", projectData[0].getName()));
    }

    @Test
    @DisplayName("Изменить имя последнего созданного проекта")
    public void updateNameOfLastCreateProjectTest() {

        step("Получить список id созданных проектов", () -> {
            List<Long> idList = new ArrayList<>();
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

        step("Получить name последнего созданного проектf", () -> {
            ProjectBody projectBody = new ProjectBody();
            projectBody.setId(Long.valueOf(Collections.max(idList)));
            String name;

            name = given()
                    .spec(getRequestSpec)
                    .body(projectBody)
                    .when()
                    .get("/projects")
                    .then()
                    .spec(successResponseSpec)
                    .body("id", notNullValue())
                    .extract().jsonPath().getString("name");

            System.out.println(name);

        });
    }

}

