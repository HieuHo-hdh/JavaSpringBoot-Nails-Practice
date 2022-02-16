package com.landingis.api.dto;

public class ErrorCode {

    /**
     * General error code
     */
    public static final String GENERAL_ERROR_UNAUTHORIZED = "ERROR-GENERAL-000";
    public static final String GENERAL_ERROR_NOT_FOUND = "ERROR-GENERAL-001";
    public static final String GENERAL_ERROR_BAD_REQUEST = "ERROR-GENERAL-002";
    public static final String GENERAL_ERROR_LOGIN_FAILED = "ERROR-GENERAL-003";
    public static final String GENERAL_ERROR_NOT_MATCH = "ERROR-GENERAL-004";
    public static final String GENERAL_ERROR_WRONG_HASH = "ERROR-GENERAL-005";
    public static final String GENERAL_ERROR_LOCKED = "ERROR-GENERAL-006";
    public static final String GENERAL_ERROR_INVALID = "ERROR-GENERAL-007";

    /**
     * Category error code
     */
    public static final String CATEGORY_ERROR_UNAUTHORIZED = "ERROR-CATEGORY-000";
    public static final String CATEGORY_ERROR_NOT_FOUND = "ERROR-CATEGORY-001";

    /**
     * Group error code
     */
    public static final String GROUP_ERROR_UNAUTHORIZED = "ERROR-GROUP-000";
    public static final String GROUP_ERROR_NOT_FOUND = "ERROR-GROUP-001";
    public static final String GROUP_ERROR_EXIST = "ERROR-GROUP-002";
    public static final String GROUP_ERROR_CAN_NOT_DELETED = "ERROR-GROUP-003";

    /**
     * Permission error code
     */
    public static final String PERMISSION_ERROR_UNAUTHORIZED = "ERROR-PERMISSION-000";
    public static final String PERMISSION_ERROR_NOT_FOUND = "ERROR-PERMISSION-001";

    /**
     * News error code
     */
    public static final String NEWS_ERROR_UNAUTHORIZED = "ERROR-NEWS-000";
    public static final String NEWS_ERROR_NOT_FOUND = "ERROR-NEWS-001";

    /**
     * Customer error code
     */
    public static final String CUSTOMER_ERROR_UNAUTHORIZED = "ERROR-CUSTOMER-000";
    public static final String CUSTOMER_ERROR_NOT_FOUND = "ERROR-CUSTOMER-001";
    public static final String CUSTOMER_ERROR_PHONE_EXIST = "ERROR-CUSTOMER-002";
    public static final String CUSTOMER_ERROR_GROUP_NOT_EXIST = "ERROR-CUSTOMER-003";
    public static final String CUSTOMER_ERROR_NOT_FOUND_PROVINCE = "ERROR-CUSTOMER-004";
    public static final String CUSTOMER_ERROR_EMAIL_EXIST = "ERROR-CUSTOMER-005";
    public static final String CUSTOMER_ERROR_OLD_PWD_NOT_MATCH = "ERROR-CUSTOMER-006";

    /**
     * Employee error code
     */
    public static final String EMPLOYEE_ERROR_UNAUTHORIZED = "ERROR-EMPLOYEE-000";
    public static final String EMPLOYEE_ERROR_NOT_FOUND = "ERROR-EMPLOYEE-001";
    public static final String EMPLOYEE_ERROR_PHONE_USERNAME_EXIST = "ERROR-EMPLOYEE-002";
    public static final String EMPLOYEE_ERROR_USERNAME_EXIST = "ERROR-EMPLOYEE-003";
    public static final String EMPLOYEE_ERROR_GROUP_NOT_EXIST = "ERROR-EMPLOYEE-004";
    public static final String EMPLOYEE_ERROR_NOT_FOUND_PROVINCE = "ERROR-EMPLOYEE-005";
    public static final String EMPLOYEE_ERROR_EMAIL_EXIST = "ERROR-EMPLOYEE-006";
    public static final String EMPLOYEE_ERROR_OLD_PWD_NOT_MATCH = "ERROR-EMPLOYEE-007";

    /**
     * Collaborator error code
     */
    public static final String COLLABORATOR_ERROR_UNAUTHORIZED = "ERROR-COLLABORATOR-000";
    public static final String COLLABORATOR_ERROR_NOT_FOUND = "ERROR-COLLABORATOR-001";
    public static final String COLLABORATOR_ERROR_PHONE_EXIST = "ERROR-COLLABORATOR-002";
    public static final String COLLABORATOR_ERROR_USERNAME_EXIST = "ERROR-COLLABORATOR-003";
    public static final String COLLABORATOR_ERROR_GROUP_NOT_EXIST = "ERROR-COLLABORATOR-004";
    public static final String COLLABORATOR_ERROR_NOT_FOUND_PROVINCE = "ERROR-COLLABORATOR-005";
    public static final String COLLABORATOR_ERROR_EMAIL_EXIST = "ERROR-COLLABORATOR-006";
    public static final String COLLABORATOR_ERROR_OLD_PWD_NOT_MATCH = "ERROR-COLLABORATOR-007";
    public static final String COLLABORATOR_ERROR_NOT_FOUND_EMPLOYEE = "ERROR-COLLABORATOR-008";

    /**
     * Province error code
     */
    public static final String PROVINCE_ERROR_UNAUTHORIZED = "ERROR-PROVINCE-000";
    public static final String PROVINCE_ERROR_NOT_FOUND = "ERROR-PROVINCE-001";
    public static final String PROVINCE_ERROR_KIND_NOT_FOUND = "ERROR-PROVINCE-002" ;

    private ErrorCode() { throw new IllegalStateException("Utility class"); }
}
