package com.hrmanagement.constant;

public class ApiUrls {
    public static final String VERSION = "api/v1";
    public static final String AUTH = VERSION + "/auth";

    //AuthController
    public static final String REGISTER = "/register";
    public static final String REGISTER_MANAGER = "/register-manager";
    public static final String LOGIN = "/login";
    public static final String UPDATE = "/update";
    public static final String ACTIVATE_STATUS = "/activate-status";


    public static final String ACTIVATE_STATUS_MANAGER = "/activate-status/{id}";
    public static final String UPDATE_EMPLOYEE = "/update-employee";
    public static final String CREATE_ADMIN = "/create-admin";
    public static final String UPDATE_MANAGER = "/update-manager";
    public static final String DELETE_MANAGER = "/delete-manager/{id}";
}
