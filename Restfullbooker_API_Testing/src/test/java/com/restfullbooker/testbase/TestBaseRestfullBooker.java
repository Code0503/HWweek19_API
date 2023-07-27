package com.restfullbooker.testbase;


import com.restfullbooker.constants.Path;
import com.restfullbooker.utils.PropertyReader;
import io.restassured.RestAssured;
import org.junit.BeforeClass;

public class TestBaseRestfullBooker {
    public static PropertyReader propertyReader;
    @BeforeClass
    public static void inIt() {
        propertyReader = PropertyReader.getInstance();
        RestAssured.baseURI= propertyReader.getProperty("restfullBooker.BaseUrl"); // getting localhost from property file
        RestAssured.basePath = Path.BOOKING; // /booking
    }
}
