package tests;

import base.BaseTest;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import models.lombok.ProjectBody;
import models.lombok.TaskBody;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static spec.RequestSpecs.createRequestSpec;
import static spec.RequestSpecs.getRequestSpec;
import static spec.ResponseSpecs.noContentResponseSpec;
import static spec.ResponseSpecs.successResponseSpec;

public class TasksOfProjectsTests extends BaseTest {
    private String projectId;
    private String taskId;
    private List<String> idList = new ArrayList<>();

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

        step("Добавить задачу в созданный проект (REST API)", () -> {
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

    @DisplayName("Удалить задачу в новомм проекте (REST API)")
    @Owner("FkkfRf")
    @Link(url = "https://todoist.com/")
    @Test
    void deleteFromNewProjectTest() {
        ProjectBody projectBody = new ProjectBody();
        TaskBody taskBody = new TaskBody();
        final TaskBody[] taskData = {new TaskBody()};

        step("Создать проект", () -> {
            projectBody.setName("Проект T");
            projectId = String.valueOf(given()
                    .spec(createRequestSpec)
                    .body(projectBody)
                    .when()
                    .post("/projects")
                    .then()
                    .spec(successResponseSpec)
                    .extract().jsonPath().getLong("id"));
        });

        step("Добавить задачу в созданный проект (REST API)", () -> {
            taskBody.setContent("Задача T");
            taskBody.setProject_id(projectId);
            taskData[0] = given()
                    .spec(createRequestSpec)
                    .body(taskBody)
                    .when()
                    .post("/tasks")
                    .then()
                    .spec(successResponseSpec)
                    .extract().as(TaskBody.class);
            taskId = taskData[0].getId();
        });


        step("Удалить задачу в последнем созданном проект (REST API)", () -> {
            given()
                    .spec(getRequestSpec)
                    .when()
                    .delete("/tasks/" + taskId)
                    .then()
                    .spec(noContentResponseSpec);
        });

    }
}

