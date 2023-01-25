package page.mobile;

import com.codeborne.selenide.ElementsCollection;
import io.appium.java_client.AppiumBy;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$$;

public class AlarmPage {
    private ElementsCollection
            confirmButton = $$(AppiumBy.className("android.widget.Button"));

    public void clickAnswer(String answer) {
        confirmButton.findBy(text(answer));
    }

}
