package com.qa.basicauth.tests;

import com.qa.base.BaseTest;
import com.qa.gorest.constants.AuthType;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BasicAuthTest extends BaseTest {
    @ Test
    public void basicAuthTest() {
        Response response = restClient.get(BASE_URL_BASIC_AUTH, "/basic_auth", null, null, AuthType.BASIC_AUTH, ContentType.ANY);
        Assert.assertEquals(response.asString().contains("Congratulations! You must have the proper credentials."), true);
    }

}
