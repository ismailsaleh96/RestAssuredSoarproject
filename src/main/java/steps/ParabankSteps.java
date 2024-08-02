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
                .formParam("customer.firstName", "John")
                .formParam("customer.lastName", "Doe")
                .formParam("customer.address.street", "123 Main St")
                .formParam("customer.address.city", "Anytown")
                .formParam("customer.address.state", "NY")
                .formParam("customer.address.zipCode", "12345")
                .formParam("customer.phoneNumber", "1234567890")
                .formParam("customer.ssn", "123-45-6789")
                .formParam("customer.username", username)
                .formParam("customer.password", password)
                .formParam("repeatedPassword", password)
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
