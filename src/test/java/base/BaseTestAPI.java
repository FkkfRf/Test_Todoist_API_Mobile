package base;

import config.ProjectApiConfig;
import config.ProjectProvider;
import io.restassured.RestAssured;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeAll;

import static helpers.CustomApiListener.withCustomTemplates;
import static io.qameta.allure.Allure.step;

public class BaseTestAPI {

    @BeforeAll
    static void setUp() {
        step("Устанавливаем базовый URI для REST API", () -> {
            RestAssured.filters(withCustomTemplates());
        });
    }
}

