import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.io.IOException;
import java.util.Properties;

import static io.restassured.config.EncoderConfig.encoderConfig;

public class TestBase {
    public RequestSpecification REQUEST;

    public TestBase() {
        Properties props = new Properties();
        try {
            props.load(getClass().getClassLoader().getResourceAsStream("config.properties"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        RestAssured.baseURI = props.getProperty("api.uri");
        RestAssured.config = RestAssured.config().encoderConfig(encoderConfig().defaultContentCharset("UTF-8"));
        //basic request setting
        REQUEST = RestAssured.given().contentType(ContentType.URLENC).param("key", props.getProperty("api.key"));
    }
}