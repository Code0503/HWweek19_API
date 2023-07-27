package com.restfullbooker.steps;

import com.restfullbooker.restfullinfo.RestfullBookerSteps;
import com.restfullbooker.utils.TestUtils;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;

import java.util.HashMap;

public class BookingStepdefs {
    static ValidatableResponse response;
    static String token;
    static String firstname1;
    static String Firstname;
    static int bookingID;
    static HashMap<String,Object> BookingDetails;

    @Steps
    RestfullBookerSteps restfullBookerSteps;
    @When("^I create new token with username \"([^\"]*)\" password \"([^\"]*)\"$")
    public void iCreateNewTokenWithUsernamePassword(String username, String password)  {
     response= restfullBookerSteps.creatingToken(username,password);
        token = response.extract().path("token");
        System.out.println("Token : "+ token);
    }

    @Then("^User must get back a valid status code \"([^\"]*)\"$")
    public void userMustGetBackAValidStatusCode(int statusCode)  {
        response.statusCode(statusCode);
    }

    @When("^User get all booking$")
    public void userGetAllBooking() {
        response = restfullBookerSteps.getAllBookings();
    }

    @When("^as user creating new booking with information  firstname \"([^\"]*)\" lastname \"([^\"]*)\" totalPrice \"([^\"]*)\" depositPaid\"([^\"]*)\"  additionalneeds \"([^\"]*)\"$")
    public void asUserCreatingNewBookingWithInformationFirstnameLastnameTotalPriceDepositPaidAdditionalneeds(String firstname, String lastname, int totalprice, boolean depositpaid, String additionalneeds)  {
        HashMap<String,Object> bookingdates = new HashMap<>();
        bookingdates.put("checkin", "2023-11-05");
        bookingdates.put("checkout", "2024-01-12");
        firstname1=TestUtils.getRandomValue() +firstname;
        response=restfullBookerSteps.creatingNewBooking(firstname1,lastname,totalprice,depositpaid,bookingdates,additionalneeds);
        response.statusCode(200);
        bookingID= response.extract().path("bookingid");
        System.out.println(bookingID);
    }

    @Then("^verify created booking By id$")
    public void verifyCreatedBookingById() {
        BookingDetails = restfullBookerSteps.getCreatedBookingByID(bookingID);
        System.out.println(BookingDetails);
    }

    @When("^as user I update created booking with information  firstname \"([^\"]*)\" lastname \"([^\"]*)\" totalPrice \"([^\"]*)\" depositPaid\"([^\"]*)\"  additionalneeds \"([^\"]*)\"$")
    public void asUserIUpdateCreatedBookingWithInformationFirstnameLastnameTotalPriceDepositPaidAdditionalneeds(String firstname, String lastname, int totalprice, boolean depositpaid, String additionalneeds) {
        HashMap<String,Object> bookingdates = new HashMap<>();
        bookingdates.put("checkin", "2023-12-05");
        bookingdates.put("checkout", "2024-02-12");
       response= restfullBookerSteps.updatedBookingByID(token,bookingID,firstname,lastname,totalprice,depositpaid,bookingdates,additionalneeds);
        Firstname =response.extract().path("firstname");
        System.out.println(Firstname);
    }

    @When("^as user I deleting created booking by id$")
    public void asUserIDeletingCreatedBookingById() {
        response=restfullBookerSteps.deleteCreatedBooking(bookingID);
        response.statusCode(201);

    }
}
