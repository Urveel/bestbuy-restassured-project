package com.bestbuy.crudtest;

import com.bestbuy.createmodel.StorePojo;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class StoresCRUDTest {
    @BeforeClass
    public static void  inIt()
    {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        RestAssured.basePath = "/stores";
    }
    private String storeName = "Best Mobile";
    private String storeType = "BigBox";
    private String storeAddress = "B67 TVS Nagar";
    private String storeAddress2 = "Kajamalai";
    private String city = "Inver Grove Heights";
    private String state = "NP";
    private long zipcode = 55131;
    private double lat = 46.01561;
    private int storeId = 21 ;
    private int serviceId = 25;
    private double lng = -92.567438 ;
    private String hours = "Mon: 10-9; Tue: 10-9; Wed: 10-9";

    private long id = 4;
    private String storeServices ;

    @Test
    public void createPost()
    {
        StorePojo storePojo = new StorePojo();
        storePojo.setStoreName(storeName);
        storePojo.setStoreType(storeType);
        storePojo.setStoreAddress(storeAddress);
        storePojo.setStoreAddress2(storeAddress2);
        storePojo.setCity(city);
        storePojo.setState(state);
        storePojo.setZipcode(zipcode);
        storePojo.setLat(lat);
        storePojo.setStoreId(storeId);
        storePojo.setServiceId(serviceId);
        storePojo.setLng(lng);
        storePojo.setHours(hours);
        storePojo.setId(id);

        Response response =
                given()
                        .contentType(ContentType.JSON)
                        .when()
                        .body(storePojo)                                  //pojo is the payload which is body used for Post,Put,Patch
                        .post();
        response.prettyPrint();
        //  id=response.then().extract().path("id");
        // response.then().log().all();
        response.then().statusCode(400);                          //Not found
    }
    @Test
    public void readGetSingleId()
    {
        Response response =
                given()
                        .when()
                        .get("/" + id);                                 //single id endpoint
        response.then().statusCode(200);
    }
    @Test
    public void getStores()                                          //get list of products
    {
        Response response =
                given()
                        .when()
                        .get();
        response.then().statusCode(200);
    }
    @Test
    public void patchUpdateId()
    {
        Response response =
                given()
                        .when()
                        .patch("/" + id);
        response.then().statusCode(200);
    }
    @Test
    public void deleteId()
    {
        given()                                                        //In delete no response
                .when()
                .delete("/" + 15)
                .then()
                .statusCode(404);

        given()
                .when()
                .get("/" + id)
                .then()
                .statusCode(404);
    }
}
