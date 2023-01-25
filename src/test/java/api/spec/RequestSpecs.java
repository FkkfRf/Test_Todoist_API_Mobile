package api.spec;

import config.ProjectConfig;
import io.restassured.specification.RequestSpecification;
import org.aeonbits.owner.ConfigFactory;

import static io.restassured.RestAssured.with;
import static io.restassured.http.ContentType.*;

public class RequestSpecs {

    static ProjectConfig config = ConfigFactory.create(ProjectConfig.class);
    static String token = config.token();
    static String baseUri = config.baseUri();


    public static RequestSpecification createRequestSpec = with()
            .header("Authorization", "Bearer "  + token )
            .header("X-Request-Id", "$(uuidgen)")
            .contentType(JSON)
            .baseUri(baseUri);

    public static RequestSpecification getRequestSpec = with()
    .header("Authorization", "Bearer " + token)
            .baseUri(baseUri)
            .contentType(JSON);


}
