Feature: Extract Metadata

  In order to extract metadata from a file
  As a valid Converter customer

  Background: Sets authentication
    Given I set valid authentication headers

  @functional
  Scenario: Verify that is possible to extract metadata from a file using TXT export format
    When I send a POST request to "/extractMetadata" with the following form data
      | file         | @"audio/audio.mp3"               |
      | md5          | 0c481e87f2774b1bd41a0a70d9b70d11 |
      | exportFormat | T                                |
      | fileName     | testExtractMetaData              |
      | detail       | d                                |
    Then I validate the response has the "200" status code
    And I validate that the response body should match with "common/messageResponse.json" JSON schema
    And I validate that the response contain the following values
      | status | 200 |

  @functional
  Scenario: Verify that is possible to extract metadata from a file using CSV export format
    When I send a POST request to "/extractMetadata" with the following form data
      | file         | @"audio/audio.mp3"               |
      | md5          | 0c481e87f2774b1bd41a0a70d9b70d11 |
      | exportFormat | T                                |
      | fileName     | testExtractMetaData              |
      | detail       | d                                |
    Then I validate the response has the "200" status code
    And I validate that the response body should match with "common/messageResponse.json" JSON schema
    And I validate that the response contain the following values
      | status | 200 |

  @functional
  Scenario: Verify that is possible to extract metadata from a file using JSON export format
    When I send a POST request to "/extractMetadata" with the following form data
      | file         | @"audio/audio.mp3"               |
      | md5          | 0c481e87f2774b1bd41a0a70d9b70d11 |
      | exportFormat | j                                |
      | fileName     | testExtractMetaData              |
      | detail       | d                                |
    Then I validate the response has the "200" status code
    And I validate that the response body should match with "common/messageResponse.json" JSON schema
    And I validate that the response contain the following values
      | status | 200 |

  @skipTest
  Scenario: Verify that is not possible to extract metadata from a file using an invalid export format
    When I send a POST request to "/extractMetadata" with the following form data
      | file         | @"audio/audio.mp3"               |
      | md5          | 0c481e87f2774b1bd41a0a70d9b70d11 |
      | exportFormat | badFormat                        |
      | fileName     | testExtractMetaData              |
      | detail       | d                                |
    Then I validate the response has the "400" status code
    And I validate that the response body should match with "common/errorResponse.json" JSON schema
    And I validate that the response contain the following values
      | status | 400                   |
      | error  | Invalid export format |

  @negative
  Scenario: Verify that is not possible to extract metadata without configuration parameters
    When I send a POST request to "/extractMetadata" with the empty form data
    Then I validate the response has the "400" status code
    And I validate that the response body should match with "common/errorResponse.json" JSON schema
    And I validate that the response contain the following values
      | status | 400                 |
      | error  | Failed format empty |
