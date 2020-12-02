Feature: Delete Users  

  In order to delete an account
  As a valid Converter customer

  Background: Sets authentication
    Given I set valid authentication headers

  @functional @createUser
 Scenario: Verify that is possible to delete an user
    When I send a DELETE request to "/user/delete/{id}"
    Then I validate the response has the "200" status code
    And I validate that the response body should match with "common/messageResponse.json" JSON schema
    And I validate that the response contain the following values
      | status  | 200          |
      | message | User deleted |

  @negative @createUser @deleteUser
  Scenario: Verify that is not possible to delete an user using an invalid id
    When I send a DELETE request to "/user/delete/{id}"
    Then I validate the response has the "400" status code
    And I validate that the response body should match with "common/errorResponse.json" JSON schema
    And I validate that the response contain the following values
      | status | 400                                  |
      | error  | The user with Id: {id}does not exist |
