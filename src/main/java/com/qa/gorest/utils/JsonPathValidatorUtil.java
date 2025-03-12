package com.qa.gorest.utils;

import java.util.List;
import java.util.Map;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;

import io.restassured.response.Response;

public class JsonPathValidatorUtil {


    public static <T> List<T> readList(Response response, String jsonPath) {
        String jsonResponse = response.getBody().asString();
        ReadContext ctx = JsonPath.parse(jsonResponse);
        return ctx.read(jsonPath);
    }

    public static <T> List<Map<String, T>> readListOfMaps(Response response, String jsonPath) {
        String jsonResponse = response.getBody().asString();
        ReadContext ctx = JsonPath.parse(jsonResponse);
        return ctx.read(jsonPath);
    }
}
