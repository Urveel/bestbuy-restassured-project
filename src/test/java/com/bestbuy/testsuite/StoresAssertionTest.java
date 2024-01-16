package com.bestbuy.testsuite;

import com.bestbuy.testbase.TestBase;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class StoresAssertionTest extends TestBase {
    static ValidatableResponse response;                                             //ValidatableResponse is an interface

    @Test
    public void storesAssertMethod()
    {
        response =
                given()
                        .when()
                        .get("stores")
                        .then().statusCode(200);

        //1. Verify  if the total is equal to 1561
        response.body("total" , equalTo(1561));

        // 2. Verify if the stores of limit is equal to 10
        response.body("limit" ,equalTo(10));

        //3. Check the single ‘Name’ in the Array list (Inver Grove Heights)
        response.body("data.name" ,hasItem("Inver Grove Heights"));

        //4. Check the multiple ‘Names’ in the ArrayList (Roseville, Burnsville, Maplewood)
        response.body("data.name" ,hasItems("Roseville" , "Burnsville" , "Maplewood"));

        //5. Verify the storeid=7 inside storeservices of the third index store of second index services
        response.body("data[2].services[3].storeservices.storeId" , equalTo(7));

        //6. Check hash map values ‘createdAt’ inside storeservices map where store name = Roseville
        response.body("data[2].services.storeservices" , everyItem(hasKey("createdAt")));

        //7. Verify the state = MN of forth store
        response.body("data[3].state" , equalTo("MN"));

        //8. Verify the store name = Rochester of 9th store
        response.body("data[8].name" ,equalTo("Rochester"));

        //9. Verify the storeId = 11 for the 6th store
        response.body("data[5].services[4].storeservices.storeId" , equalTo(11));


        //10. Verify the serviceId = 4 for the 7th store of forth service
        response.body("data[6].services[3].storeservices.serviceId" ,equalTo(4) );
    }
    }



