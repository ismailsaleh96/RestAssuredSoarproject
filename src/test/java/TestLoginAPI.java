import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class TestLoginAPI {
    public static void main(String[] args) {
        RestAssured.baseURI = "https://parabank.parasoft.com/parabank";
        Response response = given()
                .formParam("username", "Ahmed1") //Vaild YserName and Password
                .formParam("password", "1234")
                .log().all()
                .post("/login.htm");

        System.out.println("Response Code is : " + response.getStatusCode());
        System.out.println("Response Body is : " + response.asString());

        // Check if the response is a redirect
        if (response.getStatusCode() == 302) {
            String redirectUrl = response.getHeader("Location");
            if (redirectUrl != null && redirectUrl.contains("/overview.htm")) {
                System.out.println("Login Status is : Login Successful. Redirected to Overview page.");
            } else {
                System.out.println("Login Status is : Login Failed. Redirected to: " + redirectUrl);
            }
        } else {
            System.out.println("Unexpected response code: " + response.getStatusCode());
        }
    }
}
