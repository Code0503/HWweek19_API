package com.studentapp.constants;

public class EndPoints {
    //Below is STUDENT APP EndPoints
    public static final String GET_STUDENT_LIST = "/list";
    public static final String GET_STUDENT_BY_ID = "/{studentID}";
    public static final String UPDATE_STUDENT_BY_ID = "/{studentID}";
    public static final String DELETE_STUDENT_BY_ID = "/{studentID}";
    public static final String FIRST_NAME = "firstName";
    public static final String Accepted = "200";

    //Below is BestBuy PRODUCTS  EndPoints
    public static final String GET_SINGLE_PRODUCT_BY_ID = "/{productID}";
    public static final String UPDATE_PRODUCT_BY_ID = "/{productID}.json";
    public static final String DELETE_PRODUCT_BY_ID = "/{productID}";
}
