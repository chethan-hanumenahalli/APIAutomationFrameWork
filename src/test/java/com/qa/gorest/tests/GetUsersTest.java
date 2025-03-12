package com.qa.gorest.tests;

import com.qa.base.BaseTest;
import com.qa.gorest.constants.AuthType;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class GetUsersTest extends BaseTest {

    @Test
    public void getAllUsersTest() {
        Map<String, String> queryParams = new HashMap<>();
        Response response = restClient.get(BASE_URL_GOREST,"/public/v2/users", null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(enabled = false)
    public void getSingleUserTest() {
        Map<String, String> queryParams = new HashMap<>();
        Response response = restClient.get(BASE_URL_GOREST,"/public/v2/users/7728341", null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
        Assert.assertEquals(response.getStatusCode(), 200);
    }

}
