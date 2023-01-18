package tests;

import base.BaseTest;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import models.lombok.ProjectBody;
import models.lombok.TaskBody;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static spec.RequestSpecs.createRequestSpec;
import static spec.ResponseSpecs.successResponseSpec;

public class TasksOfProjectsTests extends BaseTest {
    private String projectId;

    @DisplayName("Добавить задачу в новый проект (REST API)")
    @Owner("FkkfRf")
    @Link(url = "https://todoist.com/")
    @Test
    void addNewTaskForLastCreateProjectTest() {
        ProjectBody projectBody = new ProjectBody();
        TaskBody taskBody = new TaskBody();
        final TaskBody[] taskData = {new TaskBody()};

        step("Создать проект", () -> {
            projectBody.setName("Проект K");
            projectId = String.valueOf(given()
                    .spec(createRequestSpec)
                    .body(projectBody)
                    .when()
                    .post("/projects")
                    .then()
                    .spec(successResponseSpec)
                    .extract().jsonPath().getLong("id"));
        });

        step("Добавить задачу в последний созданный проект (REST API)", () -> {
            taskBody.setContent("Новая задача");
            taskBody.setDescription("Описание тестовой задачи");
            taskBody.setProject_id(projectId);
            taskData[0] = given()
                    .spec(createRequestSpec)
                    .body(taskBody)
                    .when()
                    .post("/tasks")
                    .then()
                    .spec(successResponseSpec)
                    .extract().as(TaskBody.class);
        });
        step("Проверить имя задачи", () ->
                assertEquals("Новая задача", taskData[0].getContent()));
        step("Проверить id проекта", () ->
                assertEquals(projectId, taskData[0].getProject_id()));
    }
}
