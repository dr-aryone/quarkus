package io.quarkus.smallrye.openapi.test;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import io.quarkus.test.QuarkusUnitTest;
import io.restassured.RestAssured;

public class OpenApiDefaultPathTestCase {
    private static final String OPEN_API_PATH = "/openapi";

    @RegisterExtension
    static QuarkusUnitTest runner = new QuarkusUnitTest()
            .setArchiveProducer(() -> ShrinkWrap.create(JavaArchive.class)
                    .addClasses(OpenApiResource.class));

    @Test
    public void testOpenApiPathAccessResource() {
        RestAssured.given().queryParam("format", "application/yaml").when().get(OPEN_API_PATH).then().header("Content-Type",
                "application/yaml;charset=UTF-8");
        RestAssured.given().queryParam("format", "application/json").when().get(OPEN_API_PATH).then().header("Content-Type",
                "application/json;charset=UTF-8");
    }
}
