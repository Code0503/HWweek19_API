package com.studentapp.steps;

import com.studentapp.constants.EndPoints;
import com.studentapp.model.StudentPojo;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;
import java.util.List;

public class StudentSteps {
    @Step ("Create new student data")
    public ValidatableResponse  postCreatNewStudenData(String firstName, String lastName, String email, String programme, List<String> courseList){

        // created student pojo class in main class using encapsulation OPPS concept

        StudentPojo studentPojo = new StudentPojo();
        studentPojo.setFirstName(firstName);
        studentPojo.setLastName(lastName);
        studentPojo.setEmail(email);
        studentPojo.setProgramme(programme);
        studentPojo.setCourses(courseList);

        return SerenityRest . given()
                .header("Content-Type","application/json")
                .body(studentPojo)
                .log().all()
                .when().post().then();
    }
    @Step("Getting response by last name")
    public HashMap<String, Object> getStudentByLastname(String lastName) {

        String s1 = "findAll{it.lastName=='";
        String s2 ="'}.get(0)";
        return SerenityRest. given()
                .when()
                .get(EndPoints.GET_STUDENT_LIST)
                .then()
                .statusCode(200)
                .extract()
                .path(s1 +lastName+ s2);
    }
    @Step("update student  email, And programme")
    public ValidatableResponse updateStudent(int studentID,String firstName, String lastName, String email, String programme, List<String> courseList) {
        StudentPojo studentPojo= new StudentPojo();
        studentPojo.setFirstName(firstName);
        studentPojo.setLastName(lastName);
        studentPojo.setEmail(email);
        studentPojo.setProgramme(programme);
        studentPojo.setCourses(courseList);

        return SerenityRest .given()
                .log().all()
                .header("Content-Type", "application/json")
                .pathParam("studentID",studentID)
                .body(studentPojo)
                .when()
                .put(EndPoints.UPDATE_STUDENT_BY_ID)
                .then();
    }


    @ Step("delete student by ID")
    public ValidatableResponse deleteStudent(int studentID){
        return SerenityRest. given()
                .header("Content-Type", "application/json")
                .pathParam("studentID",studentID)
                .when()
                .delete(EndPoints.DELETE_STUDENT_BY_ID)
                .then();
    }

    public ValidatableResponse getStudentByID(int studentID){

        return SerenityRest.given()
                .pathParam("studentID",studentID)
                .when()
                .get(EndPoints.GET_STUDENT_BY_ID)
                .then()
                .log().all();
    }
}
