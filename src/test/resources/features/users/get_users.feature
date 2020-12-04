Feature: Get information of Users

  In order to get information of the users
  As a valid Converter customer

  Background: Sets authentication
    Given I set valid authentication headers

  @functional
  Scenario: Verify that is possible to retrieve all users as list
    Given I am logged in the Converter Application
    When I send a GET request to "user/list"
    Then I validate the response has the "200" status code
