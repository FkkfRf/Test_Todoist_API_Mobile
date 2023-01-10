package spec;

import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.with;
import static io.restassured.http.ContentType.JSON;

public class RequestSpecs {

    public static RequestSpecification requestSpec = with()
            .log().all()
            .contentType(JSON)
            .queryParam("projectId", "1771");
 }
