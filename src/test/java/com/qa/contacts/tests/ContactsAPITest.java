package com.qa.contacts.tests;

import com.qa.base.BaseTest;
import com.qa.gorest.constants.AuthType;
import com.qa.gorest.manager.ConfigManager;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import com.qa.pojo.ContactsCredentials;
import org.testng.annotations.Test;

public class ContactsAPITest extends BaseTest {
    private String tokenId;

    @BeforeMethod
    public void getToken() {
        ContactsCredentials creds=ContactsCredentials.builder()
                .email("naveenanimation20@gmail.com")
                .password("test@123")
                .build();

        Response response=restClient.post(BASE_URL_CONTACTS,"/users/login",creds,null,null, AuthType.NO_AUTH, ContentType.JSON);
        tokenId=response.jsonPath().getString("token");
        System.out.println("Token ID: "+tokenId);
        ConfigManager.set("contacts_bearer_token",tokenId);
    }

    @Test
    public void getContactsTest()
    {
       Response response= restClient.get(BASE_URL_CONTACTS,"/contacts",null,null,AuthType.CONTACTS_BEARER_TOKEN,ContentType.JSON);
        Assert.assertEquals(response.getStatusCode(),200);
    }

}
