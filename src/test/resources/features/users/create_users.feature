Feature: Create Users  

  In order to create an account
  As a valid Converter customer

  @functional @deleteUser
  Scenario: Verify that is possible to create a new account
    When I send a POST request to "/user/createUser" with the following form data
      | username   | laura     |
      | name       | laura     |
      | lastName   | toro      |
      | password   | Secret123 |
      | rePassword | Secret123 |
    Then I validate the response has the "200" status code
    And I validate that the response body should match with "users/createUsers.json" JSON schema
    And I validate that the response contains the following values
      | status  | 200                                                  |
      | message | Your account was created! Please, login to continue. |

  @negative @deleteUser
  Scenario: Verify that is not possible to create a new account without username
    When I send a POST request to "/user/createUser" with the following form data
      | username   |           |
      | name       | laura     |
      | lastName   | toro      |
      | password   | Secret123 |
      | rePassword | Secret123 |
    Then I validate the response has the "400" status code
    And I validate that the response body should match with "users/createUsers.json" JSON schema
    And I validate that the response contains the following values
      | status | 400                              |
      | error  | The field username is mandatory. |

  @negative @deleteUser
  Scenario: Verify that is not possible to create a new account without name
    When I send a POST request to "/user/createUser" with the following form data
      | username   | laura     |
      | name       |           |
      | lastName   | toro      |
      | password   | Secret123 |
      | rePassword | Secret123 |
    Then I validate the response has the "400" status code
    And I validate that the response body should match with "users/createUsers.json" JSON schema
    And I validate that the response contains the following values
      | status | 400                          |
      | error  | The field name is mandatory. |

  @negative @deleteUser
  Scenario: Verify that is not possible to create a new account without lastName
    When I send a POST request to "/user/createUser" with the following form data
      | username   | laura     |
      | name       | laura     |
      | lastName   |           |
      | password   | Secret123 |
      | rePassword | Secret123 |
    Then I validate the response has the "400" status code
    And I validate that the response body should match with "users/createUsers.json" JSON schema
    And I validate that the response contains the following values
      | status | 400                              |
      | error  | The field lastName is mandatory. |

  @negative @deleteUser
  Scenario: Verify that is not possible to create a new account without username, name and lastName
    When I send a POST request to "/user/createUser" with the following form data
      | username   |           |
      | name       |           |
      | lastName   |           |
      | password   | Secret123 |
      | rePassword | Secret123 |
    Then I validate the response has the "400" status code
    And I validate that the response body should match with "users/createUsers.json" JSON schema
    And I validate that the response contains the following values
      | status | 400                              |
      | error  | The field username is mandatory. |

  @negative @deleteUser
  Scenario: Verify that is not possible to create a new account with password confirmation different from password
    When I send a POST request to "/user/createUser" with the following form data
      | username   | laura     |
      | name       | laura     |
      | lastName   | toro      |
      | password   |           |
      | rePassword | Secret123 |
    Then I validate the response has the "400" status code
    And I validate that the response body should match with "users/createUsers.json" JSON schema
    And I validate that the response contains the following values
      | status | 400                                               |
      | error  | Password and Password Confirmation are different. |

  @negative @deleteUser
  Scenario: Verify that is not possible to create a new account with password with less than eight characters and without one capital letter and at least one number
    When I send a POST request to "/user/createUser" with the following form data
      | username   | laura     |
      | name       | laura     |
      | lastName   | toro      |
      | password   |           |
      | rePassword | Secret123 |
    Then I validate the response has the "400" status code
    And I validate that the response body should match with "users/createUsers.json" JSON schema
    And I validate that the response contains the following values
      | status | 400                                                            |
      | error  | Password must have at least one number and one capital letter. |

  @functional @createUser @deleteUser
  Scenario: Verify that is possible to logout user
    Given I am logged in the Converter Application
    When I send a GET request to "/logout"
    Then I validate the response has the "302" status code
    And I validate that the response body should match with "user/logout.json" JSON schema
    And I validate that the response contains the following values
      | status | 302 |
