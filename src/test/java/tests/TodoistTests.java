package tests;

import base.BaseTest;
import models.lombok.LoginBody;
import models.lombok.LoginUnSuccessResponse;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static spec.RequestSpecs.loginRequestSpec;
import static spec.ResponseSpecs.loginSuccessResponseSpec;
import static spec.ResponseSpecs.loginUnSuccessResponseSpec;

public class TodoistTests extends BaseTest {

    @Test
    void loginTest(){

      LoginBody loginBody = new LoginBody();
      loginBody.setEmail("vil_la@bk.ru");
      loginBody.setPassword("Vfcmrf23");

      given()
              .spec(loginRequestSpec)
              .body(loginBody)
              .when()
              .post()
              .then()
              .spec(loginSuccessResponseSpec);



     // assertThat(response.getData().get(0).getHttp_code()).isEqualTo(401);


    }

}
