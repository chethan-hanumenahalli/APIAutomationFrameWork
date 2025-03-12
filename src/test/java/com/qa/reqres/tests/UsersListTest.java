package com.qa.reqres.tests;

import com.qa.base.BaseTest;
import com.qa.gorest.constants.AuthType;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UsersListTest extends BaseTest {

    @Test
    public void getSingleUserTest() {
        Response response = restClient.get(BASE_URL_REQ_RES,"/api/users?page=2", null, null, AuthType.NO_AUTH, ContentType.JSON);
        Assert.assertEquals(response.getStatusCode(), 200);
    }

}
