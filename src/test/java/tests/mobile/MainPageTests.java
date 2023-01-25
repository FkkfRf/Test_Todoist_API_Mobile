package tests.mobile;

import helpers.DataForTests;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.back;
import static io.qameta.allure.Allure.step;
import static helpers.DataForTests.*;

@Tag("MOBILE")
public class MainPageTests extends BaseTest {

    @DisplayName("Добавить задачу")
    @Test
    void addTaskTest() {
        step("Авторизация", () -> {
            loginPage.loginByEmail(loginEmail, loginPassword);
            changeTimePage.selectChangeTime();
            mainPage.clickPlusButton();
        });
        step("Создать задачу", () -> {
            mainPage.inputTaskName(taskName, taskDescription);
            mainPage.checkAddTask(taskName, taskDescription);
        });
    }

    @DisplayName("Добавить проект")
    @Test
    void addProjectTest() {
        step("Авторизация", () -> {
            loginPage.loginByEmail(loginEmail, loginPassword);
            changeTimePage.selectChangeTime();
        });
        step(" Добавить новый проект", () -> {
            mainPage.clickMenuButton();
            projectPage.checkProjectPage("Projects");
            projectPage.clickAddProjectButon();
            projectPage.createProject(DataForTests.projectName);
            projectPage.checkNewProjectName(DataForTests.projectName);
            back();
        });
        step("Найти созданный проект в общем списке проектов", () -> {
            mainPage.clickMenuButton();
            projectPage.checkProjectPage(projectName);
        });
    }
}
