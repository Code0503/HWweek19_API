package com.products.cucumber.steps;

import com.products.productinfo.ProductsSteps;
import com.products.utils.TestUtils;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;

public class ProductStepdefs {
    static ValidatableResponse response;
    static String name1;
    static int productID;
    static int newShipping;
    static String newModel;
    @Steps
    ProductsSteps productsSteps;
    @When("^User get all list of products$")
    public void userGetAllListOfProducts() {
        response= productsSteps.getListOfProducts();
    }

    @Then("^verify status code is \"([^\"]*)\"$")
    public void verifyStatusCodeIs(Integer statusCode)  {
     response.statusCode(statusCode);
    }

    @When("^I create new product detail by name \"([^\"]*)\" type \"([^\"]*)\" price \"([^\"]*)\" upc \"([^\"]*)\" shipping \"([^\"]*)\" description \"([^\"]*)\"  manufacturer \"([^\"]*)\" model \"([^\"]*)\" url \"([^\"]*)\"$")
    public void iCreateNewProductDetailByNameTypePriceUpcShippingDescriptionManufacturerModelUrl(String name, String type, int price, String upc, int shipping, String description, String manufacturer, String model, String url)  {
       name1 =TestUtils.getRandomValue()+ name;
        response= productsSteps.postAddNewProduct(name1,type,price,upc,shipping,description,manufacturer,model,url);
        response.statusCode(201);
        productID =response.extract().path("id");
    }

    @When("^user I getting created product list by ID$")
    public void userIGettingCreatedProductListByID() {
       response=productsSteps.getCreatedProductById(productID);
       response.statusCode(200);
    }

    @When("^I user updating product details by name \"([^\"]*)\" type \"([^\"]*)\" price \"([^\"]*)\" upc \"([^\"]*)\" shipping \"([^\"]*)\" description \"([^\"]*)\"  manufacturer \"([^\"]*)\" model \"([^\"]*)\" url \"([^\"]*)\"$")
    public void iUserUpdatingProductDetailsByNameTypePriceUpcShippingDescriptionManufacturerModelUrl(String name, String type, int price, String upc, int shipping, String description, String manufacturer, String model, String url)  {
     newShipping= Integer.parseInt(shipping+ TestUtils.getRandomValue());
    newModel = model+ TestUtils.getRandomValue();
        response=productsSteps.updateCreatedID(productID,name,type,price,upc,newShipping,description,manufacturer,newModel,url);
        String actualModel=response.extract().path("model");
        Assert.assertEquals("verify by updated model",newModel,actualModel);
    }

    @When("^I delete created product by product id$")
    public void iDeleteCreatedProductByProductId() {
        productsSteps.delete(productID);
    }

    @Then("^try to get created product by ID and Verify status code \"([^\"]*)\"$")
    public void tryToGetCreatedProductByIDAndVerifyStatusCode(int statusCode)  {
        productsSteps.getCreatedProductById(productID).statusCode(statusCode);
    }
}
