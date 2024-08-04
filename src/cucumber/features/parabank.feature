Feature: Sample feature to test Register and Login by Cucumber RestAssured and TestNG

  Scenario: Successful registration and login for Parabank

   Given a user with valid registration data
    When the user registers
    Then the registration is successful
    And the status code is 200
    When the user logs in with valid credentials
    Then the login is successful
    And the status code is 200