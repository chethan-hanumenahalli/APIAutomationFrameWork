package com.qa.base;

import com.qa.gorest.client.RestClient;
import com.qa.gorest.manager.ConfigManager;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseTest {

    protected final static String BASE_URL_REQ_RES = "https://reqres.in";
    protected final static String BASE_URL_CONTACTS = "https://thinking-tester-contact-list.herokuapp.com";
    protected final static String BASE_URL_PRODUCTS = "https://fakestoreapi.com";
    protected final static String BASE_URL_GOREST = "https://gorest.co.in";
    protected final static String BASE_URL_BASIC_AUTH = "https://the-internet.herokuapp.com";
    protected final static String BASE_URL_AMADEUS = "https://test.api.amadeus.com";


    protected RestClient restClient;


    @BeforeTest
    public void setUp() {
        RestAssured.filters(new AllureRestAssured());
        restClient = new RestClient();
    }
}
