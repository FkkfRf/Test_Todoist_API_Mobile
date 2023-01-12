package tests;

import base.BaseTest;
import models.lombok.LoginBody;
import models.lombok.LoginSuccessResponse;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

import static spec.RequestSpecs.loginRequestSpec;
import static spec.ResponseSpecs.loginSuccessResponseSpec;
import static spec.ResponseSpecs.loginUnSuccessResponseSpec;

public class TodoistTests extends BaseTest {
    private static Integer SUCCESS_STATUS_CODE = 200;
    private static Integer UNSUCCESS_STATUS_CODE = 401;
    @Test
    void loginSuccessTest() {

        LoginBody loginBody = new LoginBody();
        loginBody.setEmail("vil_la@bk.ru");
        loginBody.setPassword("Vfcmrf23");

       // LoginSuccessResponse response =
        given()
                .spec(loginRequestSpec)
                .body(loginBody)
                .when()
                .post()
                .then()
                .spec(loginSuccessResponseSpec);
        //open("/favicon.ico");
    }

    @Test
    void loginUnSuccessTest() {
        LoginBody loginBody = new LoginBody();
        loginBody.setEmail("vil_la@bk.ru");
        loginBody.setPassword("11111111");

        given()
                .spec(loginRequestSpec)
                .body(loginBody)
                .when()
                .post()
                .then()
                .spec(loginUnSuccessResponseSpec);
        //open("/favicon.ico");
        //sleep(30000);

    }

}


