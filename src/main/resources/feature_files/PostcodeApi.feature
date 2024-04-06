@testToRun
Feature: PostcodeApi

  Scenario Outline: Get request for postcode that returns one place name
    Given service is up and running
    When I send GET request for a "<postcode>" in the "<countryAbrreviation>"
    Then the following details "<postcode>", "<country>", "<countryAbrreviation>", "<placeName>", "<longitude>", "<state>", "<stateAbbreviation>" and "<latitude>" for the post code are returned in the response body with status code of 200
    Examples:
      | postcode | country       | countryAbrreviation | placeName     | longitude | state      | stateAbbreviation | latitude |
      | PO5      | Great Britain | GB                  | Portsmouth    | -1.0913   | England    | ENG               | 50.799   |
      | SO2      | Great Britain | GB                  | Southampton   | -1.4043   | England    | ENG               | 50.9039  |
      | 90210    | United States | US                  | Beverly Hills | -118.4065 | California | CA                | 34.0901  |

  Scenario Outline: Failure scenario for postcode Get request
    Given service is up and running
    When I send GET request for an invalid "<postcode>" in the "<countryAbrreviation>"
    Then 404 Not found error status code is returned
    Examples:
      | postcode | countryAbrreviation |
      | Q10      | GB                  |
      | 90       | US                  |

