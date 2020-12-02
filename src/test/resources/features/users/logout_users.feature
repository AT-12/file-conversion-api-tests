Feature: Logout Users

  In order to create an account
  As a valid Converter customer

  @functional @createUser @deleteUser
  Scenario: Verify that is possible to logout user
    Given I am logged in the Converter Application
    When I send a GET request to "/logout"
    Then I validate the response has the "302" status code
    And I validate that the response body should match with "users/logout.json" JSON schema
    And I validate that the response contain the following values
      | status | 302 |