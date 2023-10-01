package com.spotify.auth2.api;

import com.spotify.auth2.pojo.Playlist;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;

import static com.spotify.auth2.api.Route.API;
import static com.spotify.auth2.api.Route.TOKEN;
import static com.spotify.auth2.api.SpecBuilder.*;
//import static com.spotify.auth2.api.TokenManger.getToken;
import static com.spotify.auth2.api.TokenManger.getToken;
import static com.spotify.auth2.api.TokenManger.renewToken;
import static io.restassured.RestAssured.given;

public class RestResource {
    public static Response post(String path,String token, Object requestPlaylist)
    {
        return given(getRequestSpec())
                .header("Authorization", "Bearer " + token)
                .body(requestPlaylist)
                .when().post(path)
                .then().spec(getResponseSpec())
                .extract().response();
    }
    public static Response postAccount(HashMap<String, String> formParam)
    {
        return  given(getAccountRequestSpec())
            .formParams(formParam)
            .when().post(API+TOKEN)
            .then().spec(getResponseSpec())
            .extract().response();
    }
    public static Response get(String path,String token)
    {
        return given(getRequestSpec())
                .header("Authorization", "Bearer " + token)
                .when().get(path)
                .then().spec(getResponseSpec())
                .assertThat().statusCode(200).extract().response();
    }
    public static Response update(String path,String token, Object requestPlaylist) {
       return given(getRequestSpec())
                .body(requestPlaylist)
               .header("Authorization", "Bearer " + token)
                .when().put(path)
                .then().spec(getResponseSpec()).extract().response();
    }
}
