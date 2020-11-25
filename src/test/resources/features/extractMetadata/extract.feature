Feature: Extract Metadata

  In order to extract metadata from a file
  As a valid Converter customer

  Background: Sets authentication
    Given I set valid authentication headers
  
  @functional
  Scenario: Verify that "/extractMetadata" endpoint can perform "POST" request with "exportFormat" field as TXT
    When I send a POST request to "/extractMetadata" with the following form data
      | file         | @"template/audio/demo.mp3"       |
      | md5          | e1b3fab24c8af81c1aa13dbbb4e44ff0 |
      | exportFormat | t                                |
      | fileName     | testExtractMetaData              |
      | detail       | d                                |
    Then I validate the response has the "200" status code
    And I validate that the response body should match with "metadata/metadataSchema.json" JSON schema
    And I validate that the response container the following values
      | status  | 200 |

  @functional
  Scenario: Verify that "/extractMetadata" endpoint can perform "POST" request with "exportFormat" field as CSV
    When I send a POST request to "/extractMetadata" with the following form data
      | file         | @"template/audio/demo.mp3"       |
      | md5          | e1b3fab24c8af81c1aa13dbbb4e44ff0 |
      | exportFormat | T                                |
      | fileName     | testExtractMetaData              |
      | detail       | d                                |
    Then I validate the response has the "200" status code
    And I validate that the response body should match with "metadata/metadataSchema.json" JSON schema
    And I validate that the response container the following values
      | status  | 200                                                                     |

  @functional
  Scenario: Verify that "/extractMetadata" endpoint can perform "POST" request with "exportFormat" field as JSON
    When I send a POST request to "/extractMetadata" with the following form data
      | file | @"template/audio/demo.mp3" |
      | md5          | e1b3fab24c8af81c1aa13dbbb4e44ff0 |
      | exportFormat | j                                |
      | fileName     | testExtractMetaData              |
      | detail       | d                                |
    Then I validate the response has the "200" status code
    And I validate that the response body should match with "metadata/metadataSchema.json" JSON schema
    And I validate that the response container the following values
      | status  | 200                                                                     |

  @negative
  Scenario: Verify that "/convertAudio" endpoint return error when I send invalid exportFormat
    When I send a POST request to "/extractMetadata" with the following form data
      | file         | @"template/audio/demo.mp3"       |
      | md5          | e1b3fab24c8af81c1aa13dbbb4e44ff0 |
      | exportFormat | badFormat                        |
      | fileName     | testExtractMetaData              |
      | detail       | d                                |
    Then I validate the response has the "400" status code
    And I validate that the response body should match with "audio/audioErrorSchema.json" JSON schema
    And I validate that the response container the following values
      | status | 400                    |
      | error | "Invalid export format" |
      
  @negative
  Scenario: Verify that "/extractMetadata" endpoint return error when I send empty information
    When I send a POST request to "/extractMetadata" with the empty form data
    Then I validate the response has the "400" status code
    And I validate that the response body should match with "audio/audioErrorSchema.json" JSON schema
    And I validate that the response container the following values
      | status | 400                   |
      | error  | "Failed format empty" |
