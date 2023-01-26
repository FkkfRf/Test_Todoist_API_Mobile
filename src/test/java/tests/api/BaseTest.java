package tests.api;

import helpers.CustomApiListener;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class BaseTest {
    @BeforeAll
    static void setUp() {
        RestAssured.filters(CustomApiListener.withCustomTemplates());
    }
}
