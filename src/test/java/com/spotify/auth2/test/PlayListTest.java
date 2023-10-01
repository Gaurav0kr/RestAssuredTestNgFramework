package com.spotify.auth2.test;

import com.spotify.auth2.api.ApplicationAPI.PlayistAPI;
import com.spotify.auth2.pojo.Error;
import com.spotify.auth2.pojo.Playlist;
import com.spotify.auth2.utils.ConfigLoader;
import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static com.spotify.auth2.api.SpecBuilder.getRequestSpec;
import static com.spotify.auth2.api.SpecBuilder.getResponseSpec;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class PlayListTest {

    public Playlist playlistBuilder(String name, String description, Boolean _public)
    {
        return Playlist.builder().name(name).description(description)._public(_public).build();
       /* Playlist playlist = new Playlist();
        playlist.setName("New Playlist");
        playlist.setDescription("New playlist description");
        playlist.set_public(false);
        return playlist;*/

        /*return new Playlist()
                .setName("New Playlist")
                .setDescription("New playlist description")
                .setPublic(false);*/
    }
    public void assertPlaylistEqual(Playlist requestPlaylist, Playlist responsePlaylist)
    {
        assertThat(responsePlaylist.getName(), equalTo(requestPlaylist.getName()));
        assertThat(responsePlaylist.getDescription(), equalTo(requestPlaylist.getDescription()));
        assertThat(responsePlaylist.get_public(), equalTo(requestPlaylist.get_public()));
    }
public void assertStatusCode(int actualStatusCode, int expectedStatusCode)
{
    assertThat(actualStatusCode,equalTo(expectedStatusCode));
}

    String invalid_token = "aabcd990abba";
   @Description("this is the description")
    @Test(description ="Human-readable test name")
    public void shouldbeAbletoCreatePayList() {
        Playlist requestPlaylist = playlistBuilder("New Playlist","New playlist description",false);
        Response response = PlayistAPI.post(requestPlaylist);
        assertStatusCode(response.statusCode(),201);
        assertPlaylistEqual(requestPlaylist,response.as(Playlist.class));
    }

    @Test
    public void shouldbeAbletoGetaPayList()
    {
        Playlist requestPlaylist = Playlist.builder().build();
        requestPlaylist.setName("New Playlist");
        requestPlaylist.setDescription("New playlist description");
        requestPlaylist.set_public(false);

        Response response = PlayistAPI.get("3QHo2EK1zhrgBhgimVJhRB");
        assertThat(response.statusCode(),equalTo(200));
        Playlist responsePlaylist = response.as(Playlist.class);

        assertThat(responsePlaylist.getName(), equalTo(requestPlaylist.getName()));
        assertThat(responsePlaylist.getDescription(), equalTo(requestPlaylist.getDescription()));
        assertThat(responsePlaylist.get_public(), equalTo(requestPlaylist.get_public()));
                }
    @Test
    public void shouldbeAbletoUpdateaPayList()
    {
        Playlist requestPlaylist = Playlist.builder().build();
        requestPlaylist.setName("New Playlist");
        requestPlaylist.setDescription("New playlist description");
        requestPlaylist.set_public(false);

        Response response = PlayistAPI.update("abc",requestPlaylist);
        assertThat(response.statusCode(),equalTo(200));
        }
    @Test
    public void negative_400_shouldNotbeCreatedPlaylist()
    {
        Playlist requestPlaylist = Playlist.builder().build();
        requestPlaylist.setName("");
        requestPlaylist.setDescription("New playlist description");
        requestPlaylist.set_public(false);

        Response response = PlayistAPI.post(requestPlaylist);
        assertThat(response.statusCode(),equalTo(400));
        Error error = response.as(Error.class);

        assertThat(error.getError().getStatus(),equalTo(400));
        assertThat(error.getError().getMessage(),equalTo("Missing required field: name"));
    }
    @Test
    public void negative_401_shouldNotbeCreatedPlaylist()
    {
        Playlist requestPlaylist = Playlist.builder().build();
        requestPlaylist.setName("New Playlist");
        requestPlaylist.setDescription("New playlist description");
        requestPlaylist.set_public(false);

        Response response = PlayistAPI.post(requestPlaylist,invalid_token);
        assertThat(response.statusCode(),equalTo(401));

        Error error = response.as(Error.class);

        assertThat(error.getError().getStatus(),equalTo(401));
        assertThat(error.getError().getMessage(),equalTo("Invalid access token"));

    }
}
