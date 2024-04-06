package stepDefs;

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
        setApplicationJsonHeader();
        setEndpointPath(ServiceUrl);
        responseForServiceCall = getCall();
        assertThat(responseForServiceCall.statusCode(), equalTo(200));
    }

    @When("I send GET request for a {string} in the {string}")
    @When("I send GET request for an invalid {string} in the {string}")
    public void iSendGETRequestForAInThe(String pCode, String cAbbreviation) {
        setApplicationJsonHeader();
        if (cAbbreviation.equalsIgnoreCase( "GB")) {
            setEndpointPath(postCodeUrl_GB + pCode);
        } else {
            setEndpointPath(postCodeUrl_US + pCode);
        }
        responseForGetAPostCodeCall = getCall();
    }

    @Then("the following details {string}, {string}, {string}, {string}, {string}, {string}, {string} and {string} for the post code are returned in the response body with status code of {int}")
    public void theFollowingDetailsAndForThePostCodeAreReturnedInTheResponseBodyWithStatusCodeOf(String pCode, String country, String cAbbreviation, String pName, String longitude, String state, String sAbbreviation, String latitude, Integer sCode) {
        assertThat(responseForGetAPostCodeCall.statusCode(), equalTo(sCode));
        assertThat(responseForGetAPostCodeCall.body().jsonPath().get("'post code'"), equalTo(pCode));
        assertThat(responseForGetAPostCodeCall.body().jsonPath().get("country"), equalTo(country));
        assertThat(responseForGetAPostCodeCall.body().jsonPath().get("'country abbreviation'"), equalTo(cAbbreviation));
        assertThat(responseForGetAPostCodeCall.body().jsonPath().get("places[0].'place name'"), equalTo(pName));
        assertThat(responseForGetAPostCodeCall.body().jsonPath().get("places[0].longitude"), equalTo(longitude));
        assertThat(responseForGetAPostCodeCall.body().jsonPath().get("places[0].state"), equalTo(state));
        assertThat(responseForGetAPostCodeCall.body().jsonPath().get("places[0].'state abbreviation'"), equalTo(sAbbreviation));
        assertThat(responseForGetAPostCodeCall.body().jsonPath().get("places[0].latitude"), equalTo(latitude));

    }

    @Then("{int} Not found error status code is returned")
    public void notFoundErrorStatusCodeIsReturned(int sCode) {
        assertThat(responseForGetAPostCodeCall.statusCode(), equalTo(sCode));
    }
}
