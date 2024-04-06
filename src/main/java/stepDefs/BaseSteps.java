package stepDefs;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

import java.net.MalformedURLException;
import java.net.URL;


public class BaseSteps {
    public Headers headers;
    private String endpointPath;
    private Response response;
    String ServiceUrl, postCodeUrl_GB,postCodeUrl_US;

    public BaseSteps() {
        ServiceUrl = "https://api.zippopotam.us";
        postCodeUrl_GB = ServiceUrl + "/GB/";
        postCodeUrl_US = ServiceUrl + "/US/";
    }

    public void setHeaders(Headers value) {
        restHeaders();
        headers = value;
    }

    private void restHeaders() {
        headers = null;
    }

    public void setApplicationJsonHeader() {
        headers = new Headers(
                new Header("Content-Type", "application/json"),
                new Header("Accept", "application/json"));
        setHeaders(headers);
    }

    protected URL getURL() {
        try {
            return new URL(endpointPath);
        } catch (MalformedURLException e) {
            throw new RuntimeException();
        }
    }

    public Response getCall() {
        response = RestAssured.given().log().all()
                .headers(headers)
                .when().get(getURL())
                .then().log().all().extract().response();
        return response;
    }

    public void setEndpointPath(String endpointPath) {
        this.endpointPath = endpointPath;
    }


}


