Feature: Create Users  

  In order to create an account
  As a valid Converter customer

  Background: Sets valid request
    Given I set the request headers without authentication

  @skipTest @deleteUser
  Scenario: Verify that is possible to create a new account
    When I send a POST request to "/user/createUser" with the following form data
      | username   | laura     |
      | name       | laura     |
      | lastName   | toro      |
      | password   | Secret123 |
      | rePassword | Secret123 |
    And I store the "username" value from request to clean workspace
    Then I validate the response has the "200" status code
    And I validate that the response body should match with "common/messageResponse.json" JSON schema
    And I validate that the response contain the following values
      | status  | 200                                                  |
      | message | Your account was created! Please, login to continue. |

  @skipTest @deleteUser
  Scenario: Verify that is not possible to create a new account with empty form data
    When I send a POST request to "/user/createUser" with the empty form data
    Then I validate the response has the "400" status code
    And I validate that the response body should match with "common/errorResponse.json" JSON schema
    And I validate that the response contain the following values
      | status | 400                              |
      | error  | The field username is mandatory. |

  @skipTest @deleteUser
  Scenario: Verify that is not possible to create a new account without name
    When I send a POST request to "/user/createUser" with the following form data
      | username   | laura     |
      | name       |           |
      | lastName   | toro      |
      | password   | Secret123 |
      | rePassword | Secret123 |
    And I store the "username" value from request to clean workspace
    Then I validate the response has the "400" status code
    And I validate that the response body should match with "common/errorResponse.json" JSON schema
    And I validate that the response contain the following values
      | status | 400                          |
      | error  | The field name is mandatory. |

  @skipTest @deleteUser
  Scenario: Verify that is not possible to create a new account without lastName
    When I send a POST request to "/user/createUser" with the following form data
      | username   | laura     |
      | name       | laura     |
      | lastName   |           |
      | password   | Secret123 |
      | rePassword | Secret123 |
    And I store the "username" value from request to clean workspace
    Then I validate the response has the "400" status code
    And I validate that the response body should match with "common/errorResponse.json" JSON schema
    And I validate that the response contain the following values
      | status | 400                              |
      | error  | The field lastName is mandatory. |

  @skipTest @deleteUser
  Scenario: Verify that is not possible to create a new account without username, name and lastName
    When I send a POST request to "/user/createUser" with the following form data
      | username   |           |
      | name       |           |
      | lastName   |           |
      | password   | Secret123 |
      | rePassword | Secret123 |
    And I store the "username" value from request to clean workspace
    Then I validate the response has the "400" status code
    And I validate that the response body should match with "common/errorResponse.json" JSON schema
    And I validate that the response contain the following values
      | status | 400                              |
      | error  | The field username is mandatory. |

  @skipTest @deleteUser
  Scenario: Verify that is not possible to create a new account with password confirmation different from password
    When I send a POST request to "/user/createUser" with the following form data
      | username   | laura     |
      | name       | laura     |
      | lastName   | toro      |
      | password   |           |
      | rePassword | Secret123 |
    And I store the "username" value from request to clean workspace
    Then I validate the response has the "400" status code
    And I validate that the response body should match with "common/errorResponse.json" JSON schema
    And I validate that the response contain the following values
      | status | 400                                               |
      | error  | Password and Password Confirmation are different. |

  @skipTest @deleteUser
  Scenario: Verify that is not possible to create a new account with password with less than eight characters and without one capital letter and at least one number
    When I send a POST request to "/user/createUser" with the following form data
      | username   | laura     |
      | name       | laura     |
      | lastName   | toro      |
      | password   |           |
      | rePassword | Secret123 |
    And I store the "username" value from request to clean workspace
    Then I validate the response has the "400" status code
    And I validate that the response body should match with "common/errorResponse.json" JSON schema
    And I validate that the response contain the following values
      | status | 400                                                            |
      | error  | Password must have at least one number and one capital letter. |
