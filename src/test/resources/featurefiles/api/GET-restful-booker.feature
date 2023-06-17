@api @regression
Feature: Restful Booker Get API Calls

  Scenario: verify status code and response body when user try to get booking details
    Given I have the url to get all bookings
    When I send get request
    Then status code should be 200
    And all booking IDs should be present in response