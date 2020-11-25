Feature: Video Controller

  In order to convert a file
  As a valid Converter customer

  Background: Sets authentication
    Given I set valid authentication headers

  Scenario: Verify that "/convertVideo" endpoint can perform "POST" request
    When I send a POST request to "/convertVideo" with the following form data
      | file              | @"template/video/demo.mp4"       |
      | md5               | 16b258600183d1d5dc0631c82de13000 |
      | exportFormat      | .mp4                             |
      | name              | videoDemo                        |
      | videoCodec        | h264                             |
      | codec             | mp3                              |
      | extractThumbnail  | true                             |
      | extractMetadata   | true                             |

    Then I validate the response has the "200" status code
    And I validate that the response body should match with "video/videoSchema.json" JSON schema
    And I validate that the response contain the following values
      | status  | 200 |

  Scenario: Verify that "/convertVideo" endpoint can perform "POST" request
    When I send a POST request to "/convertVideo" with the following form data changing the exportFormat to MOV
      | file              | @"template/video/demo.mp4"       |
      | md5               | 16b258600183d1d5dc0631c82de13000 |
      | exportFormat      | .mov                             |
      | name              | videoDemo                        |
      | videoCodec        | h264                             |
      | codec             | mp3                              |
      | extractThumbnail  | true                             |
      | extractMetadata   | true                             |

    Then I validate the response has the "200" status code
    And I validate that the response body should match with "video/videoSchema.json" JSON schema
    And I validate that the response contain the following values
      | status  | 200 |


  Scenario: Verify that "/convertVideo" endpoint can perform "POST" request
    When I send a POST request to "/convertVideo" with the following form data changing the exportFormat to GIF
      | file              | @"template/video/demo.mp4"       |
      | md5               | 16b258600183d1d5dc0631c82de13000 |
      | exportFormat      | .gif                             |
      | frames            | 24                               |
      | controlLoop       | 0                                |
      | duration          | 0:00:30                          |
      | start             | 00:00:05                         |
      | secondsToOutput   | 15                               |
      | name              | videoDemo                        |
      | extractThumbnail  | true                             |
      | extractMetadata   | true                             |

    Then I validate the response has the "200" status code
    And I validate that the response body should match with "video/videoSchema.json" JSON schema
    And I validate that the response contain the following values
      | status  | 200 |

  Scenario: Verify that "/convertVideo" endpoint return error when I send Invalid information
    When I send a POST request to "/convertVideo" with the following form data
    When I set the following form data
      | file              | @"template/video/demo.mp4"       |
      | md5               | 16b258600183d1d5dc0631c82de13000 |
      | exportFormat      | .mp4                             |
      | name              | videoDemo                        |
      | videoCodec        | h264                             |
      | codec             | 47859                            |
      | extractThumbnail  | true                             |
      | extractMetadata   | true                             |
    Then I validate the response has the "400" state code
    And I validate that the response body should match with "video/videoErrorSchema.json" JSON schema
    And I validate that the response contain the following values
      | status  | 400             |
      | error   | "Invalid codec" |

  Scenario: Verify that "/convertVideo" endpoint return error when I send Invalid information
    When I send a POST request to "/convertVideo" with the empty form data
    Then I validate the response has the "400" state code
    And I validate that the response body should match with "video/videoErrorSchema.json" JSON schema
    And I validate that the response contain the following values
      | status | 400           |
      | error  | "Bad Request" |