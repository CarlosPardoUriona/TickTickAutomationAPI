package testSuite;

import io.restassured.response.Response;
import org.json.JSONObject;

import utils.GetProperties;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class TestBase {
    String tokenName = "Token";
    String tokenValue = null;
    public String getToken(){
        JSONObject body= new JSONObject();
       // body.put("Content","BootcampTest");

        Response response=given()
                .auth()
                .preemptive()
                .basic("karlitos.p@gmail.com","123456")
                .body(body.toString())
                .log().all()
                .when()
                .get("https://todo.ly/api/authentication/token.json");

        response.then()
                .log().all()
                .statusCode(200);
              //  .body("Content",equalTo("BootcampTest"));

       return tokenValue = response.then().extract().path("TokenString");
    }


}
