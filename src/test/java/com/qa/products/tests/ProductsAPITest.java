package com.qa.products.tests;

import com.qa.base.BaseTest;


import com.qa.gorest.constants.AuthType;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

public class ProductsAPITest extends BaseTest {

    @Test
    public void getProductsAPITest() {
        Response response = restClient.get(BASE_URL_PRODUCTS,"/products", null, null, AuthType.NO_AUTH, ContentType.JSON);
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test
    public void getProductsAPILimitTest() {
        Map<String, String> queryParam = Map.of("limit", "5");
        Response response = restClient.get(BASE_URL_PRODUCTS,"/products", queryParam, null, AuthType.NO_AUTH, ContentType.JSON);
        Assert.assertEquals(response.statusCode(), 200);
    }
}
