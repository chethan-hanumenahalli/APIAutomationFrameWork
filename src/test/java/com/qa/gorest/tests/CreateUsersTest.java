package com.qa.gorest.tests;

import com.qa.base.BaseTest;
import com.qa.gorest.constants.AuthType;
import com.qa.pojo.User;
import com.qa.gorest.utils.StringUtility;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class CreateUsersTest extends BaseTest {

    @DataProvider
    public Object[][] getUserData() {
        return new Object[][]{
                {"Naveen", "Male", "Active"},
                {"Kohli", "Male", "Active"}
        };
    }

    @Test(dataProvider ="getUserData" )
    public void createUserTest(String name,String gender,String status) {
        // 1.Creating a user - POST
        User user = new User(null, name, StringUtility.getRandomEmailID(), gender,status );
        Response response = restClient.post(BASE_URL_GOREST, "/public/v2/users", user, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
        Assert.assertEquals(response.getStatusCode(), 201);

        //Fetch the User-ID
        String userID = response.jsonPath().getString("id");
        System.out.println(userID);

        // Validating if fresh user is created - GET
        Response responseGet = restClient.get(BASE_URL_GOREST, "/public/v2/users/" + userID, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
        Assert.assertEquals(responseGet.getStatusCode(), 200);
        Assert.assertEquals(responseGet.jsonPath().getString("id"), userID);
        Assert.assertEquals(responseGet.jsonPath().getString("name"), user.getName());
        Assert.assertEquals(responseGet.jsonPath().getString("email"), user.getEmail());
    }


}
