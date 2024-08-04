import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class TestRegisterAPI {
    public static void main(String[] args) {
        RestAssured.baseURI = "https://parabank.parasoft.com/parabank";

        // Create the request and capture the response
        Response response = given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("customer.firstName", "ismail")
                .formParam("customer.lastName", "saleh")
                .formParam("customer.address.street", "4 Maadi Street")
                .formParam("customer.address.city", "Cairo")
                .formParam("customer.address.state", "CA")
                .formParam("customer.address.zipCode", "12345")
                .formParam("customer.phoneNumber", "1151279456")
                .formParam("customer.ssn", "51236")
                .formParam("customer.username", "testismail123")
                .formParam("customer.password", "password123")
                .formParam("repeatedPassword", "password123")
                .log().all()  // Log all request details
                .post("/register.htm")
                .then()       // Continue to add more logging
                .log().all()  // Log all response details
                .extract().response(); // Extract response

        // Print the response details
        System.out.println("Response Code for the Regiseration is is : " + response.getStatusCode());
        System.out.println("Response Body is : " + response.asString());

    }
}
