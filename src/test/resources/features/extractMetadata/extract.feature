Feature: Extract Metadata

  In order to extract metadata from a file
  As a valid Converter customer

  Background: Sets authentication
    Given I set valid authentication headers

  @functional
  Scenario: Verify that is possible to extract metadata from a file using TXT export format
    When I send a POST request to "/extractMetadata" with the following form data
      | file         | @"template/audio/audio.mp3"       |
      | md5          | 2688ed914ed6315229fb89e1e916cdee |
      | exportFormat | t                                |
      | fileName     | testExtractMetaData              |
      | detail       | d                                |
    Then I validate the response has the "200" status code
    And I validate that the response body should match with "common/messageResponse.json" JSON schema
    And I validate that the response container the following values
      | status | 200 |

  @functional
  Scenario: Verify that is possible to extract metadata from a file using CSV export format
    When I send a POST request to "/extractMetadata" with the following form data
      | file         | @"template/audio/audio.mp3"       |
      | md5          | 2688ed914ed6315229fb89e1e916cdee |
      | exportFormat | T                                |
      | fileName     | testExtractMetaData              |
      | detail       | d                                |
    Then I validate the response has the "200" status code
    And I validate that the response body should match with "common/messageResponse.json" JSON schema
    And I validate that the response container the following values
      | status | 200 |

  @functional
  Scenario: Verify that is possible to extract metadata from a file using JSON export format
    When I send a POST request to "/extractMetadata" with the following form data
      | file         | @"template/audio/demo.mp3"       |
      | md5          | 2688ed914ed6315229fb89e1e916cdee |
      | exportFormat | j                                |
      | fileName     | testExtractMetaData              |
      | detail       | d                                |
    Then I validate the response has the "200" status code
    And I validate that the response body should match with "common/messageResponse.json" JSON schema
    And I validate that the response container the following values
      | status | 200 |

  @negative
  Scenario: Verify that is not possible to extract metadata from a file using an invalid export format
    When I send a POST request to "/extractMetadata" with the following form data
      | file         | @"template/audio/demo.mp3"       |
      | md5          | 2688ed914ed6315229fb89e1e916cdee |
      | exportFormat | badFormat                        |
      | fileName     | testExtractMetaData              |
      | detail       | d                                |
    Then I validate the response has the "400" status code
    And I validate that the response body should match with "common/errorResponse.json" JSON schema
    And I validate that the response container the following values
      | status | 400                     |
      | error  | "Invalid export format" |

  @negative
  Scenario: Verify that is not possible to extract metadata without configuration parameters
    When I send a POST request to "/extractMetadata" with the empty form data
    Then I validate the response has the "400" status code
    And I validate that the response body should match with "common/errorResponse.json" JSON schema
    And I validate that the response container the following values
      | status | 400                   |
      | error  | "Failed format empty" |
