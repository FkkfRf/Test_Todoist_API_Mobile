package tests;

import base.BaseTestAPI;
import models.lombok.ProjectBody;
import models.lombok.ProjectResponseBody;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
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
        projectBody.setName("Проект Ю");

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
    public void updateNameOfLastProjectTest() {

        step("Получить имена всех проектов", () -> {
            //ProjectResponseBody response = new ProjectResponseBody();
            List<ProjectResponseBody> response = new ArrayList<>();
            ProjectResponseBody responsee = given()
                    .spec(getRequestSpec)
                    .when()
                    .get("/projects")
                    .then()
                    .spec(successResponseSpec)
                    .extract().as(ProjectResponseBody.class);


/* List<StepData> stepList = new ArrayList<>();
            for (int i = 0; i < stepList.size(); i++) {
                $$(".TreeElement__node").get(i).shouldHave(text(stepList.get(i).getName()));
            }
            List<String> expectedSteps = stepList.stream().map(StepData::getName).toList();
            */
        });
    }

}

