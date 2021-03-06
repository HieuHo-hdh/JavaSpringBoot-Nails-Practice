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
    public static final String COLLABORATOR_TOTAL_MONEY_REPORT_ERROR_UNAUTHORIZED = "ERROR-COLLABORATOR-009";

    /**
     * Province error code
     */
    public static final String PROVINCE_ERROR_UNAUTHORIZED = "ERROR-PROVINCE-000";
    public static final String PROVINCE_ERROR_NOT_FOUND = "ERROR-PROVINCE-001";
    public static final String PROVINCE_ERROR_KIND_NOT_FOUND = "ERROR-PROVINCE-002" ;

    /**
     * Address error code
     */
    public static final String ADDRESS_ERROR_UNAUTHORIZED = "ERROR-ADDRESS-000";
    public static final String ADDRESS_ERROR_NOT_FOUND = "ERROR-ADDRESS-001";
    public static final String ADDRESS_ERROR_NOT_FOUND_PROVINCE = "ERROR-ADDRESS-002";
    public static final String ADDRESS_ERROR_NOT_FOUND_DISTRICT = "ERROR-ADDRESS-003";
    public static final String ADDRESS_ERROR_NOT_FOUND_COMMUNE = "ERROR-ADDRESS-004";
    public static final String ADDRESS_ERROR_NOT_FOUND_CUSTOMER = "ERROR-ADDRESS-005";

    /**
     * Settings error code
     */
    public static final String SETTINGS_ERROR_UNAUTHORIZED = "ERROR-SETTINGS-000";
    public static final String SETTINGS_ERROR_NOT_FOUND = "ERROR-SETTINGS-001";
    public static final String SETTINGS_ERROR_NOT_FOUND_KIND = "ERROR-SETTINGS-002";
    public static final String SETTINGS_ERROR_NOT_FOUND_GROUP = "ERROR-SETTINGS-003";
    public static final String SETTINGS_ERROR_INVALID_GROUP = "ERROR-SETTINGS-004";
    public static final String SETTINGS_ERROR_KEY_DUPLICATED = "ERROR-SETTINGS-005";

    /**
     * Import-export error code
     */
    public static final String IMPORTEXPORT_ERROR_UNAUTHORIZED = "ERROR-IMPORTEXPORT-000";
    public static final String IMPORTEXPORT_ERROR_NOT_FOUND = "ERROR-IMPORTEXPORT-001";
    public static final String IMPORTEXPORT_ERROR_NOT_FOUND_CATEGORY = "ERROR-IMPORTEXPORT-002";
    public static final String IMPORTEXPORT_ERROR_NULL_KIND = "ERROR-IMPORTEXPORT-003";

    /**
     * Product error code
     */
    public static final String PRODUCT_ERROR_UNAUTHORIZED = "ERROR-PRODUCT-000";
    public static final String PRODUCT_ERROR_NOT_FOUND = "ERROR-PRODUCT-001";
    public static final String PRODUCT_ERROR_NOT_FOUND_CATEGORY = "ERROR-PRODUCT-002";
    public static final String PRODUCT_ERROR_NULL_KIND = "ERROR-PRODUCT-003";

    /**
     * Orders error code
     */
    public static final String ORDERS_ERROR_UNAUTHORIZED = "ERROR-ORDERS-000";
    public static final String ORDERS_ERROR_NOT_FOUND = "ERROR-ORDERS-001";
    public static final String ORDERS_ERROR_NOT_FOUND_PRODUCT = "ERROR-ORDERS-002";
    public static final String ORDERS_ERROR_WRONG_STATE_UPDATE = "ERROR-ORDERS-003";
    public static final String ORDERS_ERROR_NOT_FOUND_ORDERS_DETAIL = "ERROR-ORDERS-004";
    public static final String ORDERS_ERROR_UNEQUAL_NUMBER = "ERROR-ORDERS-005";
    public static final String ORDERS_ERROR_INVALID_STATE = "ERROR-ORDERS-006";
    public static final String ORDERS_ERROR_NOT_FOUND_COLLABORATOR = "ERROR-ORDERS-007";


    /**
     * Collaborator product error code
     */
    public static final String COLLABORATOR_PRODUCT_ERROR_UNAUTHORIZED = "ERROR-COLLABORATOR-PRODUCT-001";
    public static final String COLLABORATOR_PRODUCT_ERROR_NOT_FOUND = "ERROR-COLLABORATOR-PRODUCT-002";
    public static final String COLLABORATOR_PRODUCT_ERROR_NOT_FOUND_PRODUCT = "ERROR-COLLABORATOR-PRODUCT-003";
    public static final String COLLABORATOR_PRODUCT_ERROR_NOT_FOUND_COLLABORATOR = "ERROR-COLLABORATOR-PRODUCT-004";
    public static final String COLLABORATOR_PRODUCT_ERROR_INVALID_VALUE = "ERROR-COLLABORATOR-PRODUCT-005";
    public static final String COLLABORATOR_PRODUCT_ERROR_INVALID_KIND = "ERROR-COLLABORATOR-PRODUCT-006";

    /**
     * Salary period error code
     */
    public static final String SALARY_PERIOD_ERROR_UNAUTHORIZED = "ERROR-SALARY-PERIOD-001";
    public static final String SALARY_PERIOD_ERROR_NOT_FOUND = "ERROR-SALARY-PERIOD-002";
    public static final String SALARY_PERIOD_ERROR_INVALID_TIME = "ERROR-SALARY-PERIOD-003";
    public static final String SALARY_PERIOD_ERROR_NOT_FOUND_COLLABORATOR = "ERROR-SALARY-PERIOD-004";
    public static final String SALARY_PERIOD_ERROR_INVALID_VALUE = "ERROR-SALARY-PERIOD-005";
    public static final String SALARY_PERIOD_ERROR_INVALID_KIND = "ERROR-SALARY-PERIOD-006";

    /**
     * Salary error code
     */
    public static final String SALARY_ERROR_UNAUTHORIZED = "ERROR-SALARY-PERIOD-001";
    public static final String SALARY_ERROR_NOT_FOUND = "ERROR-SALARY-PERIOD-002";
    public static final String SALARY_ERROR_INVALID_TIME = "ERROR-SALARY-PERIOD-003";
    public static final String SALARY_ERROR_NOT_FOUND_COLLABORATOR = "ERROR-SALARY-PERIOD-004";
    public static final String SALARY_ERROR_INVALID_VALUE = "ERROR-SALARY-PERIOD-005";
    public static final String SALARY_ERROR_INVALID_KIND = "ERROR-SALARY-PERIOD-006";

    private ErrorCode() { throw new IllegalStateException("Utility class"); }
}
