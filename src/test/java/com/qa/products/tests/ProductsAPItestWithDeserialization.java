package com.qa.products.tests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.qa.base.BaseTest;
import com.qa.gorest.constants.AuthType;
import com.qa.gorest.utils.JsonUtility;
import com.qa.pojo.Product;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;

public class ProductsAPItestWithDeserialization extends BaseTest {

    @Test
    public void getProductsTest() throws JsonProcessingException {
        Response response = restClient.get(BASE_URL_PRODUCTS,
                "/products", null, null, AuthType.NO_AUTH, ContentType.JSON);
        Assert.assertEquals(response.statusCode(), 200);

        Product[] product = JsonUtility.deserialize(response, Product[].class);

        System.out.println(Arrays.toString(product));

        for(Product p : product) {
            System.out.println("ID : " + p.getId());
            System.out.println("Title: " + p.getTitle());
            System.out.println("Price: " + p.getPrice());
            System.out.println("Rate: " + p.getRating());
            System.out.println("------------------------------");
        }
    }
}
