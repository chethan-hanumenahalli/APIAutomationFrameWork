package com.qa.schemavalidator.tests;

import com.qa.base.BaseTest;
import com.qa.gorest.constants.AuthType;
import com.qa.gorest.utils.SchemaValidator;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProductAPIsSchemaTest extends BaseTest {

    @Test
    public void productAPISchemaValidate() {
        Response response = restClient.get(BASE_URL_PRODUCTS, "/products", null, null, AuthType.NO_AUTH, ContentType.ANY);
        Assert.assertTrue(SchemaValidator.validateSchema(response, "schema/product-schema.json"));
    }


}
