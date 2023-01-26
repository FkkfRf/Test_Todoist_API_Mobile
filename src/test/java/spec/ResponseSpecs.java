package spec;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.filter.log.LogDetail.*;
import static org.hamcrest.Matchers.notNullValue;

public class ResponseSpecs {
    public static ResponseSpecification successResponseSpec = new ResponseSpecBuilder()
            .log(STATUS)
            .expectStatusCode(200)
            .expectBody("id", notNullValue())
            .build();

    public static ResponseSpecification unSuccessResponseSpec = new ResponseSpecBuilder()
            .log(STATUS)
            .expectStatusCode(404)
            .build();

    public static ResponseSpecification noContentResponseSpec = new ResponseSpecBuilder()
            .log(STATUS)
            .expectStatusCode(204)
            .build();
}
