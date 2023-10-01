package com.spotify.auth2.api;

import com.spotify.auth2.utils.ConfigLoader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.time.Instant;
import java.util.HashMap;

import static com.spotify.auth2.api.SpecBuilder.getResponseSpec;
import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;

public class TokenManger {

    private static String access_token;
   private static Instant  expiry_time;
    public static String getToken()
    {
        try{
            if(access_token == null || Instant.now().isAfter(expiry_time))
            {
                Response response = renewToken();
                access_token = response.path("access_token");
                int expiryDurationInSecond = response.path("expires_in");
                expiry_time = Instant.now().plusSeconds(expiryDurationInSecond -300);
            }
           else
               System.out.println("Token is good to use");
        }
        catch (Exception e)
        {
throw new RuntimeException("Abort !!! Failed to get token");
        }
        return access_token;
    }
    public static Response renewToken()
    {
        HashMap<String,String> formParam = new HashMap<>();
/*        formParam.put("grant_type", ConfigLoader.getInstance().getgrant_type());
        formParam.put("refresh_token",ConfigLoader.getInstance().getrefresh_token());
        formParam.put("client_id",ConfigLoader.getInstance().getClientId());
        formParam.put("client_secret",ConfigLoader.getInstance().getclient_secret());*/
               formParam.put("grant_type", "refresh_token");
        formParam.put("refresh_token","AQCYdBg7akSwUvzQep2q3QewKLj7WS6uEZmR7EalvT6epLv293GFMnKjgQBooYL7W1dih_UiUZN25xINRgj2WqqGS-coKbTArNQDSjsSwuF_LZzN0TwVzgY47bVfz4ilLfE");
        formParam.put("client_id","e1e3a453ee4747d9bba24551b4a4032e");
        formParam.put("client_secret","c612d543bd9f44d58463bf3c9bc34a74");
       Response response =RestResource.postAccount(formParam);

       if(response.getStatusCode() !=200)
           throw new RuntimeException("Abort !!! Renew token Failed");
       return response;
    }
}
