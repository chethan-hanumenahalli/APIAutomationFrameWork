package com.qa.gorest.tests;

import com.qa.base.BaseTest;
import com.qa.pojo.User;
import com.qa.gorest.utils.StringUtility;
import com.qa.gorest.constants.AuthType;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PatchUsersTest extends BaseTest {
    @Test
    public void patchUserTest() {

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
        user.setEmail(StringUtility.getRandomEmailID());

        // 3.Update the same user using the same user id - PATCH
        Response responsePATCH=restClient.patch(BASE_URL_GOREST,"/public/v2/users/"+userID,user,null,null,AuthType.BEARER_TOKEN, ContentType.JSON);
        Assert.assertEquals(responsePATCH.getStatusCode(),200);
        Assert.assertEquals(responsePATCH.jsonPath().getString("email"),user.getEmail());

    }
}
