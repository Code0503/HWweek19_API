package com.products.steps;


import com.products.constants.EndPoints;
import com.products.model.ProductsPojo;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import static io.restassured.RestAssured.given;

public class ProductsSteps {
    @Step("create a new product")
    public ValidatableResponse postAddNewProduct(String name,String type, int price,String upc, int shipping,String description, String manufacturer,String model, String url){
        ProductsPojo pj = new ProductsPojo();
        pj.setName(name);
        pj.setType(type);
        pj.setPrice(price);
        pj.setUpc(upc);
        pj.setShipping(shipping);
        pj.setDescription(description);
        pj.setManufacturer(manufacturer);
        pj.setModel(model);
        pj.setUrl(url);

        return SerenityRest .given()
                .header("Content-Type","application/json")
                .body(pj)
                .when()
                .post()
                .then();
    }
    @Step("search product by ID")
    public ValidatableResponse getCreatedProductById(int productID){
        return SerenityRest. given()
                .pathParam("productID",productID)
                .when()
                .get(EndPoints.GET_SINGLE_PRODUCT_BY_ID)
                .then()
                .log().all();
    }
    @Step("update sing details")
    public ValidatableResponse updateCreatedID( int productID,String Newname,String type, int price,String upc, int shipping,String description, String manufacturer,String model, String url){
        ProductsPojo pj = new ProductsPojo();
        pj.setName(Newname);
        return (ValidatableResponse) SerenityRest .given()
                .log().all()
                .header("Content-Type","application/json")
                .pathParams("productID",productID)
                .when()
                .body(pj)
                .patch(EndPoints.UPDATE_PRODUCT_BY_ID)
        .then();
    }
    @Step // PATCH
    public void patchCrestedProductDetails(String model, int productID){
        ProductsPojo pj = new ProductsPojo();
        pj.setModel(model);
        Response response = given()
                .log().all()
                .header("Content-Type","application/json")
                .pathParams("productID",productID)
                .when()
                .body(pj)
                .patch("/{id}");
        response.then().statusCode(200);

    }
    @Step// PUT
    public ValidatableResponse putAndSetChangesInCrestedProductDetails(int productID,String productName,String type, int NewPrice,String upc, int shipping,String descri,String manufac,String NewModel, String url ){
        ProductsPojo pj = new ProductsPojo();
        pj.setName(productName);
        pj.setType(type);
        pj.setPrice(NewPrice);
        pj.setUpc(upc);
        pj.setShipping(shipping);
        pj.setDescription(descri);
        pj.setManufacturer(manufac);
        pj.setModel(NewModel);
        pj.setUrl(url);

        return SerenityRest .given()
                .log().all()
                .header("Content-Type","application/json")
                .pathParam("id",productID)
                .when()
                .body(pj)
                .put(EndPoints.UPDATE_PRODUCT_BY_ID)
       .then();
    }
    @Step//Delete
    public ValidatableResponse delete(int productID){
       return SerenityRest .given()
                .log().all()
                .header("Content-Type", "application/json")
                .pathParam("id",productID )
                .when()
                .delete(EndPoints.DELETE_PRODUCT_BY_ID)
                .then();

    }
}
