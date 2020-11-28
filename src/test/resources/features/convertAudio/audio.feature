Feature: Audio Converter

  In order to convert an audio file
  As a valid Converter customer

  Background: Sets authentication
    Given I set valid authentication headers

  @functional
  Scenario: Verify that is possible to converted an audio file
    When I send a POST request to "/convertAudio" with the following form data
      | file            | @"audio/audio.mp3"               |
      | md5             | 0c481e87f2774b1bd41a0a70d9b70d11 |
      | exportFormat    | .mp3                             |
      | name            | demoConvertFile                  |
      | channel         | 2                                |
      | codec           | libmp3lame                       |
      | bitRate         | 32k                              |
      | sampleRate      | 22050                            |
      | cut             | on                               |
      | start           | 00:00:00                         |
      | duration        | 10                               |
      | secondsToOutput | 0:00:27                          |
    Then I validate the response has the "200" status code
    And I validate that the response body should match with "common/messageResponse.json" JSON schema
    And I validate that the response contain the following values
      | status | 200 |

  @functional
  Scenario: Verify that is possible to converted an audio file when the parameter cut is off
    When I send a POST request to "/convertAudio" with the following form data
      | file            | @"audio/audio.mp3"               |
      | md5             | 0c481e87f2774b1bd41a0a70d9b70d11 |
      | exportFormat    | .mp3                             |
      | name            | demoConvertFile                  |
      | channel         | 2                                |
      | codec           | libmp3lame                       |
      | bitRate         | 32k                              |
      | sampleRate      | 22050                            |
      | cut             | off                              |
      | start           | 00:00:00                         |
      | duration        | 0                                |
      | secondsToOutput | 0:00:00                          |
    Then I validate the response has the "200" status code
    And I validate that the response body should match with "common/messageResponse.json" JSON schema
    And I validate that the response contain the following values
      | status | 200 |

  @negative
  Scenario: Verify that the audio file is not converted when an invalid channel is used
    When I send a POST request to "/convertAudio" with the following form data
      | file            | @"audio/audio.mp3"               |
      | md5             | 0c481e87f2774b1bd41a0a70d9b70d11 |
      | exportFormat    | .mp3                             |
      | name            | demoConvertFile                  |
      | channel         | 20                               |
      | codec           | libmp3lame                       |
      | bitRate         | 32k                              |
      | sampleRate      | 22050                            |
      | cut             | off                              |
      | start           | 00:00:00                         |
      | duration        | 0                                |
      | secondsToOutput | 0:00:00                          |
    Then I validate the response has the "400" status code
    And I validate that the response body should match with "common/errorResponse.json" JSON schema
    And I validate that the response contain the following values
      | status | 400 |

  @negative
  Scenario: Verify that the audio file is not converted when an invalid export format is used
    When I send a POST request to "/convertAudio" with the following form data
      | file            | @"audio/audio.mp3"               |
      | md5             | 0c481e87f2774b1bd41a0a70d9b70d11 |
      | exportFormat    | .mp5                             |
      | name            | demoConvertFile                  |
      | channel         | 2                                |
      | codec           | libmp3lame                       |
      | bitRate         | 32k                              |
      | sampleRate      | 22050                            |
      | cut             | on                               |
      | start           | 00:00:00                         |
      | duration        | 10                               |
      | secondsToOutput | 0:00:00                          |
    Then I validate the response has the "400" status code
    And I validate that the response body should match with "common/errorResponse.json" JSON schema
    And I validate that the response contain the following values
      | status | 400                  |
      | error  | Invalid audio format |

  @negative
  Scenario: Verify that is not possible convert audio without configurations parameters
    When I send a POST request to "/convertAudio" with the empty form data
    Then I validate the response has the "400" status code
    And I validate that the response body should match with "common/errorResponse.json" JSON schema
    And I validate that the response contain the following values
      | status | 400                 |
      | error  | Invalid audio codec |

  @negative
  Scenario: Verify that is not possible convert audio when an invalid md5 is used
    When I send a POST request to "/convertAudio" with the following form data
      | file            | @"audio/audio.mp3" |
      | md5             | badMd5             |
      | exportFormat    | .mp3               |
      | name            | demoConvertFile    |
      | channel         | 2                  |
      | codec           | libmp3lame         |
      | bitRate         | 32k                |
      | sampleRate      | 22050              |
      | cut             | off                |
      | start           | 00:00:00           |
      | duration        | 0                  |
      | secondsToOutput | 0:00:00            |

    Then I validate the response has the "400" status code
    And I validate that the response body should match with "common/errorResponse.json" JSON schema
    And I validate that the response should contain the following values
      | status | 400              |
      | error  | Invalid md5 code |
