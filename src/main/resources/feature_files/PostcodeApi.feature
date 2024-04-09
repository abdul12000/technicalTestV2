@testToRun
Feature: PostcodeApi - Retrieve Postcode Or Zip code Information

  Background:
    Given service is up and running

  Scenario Outline: Retrieve Postcode Information for a Valid US Zip Code
    When I make a GET request to the API endpoint for zip code information using valid zip code as "<zipcode>" and "<countryAbrreviation>"
    Then the response status code should be 200 OK
    And the response body should contain the correct details for the provided Zip code as "<zipcode>", "<country>", "<countryAbrreviation>", "<placeName>", "<longitude>", "<state>", "<stateAbbreviation>" and "<latitude>"
    Examples:
      | zipcode | country       | countryAbrreviation | placeName     | longitude | state      | stateAbbreviation | latitude |
      | 90210   | United States | US                  | Beverly Hills | -118.4065 | California | CA                | 34.0901  |
      | 10001   | United States | US                  | New York City | -73.9967  | New York   | NY                | 40.7484  |

  Scenario Outline: Retrieve Postcode Information for a Valid GB postcode
    When I make a GET request to the API endpoint for postcode information using valid postcode as "<postcode>" and "<countryAbrreviation>"
    Then the response status code should be 200 OK
    And the response body should contain the correct details for the provided postcode as "<postcode>", "<country>", "<countryAbrreviation>", "<placeName>", "<longitude>", "<state>", "<stateAbbreviation>" and "<latitude>"
    Examples:
      | postcode | country       | countryAbrreviation | placeName   | longitude | state   | stateAbbreviation | latitude |
      | PO5      | Great Britain | GB                  | Portsmouth  | -1.0913   | England | ENG               | 50.799   |
      | SO2      | Great Britain | GB                  | Southampton | -1.4043   | England | ENG               | 50.9039  |

  Scenario Outline: Failure scenario -Retrieve Postcode Information for an Invalid Zip Code/Postcode
    When I make a GET request to the API endpoint with an invalid postcode or Zip code information using "<invalidCode>" and "<countryAbrreviation>"
    Then the response status code should be 404 Not Found
    Examples:
      | invalidCode | countryAbrreviation |
      | Q10         | GB                  |
      | 90          | US                  |

