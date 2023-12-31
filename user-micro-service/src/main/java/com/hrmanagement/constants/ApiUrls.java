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
    public static final String FIND_ALL_EMPLOYEE = "/findall-employee";
    public static final String UPDATE_EMPLOYEE = "/update-employee/{email}";
    public static final String IMAGE_UPLOAD = "/image-upload";
    public static final String FIND_ALL_MANAGER = "/findall-manager";
    public static final String UPDATE_MANAGER = "/update-manager/{authId}";
    public static final String DELETE_MANAGER = "/delete-manager/{authId}";
    public static final String DELETE_EMPLOYEE = "/delete-employee/{email}";
    public static final String UPDATE_USER_INFO = "/update-user-info";
    public static final String ADVANCE_CREATE = "/advances-create";
    public static final String PERMISSION_CREATE = "/permission-create";
    public static final String PERMISSION_APPROVE = "/approve-permission/{id}";
    public static final String ADVANCE_APPROVE = "/approve-advance/{id}";
    public static final String FIND_ALL_PERMISSION_EMPLOYEE = "/findall-permission-employee";
    public static final String FIND_ALL_PERMISSION_MANAGER = "/findall-permission-manager";
    public static final String FIND_ALL_ADVANCES_EMPLOYEE = "/findall-employee-advances";
    public static final String FIND_ALL_ADVANCES_MANAGER = "/findall-manager-advances";
    public static final String EXPENSE_CREATE = "/expense-create";
    public static final String EXPENSE_APPROVE = "/approve-expense";
    public static final String FIND_ALL_EXPENSE_EMPLOYEE = "/findall-employee-expenses";
    public static final String FIND_ALL_EXPENSE_MANAGER = "/findall-manager-expenses";
    public static final String PRICING_DATES = "/pricing-dates";
}
