package com.restfullbooker.restfullinfo;


import com.restfullbooker.constants.EndPoints;
import com.restfullbooker.constants.Path;
import com.restfullbooker.model.RestfulTokenPojo;
import com.restfullbooker.model.RestfullBookerPojo;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;

public class RestfullBookerSteps {
    static int idNumber;


    @Step("create token")
    public ValidatableResponse creatingToken(String username, String password) {

        RestfulTokenPojo tokenPojo = new RestfulTokenPojo();

        tokenPojo.setUsername(username);
        tokenPojo.setPassword(password);

        return SerenityRest.given()
                .log().all()
                .header("Content-Type", "application/json")
                .basePath("/auth")
                .when()
                .body(tokenPojo)
                .post().then().log().all();
    }

    @Step("get all bookings")
    public ValidatableResponse getAllBookings() {
        return SerenityRest.given()
                .basePath(Path.BOOKING)
                .when()
                .get()
                .then();
    }

    @Step("create a new booking")
    public ValidatableResponse creatingNewBooking(String firstname, String lastname, int totalprice, Boolean depositpaid, HashMap<String, Object> bookingdates, String additionalneeds) {
        RestfullBookerPojo bookerPojo = new RestfullBookerPojo();
        bookerPojo.setFirstname(firstname);
        bookerPojo.setLastname(lastname);
        bookerPojo.setTotalprice(totalprice);
        bookerPojo.setDepositpaid(depositpaid);
        bookerPojo.setBookingdates(bookingdates);
        bookerPojo.setAdditionalneeds(additionalneeds);

        return SerenityRest.given()
                .log().all()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .body(bookerPojo)
                .when()
                .post().then().log().all();
    }
    @Step("Getting response by email")
    public HashMap<String, Object> getStudentByfirstName(String firstname) {
        String p1 = "findAll{it.firstname=='";
        String p2 = "'}.get(0)";
        return SerenityRest.given().log().all()
                .when()
                .get(EndPoints.By_BookinID)
                .then()
                .extract()
                .path(p1 + firstname + p2);
    }
    @Step("get booking by id")
    public HashMap<String, Object>  getCreatedBookingByID(int bookingid){
        return SerenityRest.given()
                .pathParam("bookingid",bookingid)
                .when()
                .get("/{bookingid}")
                .then()
                .extract()
                .path("bookingid", String.valueOf(bookingid));
    }
    @Step("update booking id")
    public ValidatableResponse updatedBookingByID(String token,int bookingid,String firstname, String lastname, int totalprice, Boolean depositpaid, HashMap<String, Object> bookingdates, String additionalneeds){
        RestfullBookerPojo bookerPojo = new RestfullBookerPojo();
        bookerPojo.setFirstname(firstname);
        bookerPojo.setLastname(lastname);
        bookerPojo.setTotalprice(totalprice);
        bookerPojo.setDepositpaid(depositpaid);
        bookerPojo.setBookingdates(bookingdates);
        bookerPojo.setAdditionalneeds(additionalneeds);

        return SerenityRest .given()
                .log().all()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization","Basic YWRtaW46cGFzc3dvcmQxMjM=")
                .body(bookerPojo)
                .when()
                .put(EndPoints.UPDATE_PRODUCT_BY_ID,bookingid).then().log().all();
    }
    @Step("delete created booking by id")
    public ValidatableResponse deleteCreatedBooking(int bookingid){
        return SerenityRest.given()
                .header("Content-Type", "application/json")
                .header("Authorization","Basic YWRtaW46cGFzc3dvcmQxMjM=")
                .pathParam("bookingid",bookingid)
                .when()
                .delete("/{bookingid}").then();
    }
}
