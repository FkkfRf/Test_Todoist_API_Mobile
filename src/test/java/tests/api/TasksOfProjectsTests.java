package tests.api;

import helpers.DataForTests;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.Tag;
import models.lombok.ProjectBody;
import models.lombok.TaskBody;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static spec.RequestSpecs.createRequestSpec;
import static spec.ResponseSpecs.noContentResponseSpec;
import static spec.ResponseSpecs.successResponseSpec;

@Tag("API")
@Owner("FkkfRf")
@Link(url = "https://todoist.com/")
public class TasksOfProjectsTests extends BaseTest {
    ApiMetods apiMetods = new ApiMetods();

    @DisplayName("Добавить задачу в новый проект (REST API)")
    @Test
    void addNewTaskForLastCreateProjectTest() {
        ProjectBody projectBody = new ProjectBody();
        TaskBody taskBody = new TaskBody();

        projectBody.setName(DataForTests.projectName + " 2");

        String projectId = step("Создать проект", () ->
                String.valueOf(given()
                        .spec(createRequestSpec)
                        .body(projectBody)
                        .when()
                        .post("/projects")
                        .then()
                        .spec(successResponseSpec)
                        .extract().jsonPath().getLong("id")));

        taskBody.setContent(DataForTests.taskName);
        taskBody.setDescription(DataForTests.taskDescription);
        taskBody.setProject_id(projectId);
        TaskBody taskData = step("Добавить задачу в созданный проект (REST API)", () ->
                given()
                        .spec(createRequestSpec)
                        .body(taskBody)
                        .when()
                        .post("/tasks")
                        .then()
                        .spec(successResponseSpec)
                        .extract().as(TaskBody.class));
        step("Проверить имя задачи", () ->
                assertEquals(DataForTests.taskName, taskData.getContent()));
        step("Проверить id проекта", () ->
                assertEquals(projectId, taskData.getProject_id()));
    }

    @DisplayName("Удалить задачу в новом проекте (REST API)")
    @Test
    void deleteFromNewProjectTest() {
        ProjectBody projectBody = new ProjectBody();
        TaskBody taskBody = new TaskBody();

        projectBody.setName(DataForTests.projectName + " 3");
        String projectId = step("Создать проект", () ->
                String.valueOf(given()
                        .spec(createRequestSpec)
                        .body(projectBody)
                        .when()
                        .post("/projects")
                        .then()
                        .spec(successResponseSpec)
                        .extract().jsonPath().getLong("id")));

        taskBody.setContent(DataForTests.taskName);
        taskBody.setProject_id(projectId);
        TaskBody taskData = step("Добавить задачу в созданный проект (REST API)", () ->
                given()
                        .spec(createRequestSpec)
                        .body(taskBody)
                        .when()
                        .post("/tasks")
                        .then()
                        .spec(successResponseSpec)
                        .extract().as(TaskBody.class));

        String taskId = taskData.getId();

        step("Удалить задачу в последнем созданном проекте ", () ->
                apiMetods.
                        deleteObject(createRequestSpec, noContentResponseSpec, "/tasks/" + taskId));
    }
}

