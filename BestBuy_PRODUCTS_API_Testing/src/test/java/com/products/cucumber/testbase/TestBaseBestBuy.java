package com.products.testbase;


import com.products.constants.Path;
import com.products.utils.PropertyReader;
import io.restassured.RestAssured;
import org.junit.BeforeClass;

public class TestBaseBestBuy {
    public static PropertyReader propertyReader;
    @BeforeClass
    public static void inIt() {
        propertyReader = PropertyReader.getInstance();
        RestAssured.baseURI= propertyReader.getProperty("bestBuy.BaseUrl"); // getting localhost from property file
        RestAssured.port= Integer.parseInt(propertyReader.getProperty("bestBuy.Port")); //3030
        RestAssured.basePath = Path.PRODUCTS; // /products
    }
}
