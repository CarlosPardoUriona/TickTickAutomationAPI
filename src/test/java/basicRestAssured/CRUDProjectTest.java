package basicRestAssured;

import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CRUDProjectTest {

    /*
    *  given() --> configuration: headers/ params / auth / body
    *  when() --> method : url
    *  then() --> response: headers/ body / response code / msg code / etc
    *             verification
    *             extract
    *  log
    * */
    @Test
    public void verifyCRUDProject(){
        JSONObject body= new JSONObject();
        body.put("Content","BootcampTest");

        Response response=given()
                .headers("Token","398cb7d4c7374d0cbf5d7f44966278ef")
               // .auth()
               // .none()
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
        //body.put("Icon",5);
        response=given()
                    .headers("Token","398cb7d4c7374d0cbf5d7f44966278ef")
                    //.auth()
                    //.preemptive()
                    //.oauth2("7b649147847b4a069edbed4e3c6f7ac8")
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
                    .headers("Token","398cb7d4c7374d0cbf5d7f44966278ef")
                    //.auth()
                   // .preemptive()
                    //.oauth2("7b649147847b4a069edbed4e3c6f7ac8")
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
