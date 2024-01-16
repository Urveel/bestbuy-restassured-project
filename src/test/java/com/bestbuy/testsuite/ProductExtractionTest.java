package com.bestbuy.testsuite;

import com.bestbuy.testbase.TestBase;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class ProductExtractionTest extends TestBase {
    static ValidatableResponse response;

    @Test
    public void productsExtraction()
    {
        response =
                given()
                        .when()
                        .get("products")                                         //with or without /stores or just stores working
                        .then().statusCode(200);

//        21. Extract the limit
        int limit =  response.extract().path("limit");
        System.out.println("The limit of the data is : " + limit);

//        22. Extract the total
        int total = response.extract().path("total");
        System.out.println("Total of data is : " +total);

//        23. Extract the name of 5th product
        String name = response.extract().path("data[4].name");
        System.out.println("Name of the fifth product : " + name);


//        24. Extract the names of all the products
        List<String> namesOfAllProducts =  response.extract().path("data.name");
        System.out.println("Names of all products : " +  namesOfAllProducts);

//        25. Extract the productId of all the products
        List<Integer> productIdOfAllProducts =  response.extract().path("data.id");
        System.out.println("ProductId of all products : " + productIdOfAllProducts);

//        26. Print the size of the data list
        List<String> dataList  = response.extract().path("data");
        System.out.println("Size of the data list is : " + dataList.size());

//        27. Get all the value of the product where product name = Energizer - MAX Batteries AA (4-
//            Pack)
        List<String> allValues =  response.extract().path("data.findAll{it.name == 'Energizer - MAX Batteries AA (4-Pack)'}");
        System.out.println("All values of the product : " + allValues);

//        28. Get the model of the product where product name = Energizer - N Cell E90 Batteries (2-
//            Pack)
        List<String> model =  response.extract().path("data.findAll{it.name == 'Energizer - N Cell E90 Batteries (2-Pack)'}.model");
        System.out.println("Model of the product : " + model);                           //model is String but present inside List hence ArrayList

//        29. Get all the categories of 8th products
        List<String> categoriesOf8thProduct =  response.extract().path("data[7].categories");
        System.out.println("Categoriers of 8th product are :" + categoriesOf8thProduct);

//        30. Get categories of the store where product id = 150115
        List<String> categoriesOfTheStore = response.extract().path("data[3].categories");
        System.out.println("Categories of the store : " +categoriesOfTheStore );
        //{it.id == '150115'}  IllegalArgumentException: Invalid JSON expression: groovy: 1: Unexpected input: Do numerals without ' '

//        31. Get all the descriptions of all the products
        List<String> allDescriptions =  response.extract().path("data.description");
        System.out.println("Descriptions of all products : " +allDescriptions);

//        32. Get id of all the all categories of all the products
        List<Integer> idOfAllCategories =  response.extract().path("data.id");
        System.out.println("Id of all categories : " +idOfAllCategories);

//        33. Find the product names Where type = HardGood
        List<String> productNames = response.extract().path("data.findAll{it.type == 'HardGood'}.name");              //{it.type}
        System.out.println("Product names are : " +productNames);

//        34. Find the Total number of categories for the product where product name = Duracell - AA
//        1.5V CopperTop Batteries (4-Pack)
        List<String> categoriesOfDuracellCopper = response.extract().path("data[1].categories");
        System.out.println("Total number of categories : " +categoriesOfDuracellCopper.size());

//        35. Find the createdAt for all products whose price < 5.49
        List<String> createdAtForAllProducts = response.extract().path("data.findAll{it.price < 5.49}.findAll{it.createdAt}.createdAt");
        System.out.println("CreatedAt for all products : " +  createdAtForAllProducts);

//        36. Find the name of all categories Where product name = “Energizer - MAX Batteries AA (4-
//            Pack)”
        List<String> nameOfAllCategories = response.extract().path("data.findAll{it.name == 'Energizer - MAX Batteries AA (4-Pack)'}.categories.name");
        System.out.println("Name of all categories : " +nameOfAllCategories);

//        37. Find the manufacturer of all the products
        List<String> manufacturerOfAllProducts = response.extract().path("data.manufacturer");
        System.out.println("Manufacturer of all products : " + manufacturerOfAllProducts);


//        38. Find the image of products whose manufacturer is = Energizer
        List<String> imageOfAllProducts =  response.extract().path("data.findAll{it.manufacturer == 'Energizer'}.image");
        System.out.println("Image of all products : " + imageOfAllProducts);

//        39. Find the createdAt for all categories products whose price > 5.99
        List<String> createdAtForAllCategories =  response.extract().path("data.findAll{it.price > 5.99 }.findAll{it.createdAt}.createdAt");
        System.out.println("CreatedAt for all categories : " +createdAtForAllCategories);

//        40. Find the url of all the products
        List<String> urlOfAllProducts =  response.extract().path("data.url");
        System.out.println("Url of all products : " + urlOfAllProducts);

    }

}


