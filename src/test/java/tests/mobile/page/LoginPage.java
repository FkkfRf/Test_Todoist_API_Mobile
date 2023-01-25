package tests.mobile.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.appium.java_client.AppiumBy;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class LoginPage {
    private ElementsCollection
            editTextWiget = $$(AppiumBy.className("android.widget.EditText"));
    private final SelenideElement
            pageContinueOptions = $(AppiumBy.id("com.todoist:id/more_signin_options")),
            selectLoginByEmail = $(AppiumBy.id("com.todoist:id/email_login")),
            emailInput = $(AppiumBy.className("android.widget.EditText")),
            showPasswodButton = $(AppiumBy.className("android.widget.ImageView")),
            passwordInput = $$(AppiumBy.className("android.widget.EditText")).last(),
            loginButton = $$(AppiumBy.className("android.widget.TextView")).findBy(text("Log in"));

    @Step("Log in по Email")
    public void loginByEmail(String email, String password) {
        pageContinueOptions.click();
        selectLoginByEmail.click();
        editTextWiget.first().click();
        emailInput.sendKeys(email);
        showPasswodButton.click();
        editTextWiget.last().click();
        passwordInput.sendKeys(password);
        loginButton.click();
    }
}
