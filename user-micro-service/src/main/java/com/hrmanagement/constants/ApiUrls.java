package com.hrmanagement.constants;

public class ApiUrls {
    public static final String VERSION = "api/v1";
    public static final String USER_APP = VERSION + "/user";

    //User Controller
    public static final String CREATE_USER = "/create-user";
    public static final String UPDATE_USER = "/update-user";
    public static final String DELETE_USER = "/delete-user";
    public static final String FINDALL = "/findall";
    public static final String ACTIVATE_STATUS = "/activate-status";
    public static final String ACTIVATE_STATUS_MANAGER = "/activate-status-manager";
    public static final String ADD_EMPLOYEE = "/addemployee";
    public static final String FIND_USER = "/find-by-user-dto/{authId}";
}
