import dev.failsafe.internal.util.Assert;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import io.cucumber.java.en.And;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.restassured.http.ContentType;
import io.restassured.response.Response;



public class TestScenario extends  TestRunner {
private Response response;
    private String username;
    private String password;

    static {
        RestAssured.baseURI = "https://parabank.parasoft.com/parabank";
    }           

    @Given("a user with valid registration data")
    public void aUserWithValidRegistrationData() {
        username = "testismail" + System.currentTimeMillis();
        password = "password123";
        System.out.println("Username: " + username);
    }

    @When("the user registers")
    public void theUserRegisters() {
        response = given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("customer.firstName", "ismail")
                .formParam("customer.lastName", "saleh")
                .formParam("customer.address.street", "4 Maadi Street")
                .formParam("customer.address.city", "Cairo")
                .formParam("customer.address.state", "CA")
                .formParam("customer.address.zipCode", "12345")
                .formParam("customer.phoneNumber", "1151279456")
                .formParam("customer.ssn", "51236")
                .formParam("customer.username", username )
                .formParam("customer.password", password )
                .formParam("repeatedPassword", password )
                .post("/register.htm");
        System.out.println("Response: " + response.asString());
        System.out.println("Response Body is : " + response.asString());
        System.out.println("Response Headers is : " + response.getHeaders());
    }

    @Then("the registration is successful")
    public void theRegistrationIsSuccessful() {
        System.out.println("Response: " + response.asString());
        assertThat(response.statusCode(), equalTo(200));
    }



    @When("the user logs in with valid credentials")
    public void theUserLogsInWithValidCredentials() {
        Response response = given()
                .formParam("username", "Ahmed1")
                .formParam("password", "1234")
                .post("/login.htm");
        System.out.println("Response Code is : " + response.getStatusCode());
        System.out.println("Response Body is : " + response.asString());
    }

    @Then("the login is successful")
    public void theLoginIsSuccessful() {
        if (response.getStatusCode() == 302) {
            String redirectUrl = response.getHeader("Location");
            if (redirectUrl != null && redirectUrl.contains("/overview.htm")) {
                System.out.println("Login Successful. Redirected to Overview page.");
            } else {
                System.out.println("Login Failed. Redirected to: " + redirectUrl);
            }
        } else {
            System.out.println("Unexpected response code: " + response.getStatusCode());
        }
    }
}



