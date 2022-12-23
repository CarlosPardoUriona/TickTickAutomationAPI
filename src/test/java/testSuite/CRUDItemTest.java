package testSuite;

import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import session.Session;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CRUDItemTest extends TestBase{

    /*
    *  given() --> configuration: headers/ params / auth / body
    *  when() --> method : url
    *  then() --> response: headers/ body / response code / msg code / etc
    *             verification
    *             extract
    *  log
    * */
    @Test
    public void verifyCRUDItem(){
        String tokenValue = getToken();
        JSONObject body= new JSONObject();
        body.put("Content","BootcampTest");

        Response response=given()
                .headers(tokenName,tokenValue)
                .body(body.toString())
                .log().all()
        .when()
                .post("https://todo.ly/api/items.json");

        response.then()
                .log().all()
                .statusCode(200)
                .body("Content",equalTo("BootcampTest"));

        int idItem = response.then().extract().path("Id");

        body.put("Content","BootcampTestUpdate");
        response=given()
                    .headers(tokenName,tokenValue)
                    .body(body.toString())
                    .log().all()
                .when()
                    .put("https://todo.ly/api/items/"+idItem+".json");

        response.then()
                .log().all()
                .statusCode(200)
                .body("Deleted",equalTo(false))
                .body("Content",equalTo("BootcampTestUpdate"));


        response=given()
                    .headers(tokenName,tokenValue)
                    .body(body.toString())
                    .log().all()
                .when()
                    .delete("https://todo.ly/api/items/"+idItem+".json");

        response.then()
                    .log().all()
                    .statusCode(200)
                    .body("Deleted",equalTo(true))
                    .body("Content",equalTo("BootcampTestUpdate"));

    }

}
