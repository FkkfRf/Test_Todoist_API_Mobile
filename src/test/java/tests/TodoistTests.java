package tests;

import base.BaseTestAPI;
import models.lombok.LoginBody;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static spec.RequestSpecs.loginRequestSpec;
import static spec.ResponseSpecs.loginSuccessResponseSpec;
import static spec.ResponseSpecs.loginUnSuccessResponseSpec;

public class TodoistTests extends BaseTestAPI {

    @Test
    void loginSuccessTest() {

        LoginBody loginBody = new LoginBody();
        loginBody.setEmail(loginEmail);
        loginBody.setPassword(loginPassword);

        given()
                .spec(loginRequestSpec)
                .body(loginBody)
                .when()
                .post()
                .then()
                .spec(loginSuccessResponseSpec);
    }

    @Test
    void loginUnSuccessTest() {
        LoginBody loginBody = new LoginBody();
        loginBody.setEmail("111111@11.ru");
        loginBody.setPassword("11111111");

        given()
                .spec(loginRequestSpec)
                .body(loginBody)
                .when()
                .post()
                .then()
                .spec(loginUnSuccessResponseSpec);

    }

}


