package tests.api;

import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.Tag;
import models.lombok.ProjectBody;
import models.lombok.TaskBody;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicReference;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static models.spec.RequestSpecs.createRequestSpec;
import static models.spec.RequestSpecs.getRequestSpec;
import static models.spec.ResponseSpecs.noContentResponseSpec;
import static models.spec.ResponseSpecs.successResponseSpec;
import static helpers.DataForTests.*;

public class TasksOfProjectsTests extends BaseTest {
    private String projectId;
    private String taskId;

    @Tag("API")
    @DisplayName("Добавить задачу в новый проект (REST API)")
    @Owner("FkkfRf")
    @Link(url = "https://todoist.com/")
    @Test
    void addNewTaskForLastCreateProjectTest() {
        ProjectBody projectBody = new ProjectBody();
        TaskBody taskBody = new TaskBody();
        AtomicReference<TaskBody> taskData = new AtomicReference<TaskBody>();

        step("Создать проект", () -> {
            projectBody.setName(projectName + " 2");
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
            taskBody.setContent(taskName);
            taskBody.setDescription(taskDescription);
            taskBody.setProject_id(projectId);
            TaskBody taskDataValue = given()
                    .spec(createRequestSpec)
                    .body(taskBody)
                    .when()
                    .post("/tasks")
                    .then()
                    .spec(successResponseSpec)
                    .extract().as(TaskBody.class);

            taskData.set(taskDataValue);
        });
        step("Проверить имя задачи", () ->
                assertEquals(taskName, taskData.get().getContent()));
        step("Проверить id проекта", () ->
                assertEquals(projectId, taskData.get().getProject_id()));
    }

    @Tag("API")
    @DisplayName("Удалить задачу в новомм проекте (REST API)")
    @Owner("FkkfRf")
    @Link(url = "https://todoist.com/")
    @Test
    void deleteFromNewProjectTest() {
        ProjectBody projectBody = new ProjectBody();
        TaskBody taskBody = new TaskBody();
        AtomicReference<TaskBody> taskData = new AtomicReference<TaskBody>();

        step("Создать проект", () -> {
            projectBody.setName(projectName + " 3");
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
            taskBody.setContent(taskName);
            taskBody.setProject_id(projectId);
            TaskBody taskDataValue = given()
                    .spec(createRequestSpec)
                    .body(taskBody)
                    .when()
                    .post("/tasks")
                    .then()
                    .spec(successResponseSpec)
                    .extract().as(TaskBody.class);

            taskData.set(taskDataValue);
            taskId = taskData.get().getId();
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

