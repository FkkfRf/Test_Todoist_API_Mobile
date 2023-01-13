package spec;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.filter.log.LogDetail.*;
import static org.hamcrest.Matchers.notNullValue;

public class ResponseSpecs {
    public static ResponseSpecification createSuccessResponseSpec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .log(BODY)
            .build();
    public static ResponseSpecification loginSuccessResponseSpec = new ResponseSpecBuilder()
            .log(STATUS)
            .expectStatusCode(200)
            .build();

    public static ResponseSpecification loginUnSuccessResponseSpec = new ResponseSpecBuilder()
            .log(ALL)
            .expectStatusCode(401)
            .build();

    public static ResponseSpecification createUnSuccessResponseSpec = new ResponseSpecBuilder()
            .log(STATUS)
            .log(BODY)
            .expectStatusCode(400)
            .build();

    public static ResponseSpecification deleteResponseSpec = new ResponseSpecBuilder()
            .log(STATUS)
            .log(BODY)
            .expectStatusCode(204)
            .build();
}
