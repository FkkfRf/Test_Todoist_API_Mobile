package spec;

import config.ProjectApiConfig;
import io.restassured.specification.RequestSpecification;
import org.aeonbits.owner.ConfigFactory;

import static helpers.CustomApiListener.withCustomTemplates;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.with;
import static io.restassured.http.ContentType.*;

public class RequestSpecs {
    static ProjectApiConfig config = ConfigFactory.create(ProjectApiConfig.class);
    static String token = config.token();
    static String baseUri = config.baseUri();


    public static RequestSpecification createRequestSpec = with()
            .log().all()
            .header("Authorization", "Bearer 0123456789abcdef0123456789")
            .header("X-Request-Id", "$(uuidgen)")
            .contentType(JSON)
            .baseUri(baseUri);

    public static RequestSpecification loginRequestSpec = with()
            .filter(withCustomTemplates())
            .basePath("/API/v9.0/user/login")
            .log().body()
            .log().uri()
            .contentType(JSON);

}
