package com.qa.gorest.utils;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;

public class JsonUtility {
    private static ObjectMapper objectMapper = new ObjectMapper();

    public static <T> T deserialize(Response response, Class<T> targetClass) throws JsonProcessingException {
        return objectMapper.readValue(response.getBody().asString(), targetClass);

    }
}
