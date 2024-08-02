package java.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ParabankSteps {
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
                .formParam("customer.username", ismail321)
                .formParam("customer.password", 12345)
                .formParam("repeatedPassword", 12345)
                .post("/register.htm");
    }

    @Then("the registration is successful")
    public void theRegistrationIsSuccessful() {
        assertThat(response.statusCode(), equalTo(200));
    }

    @When("the user logs in with valid credentials")
    public void theUserLogsInWithValidCredentials() {
        response = given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("username", username)
                .formParam("password", password)
                .post("/login.htm");
    }

    @Then("the login is successful")
    public void theLoginIsSuccessful() {
        assertThat(response.statusCode(), equalTo(200));
    }
}
