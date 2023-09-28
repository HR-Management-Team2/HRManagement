package com.hrmanagement.constant;

public class RestApis {
    public static final String VERSION = "api/v1";
    public static final String COMPANY = VERSION + "/company";

    //Company Controller
    public static final String SAVE = "/save";
    public static final String UPDATE = "/update/{taxNumber}";
    public static final String DELETE = "/delete/{taxNumber}";
    public static final String FINDALL = "/findall";
    public static final String FINDBYID = "/findbyid";
    public static final String ADDCOMPANY = "/addcompany";
    public static final String CHECKCOMPANY = "/check-company";
}
