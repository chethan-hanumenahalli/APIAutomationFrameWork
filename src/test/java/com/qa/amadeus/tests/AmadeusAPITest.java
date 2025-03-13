package com.qa.amadeus.tests;

import com.qa.base.BaseTest;
import com.qa.gorest.constants.AuthType;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

public class AmadeusAPITest extends BaseTest {
    @Test(enabled = false)
    public void getFlightDetailsTest() {

        Map<String, String> queryParams = Map.of("origin", "PAR", "maxPrice", "200");
        Response response = restClient.get(BASE_URL_AMADEUS, "/v1/shopping/flight-destinations", queryParams, null, AuthType.OAUTH2, ContentType.JSON);
        Assert.assertEquals(response.getStatusCode(), 200);
    }

}
