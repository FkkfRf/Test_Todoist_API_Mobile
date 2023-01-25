package page.mobile;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.appium.java_client.AppiumBy;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ProjectPage {
    private ElementsCollection
            projectTexts = $$(AppiumBy.className("android.widget.TextView"));

    private SelenideElement
            addProjectButton = $(AppiumBy.id("com.todoist:id/add")),
            projectName = $(AppiumBy.id("com.todoist:id/name")),
            submitButton = $(AppiumBy.id("com.todoist:id/menu_form_submit")),
            profileButton = $(AppiumBy.id("com.todoist:id/profile_settings"));

    @Step("Проверить, что попали на экран проектов")
    public void checkProjectPage(String name) {
        projectTexts.findBy(Condition.text(name)).exists();
    }

    public void clickProfileButton() {
        profileButton.click();
    }

    @Step("Кликнуть по кнопке добавления проектов")
    public void clickAddProjectButon() {
        addProjectButton.click();
    }
    @Step("Создать проект с именем")
    public void createProject(String name) {
        projectName.sendKeys(name);
        submitButton.click();
    }
    @Step("Проверить наличие проекта с именем")
    public void checkNewProjectName(String name) {
        projectTexts.findBy(Condition.text(name)).exists();
    }
}
