package com.qa.gorest.tests;

import com.qa.base.BaseTest;
import com.qa.gorest.constants.AuthType;
import com.qa.pojo.User;
import com.qa.gorest.utils.StringUtility;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UpdateUsersTest extends BaseTest {
    @Test
    public void updateUserTest() {

        // 1.Creating a fresh user - POST
        User user = new User(null,"New User", StringUtility.getRandomEmailID(), "male", "active");
        Response responsePOST = restClient.post(BASE_URL_GOREST,"/public/v2/users", user, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
        Assert.assertEquals(responsePOST.getStatusCode(), 201);

        //Fetch the User-ID
        String userID=responsePOST.jsonPath().getString("id");
        System.out.println(userID);

        //2.Validate the same user created above exists - GET
        Response responseGET=restClient.get(BASE_URL_GOREST,"/public/v2/users/"+userID,null,null,AuthType.BEARER_TOKEN, ContentType.JSON);
        Assert.assertEquals(responseGET.getStatusCode(),200);
        Assert.assertEquals(responseGET.jsonPath().getString("id"),userID);

        //Update the user details using the setter
        user.setStatus("inactive");
        user.setGender("female");

        // 3.Update the same user using the same user id - PUT
        Response responsePUT=restClient.put(BASE_URL_GOREST,"/public/v2/users/"+userID,user,null,null,AuthType.BEARER_TOKEN, ContentType.JSON);
        Assert.assertEquals(responsePUT.getStatusCode(),200);
        Assert.assertEquals(responsePUT.jsonPath().getString("id"),userID);
        Assert.assertEquals(responsePUT.jsonPath().getString("status"),user.getStatus());
        Assert.assertEquals(responsePUT.jsonPath().getString("gender"),user.getGender());

    }
}
