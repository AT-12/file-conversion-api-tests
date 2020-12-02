Feature: Extract text from image

  In order to extract text from an image
  As a valid Converter customer

  Background: Set authentication
    Given I set valid authentication headers

  @functional
  Scenario: Verify that is possible to extract text from an image
    When I send a POST request to "/convertExtractText" with the following form data
      | file         | @"image/text.png"                |
      | md5          | f31e75933983501423ca55e176ca163e |
      | language     | spa                              |
      | exportFormat | .txt                             |
    Then I validate the response has the "200" status code
    And I validate that the response body should match with "common/messageResponse.json" JSON schema
    And I validate that the response contain the following values
      | status  | 200                                                          |
      | message | storage/convertedFiles//f31e75933983501423ca55e176ca163e.zip |

  @skipTest
  Scenario: Verify that is not possible to extract text with an invalid language
    When I send a POST request to "/convertExtractText" with the following form data
      | file         | @"image/text.png"                |
      | md5          | f31e75933983501423ca55e176ca163e |
      | language     | spaniol                           |
      | exportFormat | .txt                             |
    Then I validate the response has the "400" status code
    And I validate that the response body should match with "common/errorResponse.json" JSON schema
    And I validate that the response contain the following values
      | status  | 400                    |
      | error   | Failed in the language |

  @negative
  Scenario: Verify that is not possible to extract text with an invalid md5
    When I send a POST request to "/convertExtractText" with the following form data
      | file         | @"image/text.png"                |
      | md5          | f31e75933983501423ca55e176ca163w |
      | language     | spa                              |
      | exportFormat | .txt                             |
    Then I validate the response has the "400" status code
    And I validate that the response body should match with "common/errorResponse.json" JSON schema
    And I validate that the response contain the following values
      | status  | 400               |
      | error   | Failed in the md5 |

  @negative
  Scenario: Verify that is not possible to extract text with an empty language
    When I send a POST request to "/convertExtractText" with the following form data
      | file         | @"image/text.png"                |
      | md5          | f31e75933983501423ca55e176ca163e |
      | exportFormat | .txt                             |
    Then I validate the response has the "400" status code
    And I validate that the response body should match with "common/errorResponse.json" JSON schema
    And I validate that the response contain the following values
      | status | 400                   |
      | error  | failed Language empty |

  @negative
  Scenario: Verify that is not possible to extract text with an empty exportFormat
    When I send a POST request to "/convertExtractText" with the following form data
      | file         | @"image/text.png"                |
      | md5          | f31e75933983501423ca55e176ca163e |
      | language     | spa                              |
    Then I validate the response has the "400" status code
    And I validate that the response body should match with "common/errorResponse.json" JSON schema
    And I validate that the response contain the following values
      | status | 400                 |
      | error  | Failed format empty |

  @negative
  Scenario: Verify that is not possible to extract text without configuration parameters
    When I send a POST request to "/convertExtractText" with the empty form data
    Then I validate the response has the "400" status code
    And I validate that the response body should match with "common/errorResponse.json" JSON schema
    And I validate that the response contain the following values
      | status | 400                 |
      | error  | Failed format empty |
