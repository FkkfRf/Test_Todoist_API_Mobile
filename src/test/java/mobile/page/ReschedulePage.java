package mobile.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.appium.java_client.AppiumBy;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ReschedulePage {
    ElementsCollection schedulerButton = $$(AppiumBy.id("android.widget.Button"));
    private SelenideElement
            schedulerTimeButton = $(AppiumBy.id("com.todoist:id/scheduler_time")),
            rescheduleButton = $(AppiumBy.id("com.todoist:id/scheduler_submit"));

    @Step("Кликнуть кнопку ADD TIME")
    public void clickAddTimeButton() {
        schedulerTimeButton.click();
    }

    @Step("Проверить установленное время")
    public void checkSchedulerTime(String time) {
        schedulerButton.findBy(text(time)).exists();
    }

    @Step("Кликнуть кнопку RESHEDULE")
    public void clickRescheduleButton() {
        rescheduleButton.click();
    }

}
