package com.spotify.auth2.api.ApplicationAPI;

import com.spotify.auth2.api.RestResource;
import com.spotify.auth2.pojo.Playlist;
import com.spotify.auth2.utils.ConfigLoader;
import io.restassured.response.Response;

import static com.spotify.auth2.api.Route.PLAYLISTS;
import static com.spotify.auth2.api.Route.USERS;
import static com.spotify.auth2.api.SpecBuilder.getRequestSpec;
import static com.spotify.auth2.api.SpecBuilder.getResponseSpec;
//import static com.spotify.auth2.api.TokenManger.getToken;
import static com.spotify.auth2.api.TokenManger.getToken;
import static com.spotify.auth2.api.TokenManger.renewToken;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class PlayistAPI {

   // static String playListPath= "/playlists/3QHo2EK1zhrgBhgimVJhRB";
   // static String userpath =  "/users/313s3e7hxzppjfkijzlcbq4labye/playlists";
    public static Response post(Playlist requestPlaylist)
    {
        return RestResource.post(USERS+"/"+ "313s3e7hxzppjfkijzlcbq4labye" +PLAYLISTS,getToken(),requestPlaylist);
    }
    public static Response post(Playlist requestPlaylist,String token)
    {
        return RestResource.post(USERS+"/"+ ConfigLoader.getInstance().getuser_id()+PLAYLISTS,token,requestPlaylist);
    }
    public static Response get( String playListId)
    {
        return RestResource.get(PLAYLISTS+"/"+"3QHo2EK1zhrgBhgimVJhRB",getToken());
    }

    public static Response update(String playListId, Playlist requestPlaylist) {
        return RestResource.update(PLAYLISTS+"/"+"3QHo2EK1zhrgBhgimVJhRB",getToken(),requestPlaylist);
    }
}
