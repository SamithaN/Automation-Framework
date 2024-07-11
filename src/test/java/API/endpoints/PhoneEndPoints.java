package API.endpoints;

import API.payload.Phone;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class PhoneEndPoints {

    public static Response createPhone(String name, int year, double price, String CPUmodel, String harddiskSize) {
        String requestBody = String.format(
                "{\"name\":\"%s\",\"data\":{\"year\":%d,\"price\":%.2f,\"CPUmodel\":\"%s\",\"harddiskSize\":\"%s\"}}",
                name, year, price, CPUmodel, harddiskSize
        );

        Response response = given()
                .contentType(ContentType.JSON)  // Set Content-Type header to JSON
                .accept(ContentType.JSON)       // Set Accept header to JSON
                .body(requestBody)
                .when()
                .post(Routes.post_url);

        return response;
    }

    public static Response getPhone() {

        Response response = given()
                .contentType(ContentType.JSON)  // Set Content-Type header to JSON
                .accept(ContentType.JSON)       // Set Accept header to JSON
                .when()
                .get(Routes.get_url);

        return response;
    }

    public static Response updatePhone(Phone payload, String id) {

        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("id",id)
                .body(payload)
                .when()
                .put(Routes.put_url);

        return response;
    }

    public static Response deletePhone(String id) {

        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("id",id)
                .when()
                .delete(Routes.delete_url);

        return response;
    }

//    public static Response createBook(String author, int title)
//    {
//        String requestBody = String.format("{ \"author\": %s, \"title\": \"%d\" }", author, title);
//
//        Response response = given()
//                .contentType(ContentType.JSON)
//                .auth().basic("admin", "password")
//                .accept(ContentType.JSON)
//                .body(requestBody)
//                .when()
//                .post(Routes.post_url);
//
//        return response;
//    }
//
//    public static Response updateBook(int id, Book payload)
//    {
//        Response response = given()
//                .contentType(ContentType.JSON)
//                .auth().basic("admin", "password")
//                .accept(ContentType.JSON)
//                .pathParam("id",id)
//                .body(payload)
//                .when()
//                .put(Routes.put_url);
//
//        return response;
//    }

}
