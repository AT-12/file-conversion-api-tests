Feature: Video Controller

  In order to convert a video file
  As a valid Converter customer

  Background: Sets authentication
    Given I set valid authentication headers

  @functional
  Scenario: Verify that is possible to convert a video to MP4 format
    When I send a POST request to "/convertVideo" with the following form data
      | file             | @"video/video.mp4"               |
      | md5              | d9061d3da8601932e98f79ec8ba1c877 |
      | exportFormat     | .mp4                             |
      | name             | videoDemo                        |
      | videoCodec       | h264                             |
      | codec            | mp3                              |
      | extractThumbnail | true                             |
      | extractMetadata  | true                             |
    Then I validate the response has the "200" status code
    And I validate that the response body should match with "common/messageResponse.json" JSON schema
    And I validate that the response contain the following values
      | status | 200 |

  @functional
  Scenario: Verify that is possible to convert a video to MOV format
    When I send a POST request to "/convertVideo" with the following form data
      | file             | @"video/video.mp4"               |
      | md5              | d9061d3da8601932e98f79ec8ba1c877 |
      | exportFormat     | .mov                             |
      | name             | videoDemo                        |
      | videoCodec       | h264                             |
      | codec            | mp3                              |
      | extractThumbnail | true                             |
      | extractMetadata  | true                             |
    Then I validate the response has the "200" status code
    And I validate that the response body should match with "common/messageResponse.json" JSON schema
    And I validate that the response contain the following values
      | status | 200 |

  @functional
  Scenario: Verify that is possible to convert a video to GIF format
    When I send a POST request to "/convertVideo" with the following form data
      | file             | @"video/video.mp4"               |
      | md5              | d9061d3da8601932e98f79ec8ba1c877 |
      | exportFormat     | .gif                             |
      | frames           | 24                               |
      | controlLoop      | 0                                |
      | duration         | 0:00:30                          |
      | start            | 00:00:05                         |
      | secondsToOutput  | 15                               |
      | name             | videoDemo                        |
      | extractThumbnail | true                             |
      | extractMetadata  | true                             |
    Then I validate the response has the "200" status code
    And I validate that the response body should match with "common/messageResponse.json" JSON schema
    And I validate that the response contain the following values
      | status | 200 |

  @negative
  Scenario: Verify that is not possible to convert a video using an invalid audio codec.
    When I send a POST request to "/convertVideo" with the following form data
      | file             | @"video/video.mp4"               |
      | md5              | d9061d3da8601932e98f79ec8ba1c877 |
      | exportFormat     | .mp4                             |
      | name             | videoDemo                        |
      | videoCodec       | h264                             |
      | codec            | 47859                            |
      | extractThumbnail | true                             |
      | extractMetadata  | true                             |
    Then I validate the response has the "400" status code
    And I validate that the response body should match with "common/errorResponse.json" JSON schema
    And I validate that the response contain the following values
      | status | 400 |

  @negative
  Scenario: Verify that is not possible to convert a video without configuration parameters.
    When I send a POST request to "/convertVideo" with the empty form data
    Then I validate the response has the "400" status code
    And I validate that the response body should match with "common/errorResponse.json" JSON schema
    And I validate that the response contain the following values
      | status | 400         |

  @negative
  Scenario: Verify that is not possible to convert a video to GIF format without frames parameter
    When I send a POST request to "/convertVideo" with the following form data
      | file             | @"video/video.mp4"               |
      | md5              | d9061d3da8601932e98f79ec8ba1c877 |
      | exportFormat     | .gif                             |
      | frames           |                                  |
      | controlLoop      | 0                                |
      | duration         | 0:00:30                          |
      | start            | 00:00:05                         |
      | secondsToOutput  | 15                               |
      | name             | videoDemo                        |
      | extractThumbnail | true                             |
      | extractMetadata  | true                             |
    Then I validate the response has the "400" status code
    And I validate that the response body should match with "common/errorResponse.json" JSON schema
    And I validate that the response contain the following values
      | status | 400 |

  @negative
  Scenario: Verify that is not possible to convert a video to GIF format without controlLoop parameter
    When I send a POST request to "/convertVideo" with the following form data
      | file             | @"video/video.mp4"               |
      | md5              | d9061d3da8601932e98f79ec8ba1c877 |
      | exportFormat     | .gif                             |
      | frames           | 24                               |
      | controlLoop      | 0                                |
      | duration         | 0:00:30                          |
      | start            | 00:00:05                         |
      | secondsToOutput  | 15                               |
      | name             | videoDemo                        |
      | extractThumbnail | true                             |
      | extractMetadata  | true                             |
    Then I validate the response has the "400" status code
    And I validate that the response body should match with "common/errorResponse.json" JSON schema
    And I validate that the response contain the following values
      | status | 400 |

  @negative
  Scenario: Verify that is not possible to convert a video to GIF format without start parameter
    When I send a POST request to "/convertVideo" with the following form data
      | file             | @"video/video.mp4"               |
      | md5              | d9061d3da8601932e98f79ec8ba1c877 |
      | exportFormat     | .gif                             |
      | frames           | 24                               |
      | controlLoop      | 0                                |
      | duration         | 0:00:30                          |
      | start            |                                  |
      | secondsToOutput  | 15                               |
      | name             | videoDemo                        |
      | extractThumbnail | true                             |
      | extractMetadata  | true                             |
    Then I validate the response has the "400" status code
    And I validate that the response body should match with "common/errorResponse.json" JSON schema
    And I validate that the response contain the following values
      | status | 400 |
