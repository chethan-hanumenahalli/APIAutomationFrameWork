package com.qa.gorest.utils;

import io.restassured.response.Response;
import io.restassured.module.jsv.JsonSchemaValidator;


    public class SchemaValidator {

        public static boolean validateSchema(Response response, String schemaFileName) {

            try {
                response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(schemaFileName));
                System.out.println("Schema validation passed");
                return true;
            } catch (Exception e) {
                System.out.println("Schema validation failed " + e.getMessage());
                return false;
            }
        }
    }


