# file-conversion-api-tests

[![Build Status](https://travis-ci.com/AT-12/file-conversion-api-tests.svg?branch=develop)](https://travis-ci.com/AT-12/file-conversion-api-tests) 

[![Code Coverage](https://img.shields.io/codecov/c/github/AT-12/file-conversion-api-tests/develop.svg)](https://codecov.io/github/AT-12/file-conversion-api-tests?branch=develop)

[![Quality Gate](https://sonarcloud.io/api/project_badges/measure?project=AT-12_file-conversion-api-tests&metric=alert_status)](https://sonarcloud.io/dashboard/index/AT-12_file-conversion-api-tests)

# Description
This is the AT-12's project, it was made on API Testing's subject. 
The proposal of this project is to apply BDD using Gerking.  
# Environment setup
Create an account on http://47.252.25.244:8080 and set the following variables on gradle.properties.
- set baseUrl
- set username
- set password

# Execute tests
In order to execute the tests there are the followings commands:

- gradle check
- gradle checkstyleMain
- gradle test
- gradle executeBDDTests
# Endpoints tested
The endpoints tested are:
- GET /user/list
- POST /user/createUser/
- GET user/delete/{id} 
- POST user/edit/{id}
- POST user/add 
- POST /convertVideo
- POST /convertImage
- POST /convertAudio
- POST /extractMetadata