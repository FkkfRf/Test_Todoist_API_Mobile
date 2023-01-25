package tests.mobile.page;

import com.codeborne.selenide.SelenideElement;
import io.appium.java_client.AppiumBy;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class ChangeTimePage {

    private SelenideElement
            title = $(AppiumBy.id("com.todoist:id/alertTitle")),
            neverAskButton = $(AppiumBy.id("android:id/button3"));

    @Step ("Закрыть окно установки часового пояса")
    public void selectChangeTime() {
        title.shouldHave(text("Change your Todoist time zone to “GMT”"));
        neverAskButton.click();
    }
}