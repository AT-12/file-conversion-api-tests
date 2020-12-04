Feature: Logout Users

  In order to create an account
  As a valid Converter customer

  Background: Sets authentication
    Given I set valid authentication headers

  @functional @createUser @deleteUser
  Scenario: Verify that is possible to logout user
    When I send a GET request to "/logout"
    Then I validate the response has the "200" status code
