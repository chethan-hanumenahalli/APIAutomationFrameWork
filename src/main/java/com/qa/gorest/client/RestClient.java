package com.qa.gorest.client;

import com.qa.gorest.constants.AuthType;
import com.qa.gorest.exceptions.FrameWorkException;
import com.qa.gorest.manager.ConfigManager;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.util.Base64;
import java.util.Map;

import static io.restassured.RestAssured.expect;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.equalTo;


public class RestClient {

    //define Response Specs:
    private ResponseSpecification responseSpecification200 = expect().statusCode(200);
    private ResponseSpecification responseSpecification200or201 = expect().statusCode(anyOf(equalTo(200), equalTo(201)));

    private ResponseSpecification responseSpecification200or404 = expect().statusCode(anyOf(equalTo(200), equalTo(404)));
    private ResponseSpecification responseSpecification201 = expect().statusCode(201);
    private ResponseSpecification responseSpecification204 = expect().statusCode(204);
    private ResponseSpecification responseSpecification400 = expect().statusCode(400);
    private ResponseSpecification responseSpecification401 = expect().statusCode(401);
    private ResponseSpecification responseSpecification404 = expect().statusCode(404);
    private ResponseSpecification responseSpecification500 = expect().statusCode(500);
    // private String baseURL = ConfigManager.get("baseURL");


//******************************CRUD METHODS******************************//

    public Response get(String baseUrl, String endPoint, Map<String, String> queryParams, Map<String, String> pathParams, AuthType authType, ContentType contentType) {
        RequestSpecification request = setUpAuthAndContentType(baseUrl, authType, contentType);
        applyParams(request, queryParams, pathParams);
        Response response = request.get(endPoint).then().spec(responseSpecification200or404).extract().response();
        response.prettyPrint();
        return response;
    }

    public <T> Response post(String baseUrl, String endPoint, T body, Map<String, String> queryParams, Map<String, String> pathParams, AuthType authType, ContentType contentType) {
        RequestSpecification request = setUpAuthAndContentType(baseUrl, authType, contentType);
        applyParams(request, queryParams, pathParams);
        Response response = request.body(body).post(endPoint).then().spec(responseSpecification200or201).extract().response();
        response.prettyPrint();
        return response;
    }

    public <T> Response put(String baseUrl, String endPoint, T body, Map<String, String> queryParams, Map<String, String> pathParams, AuthType authType, ContentType contentType) {
        RequestSpecification request = setUpAuthAndContentType(baseUrl, authType, contentType);
        applyParams(request, queryParams, pathParams);
        Response response = request.body(body).put(endPoint).then().spec(responseSpecification200).extract().response();
        response.prettyPrint();
        return response;
    }

    public <T> Response patch(String baseUrl, String endPoint, T body, Map<String, String> queryParams, Map<String, String> pathParams, AuthType authType, ContentType contentType) {
        RequestSpecification request = setUpAuthAndContentType(baseUrl, authType, contentType);
        applyParams(request, queryParams, pathParams);
        Response response = request.body(body).patch(endPoint).then().spec(responseSpecification200).extract().response();
        response.prettyPrint();
        return response;
    }

    public Response delete(String baseUrl, String endPoint, Map<String, String> queryParams, Map<String, String> pathParams, AuthType authType, ContentType contentType) {
        RequestSpecification request = setUpAuthAndContentType(baseUrl, authType, contentType);
        applyParams(request, queryParams, pathParams);
        Response response = request.delete(endPoint).then().spec(responseSpecification204).extract().response();
        response.prettyPrint();
        return response;
    }


    //******************************CRUD METHODS******************************//

    // Method to set up the request
    private RequestSpecification setUpRequest(String baseUrl, AuthType authType, ContentType contentType) {
        RequestSpecification request = RestAssured.given().log().all()
                .baseUri(baseUrl)
                .contentType(contentType)
                .accept(contentType);
        switch (authType) {
            case BEARER_TOKEN:
                request.header("Authorization", "Bearer " + ConfigManager.get("bearerToken"));
                break;
            case CONTACTS_BEARER_TOKEN:
                request.header("Authorization", "Bearer " + ConfigManager.get("contacts_bearer_token"));
                break;
            case OAUTH2:
                request.header("Authorization", "Bearer " + generateOAuth2Token());
                break;
            case BASIC_AUTH:
                request.header("Authorization", "Basic "+ generateBasicAuthToken());
                break;
            case API_KEY:
                request.header("x-api-key", ConfigManager.get("apiKey"));
                break;
            case NO_AUTH:
                System.out.println("Auth is not required");
                break;
            default:
                System.out.println("Auth is not supported,please pass the right AuthType");
                throw new FrameWorkException("NO AUTH SUPPORTED");
        }
        return request;

    }

    // Method to generate OAuth2 Token
    private String generateOAuth2Token() {
        return RestAssured.given()
                .formParam("client_id", ConfigManager.get("clientId"))
                .formParam("client_secret", ConfigManager.get("clientSecret"))
                .formParam("grant_type", ConfigManager.get("grantType"))
                .post(ConfigManager.get("tokenUrl"))
                .then()
                .extract()
                .path("access_token");
    }

    private String generateBasicAuthToken() {
        String credentials = ConfigManager.get("basicUsername") + ":" + ConfigManager.get("basicPassword");    // admin:admin
        byte[] credentialsInBytes = credentials.getBytes();     //Because encode() method accepts the parameter only in byte array format
        return Base64.getEncoder().encodeToString(credentialsInBytes);
    }


    private RequestSpecification setUpAuthAndContentType(String baseUrl, AuthType authType, ContentType contentType) {
        return setUpRequest(baseUrl, authType, contentType);
    }

    private void applyParams(RequestSpecification request, Map<String, String> queryParams, Map<String, String> pathParams) {
        if (queryParams != null) {
            request.queryParams(queryParams);
        }
        if (pathParams != null) {
            request.pathParams(pathParams);
        }
    }

}
