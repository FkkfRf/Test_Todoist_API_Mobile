package spec;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.filter.log.LogDetail.BODY;
import static org.hamcrest.Matchers.notNullValue;

public class ResponseSpecs {

    public static ResponseSpecification createCaseSpec = new ResponseSpecBuilder()
            .log(BODY)
            .expectStatusCode(200)
            .expectBody("automated", notNullValue())
            .expectBody("external", notNullValue())
            .build();

    public static ResponseSpecification createStepSpec = new ResponseSpecBuilder()
            .log(BODY)
            .expectStatusCode(200)
            .build();

}
