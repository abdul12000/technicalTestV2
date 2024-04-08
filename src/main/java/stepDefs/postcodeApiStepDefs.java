package stepDefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class postcodeApiStepDefs extends BaseSteps {
    Response responseForServiceCall, responseForGetAPostCodeCall;

    @Given("service is up and running")
    public void service_is_up_and_running() {
        setEndpointPath(ServiceUrl);
        responseForServiceCall = getCall();
        assertThat(responseForServiceCall.statusCode(), equalTo(200));
    }

    @When("I make a GET request to the API endpoint with an invalid postcode or Zip code information using {string} and {string}")
    @When("I make a GET request to the API endpoint for postcode information using valid postcode as {string} and {string}")
    @When("I make a GET request to the API endpoint for zip code information using valid zip code as {string} and {string}")
    public void iMakeAGETRequestToTheAPIEndpointForPostcodeInformationUsingValidZipCodeAsAnd(String pCode, String cAbbreviation) {
        if (cAbbreviation.equalsIgnoreCase("GB")) {
            setEndpointPath(postCodeUrl_GB + pCode);
        } else {
            setEndpointPath(postCodeUrl_US + pCode);
        }
        responseForGetAPostCodeCall = getCall();

    }

    @Then("the response status code should be {int} Not Found")
    @Then("the response status code should be {int} OK")
    public void theResponseStatusCodeShouldBeOK(int sCode) {
        assertThat(responseForGetAPostCodeCall.statusCode(), equalTo(sCode));
    }

    @And("the response body should contain the correct details for the provided Zip code as {string}, {string}, {string}, {string}, {string}, {string}, {string} and {string}")
    @And("the response body should contain the correct details for the provided postcode as {string}, {string}, {string}, {string}, {string}, {string}, {string} and {string}")
    public void theResponseBodyShouldContainTheCorrectDetailsForTheProvidedPostcodeAsAnd(String pCode, String country, String cAbbreviation, String pName, String longitude, String state, String sAbbreviation, String latitude) {
        assertThat(responseForGetAPostCodeCall.body().jsonPath().get("'post code'"), equalTo(pCode));
        assertThat(responseForGetAPostCodeCall.body().jsonPath().get("country"), equalTo(country));
        assertThat(responseForGetAPostCodeCall.body().jsonPath().get("'country abbreviation'"), equalTo(cAbbreviation));
        assertThat(responseForGetAPostCodeCall.body().jsonPath().get("places[0].'place name'"), equalTo(pName));
        assertThat(responseForGetAPostCodeCall.body().jsonPath().get("places[0].longitude"), equalTo(longitude));
        assertThat(responseForGetAPostCodeCall.body().jsonPath().get("places[0].state"), equalTo(state));
        assertThat(responseForGetAPostCodeCall.body().jsonPath().get("places[0].'state abbreviation'"), equalTo(sAbbreviation));
        assertThat(responseForGetAPostCodeCall.body().jsonPath().get("places[0].latitude"), equalTo(latitude));

    }

}
