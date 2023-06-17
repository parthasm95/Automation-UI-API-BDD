@api @regression
Feature: Restful Booker POST API Calls

  Scenario: verify status code and response body when user try create new booking
    Given I have the url to create new booking
    When I send post request with payload
    Then status code should be 200
    And the response body should contain booking id and requested payload
    And the response body should be as per schema "createBookingResponseSchema.json"