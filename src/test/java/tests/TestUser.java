package tests;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class TestUser {

    @Test
    public void getListTest(){

        Map <String,String>headers= new HashMap<>();
        headers.put("app-id","64727de75bbbf42d4b35b287");
        Response response=given()
                .baseUri("https://dummyapi.io/data/")
                .basePath("/v1")
                .headers(headers)
                .log().all()
                .when().get("post");

        System.out.println("Status code iz response je: " + response.getStatusCode());
        Assert.assertEquals(response.getStatusCode(), 200);

        System.out.println("JsonPath Evoluaton za polje text je: " + response.jsonPath().get("data[0].tags[0]"));
        Assert.assertEquals(response.jsonPath().get("data[0].tags[0]"),"animal");
    }
    @Test
    public void getListByUserTest(){

        Map<String,String> headers= new HashMap<>();
        headers.put("app-id","64727de75bbbf42d4b35b287");
        Response response =given()
                .baseUri("https://dummyapi.io/data/")
               .basePath("v1/")
                .pathParam("id","60d0fe4f5311236168a109ca")
                .headers(headers)
                .log().all()
                .when().get("user/{id}/post");

        Assert.assertEquals(response.getStatusCode(),200);
        Assert.assertEquals(response.jsonPath().get("data[0].likes"),"45");

    }
    @Test
    public void getListByTagTest(){

        Map<String,String> headers= new HashMap<>();
        headers.put("app-id","64727de75bbbf42d4b35b287");
        Response response =given()
                .baseUri("https://dummyapi.io/data/")
                .basePath("v1/")
                .pathParam("id","dog")
                .headers(headers)
                .log().all()
                .when().get("tag/dog/post");

        Assert.assertEquals(response.getStatusCode(),200);
        Assert.assertEquals(response.jsonPath().get("data[0].likes"),"45");

    }
    @Test
    public void getPostByIdTest(){

        Map<String,String> headers= new HashMap<>();
        headers.put("app-id","64727de75bbbf42d4b35b287");
        Response response =given()
                .baseUri("https://dummyapi.io/data/")
                .basePath("v1/")
                .pathParam("id","6473c8056bb6233ea4cfdb54")
                .headers(headers)
                .log().all()
                .when().get("post/{id}");
        System.out.println("Status code iz response je: " + response.getStatusCode());
        Assert.assertEquals(response.getStatusCode(),200);

        System.out.println("JsonPath Evoluaton za polje u tag-u je: " + response.jsonPath().get("tags[0]"));
        Assert.assertEquals(response.jsonPath().get("tags[0]"),"animal");
    }

}

