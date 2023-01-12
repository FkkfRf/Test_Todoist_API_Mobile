package base;

import config.LoginCreate;
import config.ProjectProvider;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;


import static helpers.CustomApiListener.withCustomTemplates;
import static io.qameta.allure.Allure.step;

public class BaseTestAPI {

    public static String loginEmail, loginPassword;

    @BeforeAll
    static void setUp() {
        step("Устанавливаем базовый URI для REST API", () -> {
            ProjectProvider.configBase();
            RestAssured.filters(withCustomTemplates());
        });
        step("Задаём параметры для входа", () -> {
            loginEmail = LoginCreate.config.loginEmail();
            loginPassword = LoginCreate.config.loginPassword();
        });
    }
}

