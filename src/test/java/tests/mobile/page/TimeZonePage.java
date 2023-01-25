package tests.mobile.page;

import com.codeborne.selenide.ElementsCollection;
import io.appium.java_client.AppiumBy;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$$;

public class TimeZonePage {
    private ElementsCollection
            confirmButton = $$(AppiumBy.className("android.widget.Button"));

    @Step("Кликнуть кнопку установки времени TIME ZONE, NO TIME, OK")
    public void clickAnswer(String answer) {
        confirmButton.findBy(text(answer)).click();
    }
}
