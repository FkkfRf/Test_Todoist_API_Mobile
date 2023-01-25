package tests.api;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

import static helpers.CustomApiListener.withCustomTemplates;
import static io.qameta.allure.Allure.step;

public class BaseTest {
    @BeforeAll
    static void setUp() {
        Allure.step("Устанавливаем базовый URI для REST API", () -> {
            RestAssured.filters(CustomApiListener.withCustomTemplates());
        });
    }
}
