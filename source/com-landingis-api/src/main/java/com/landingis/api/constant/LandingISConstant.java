package com.landingis.api.constant;


import com.landingis.api.utils.ConfigurationService;

public class LandingISConstant {
    public static final String ROOT_DIRECTORY =  ConfigurationService.getInstance().getString("file.upload-dir","/tmp/upload");

    public static final Integer USER_KIND_ADMIN = 1;
    public static final Integer USER_KIND_CUSTOMER = 2;
    public static final Integer USER_KIND_EMPLOYEE = 3;
    public static final Integer USER_KIND_COLLABORATOR = 4;

    public static final Integer STATUS_ACTIVE = 1;
    public static final Integer STATUS_PENDING = 0;
    public static final Integer STATUS_LOCK = -1;
    public static final Integer STATUS_DELETE = -2;

    public static final Integer GROUP_KIND_SUPER_ADMIN = 1;
    public static final Integer GROUP_KIND_CUSTOMER = 2;
    public static final Integer GROUP_KIND_EMPLOYEE = 3;
    public static final Integer GROUP_KIND_COLLABORATOR = 4;

    public static final Integer MAX_ATTEMPT_FORGET_PWD = 5;
    public static final Integer MAX_TIME_FORGET_PWD = 5 * 60 * 1000; //5 minutes
    public static final Integer MAX_ATTEMPT_LOGIN = 5;
    public static final Integer MAX_TIME_VERIFY_ACCOUNT = 5 * 60 * 1000; //5 minutes

    public static final Integer CATEGORY_KIND = 1;

    public static final Integer CATEGORY_KIND_IMPORT = 1;
    public static final Integer CATEGORY_KIND_EXPORT = 2;
    public static final Integer CATEGORY_KIND_PRODUCT = 3;
    public static final Integer CATEGORY_KIND_COLLABORATOR_PRODUCT = 4;
    public static final Integer CATEGORY_KIND_NEWS_INTERNAL = 5;
    public static final Integer CATEGORY_KIND_NEWS_COLLABORATOR = 6;

    public static final Integer GENDER_MALE = 1;
    public static final Integer GENDER_FEMALE = 2;
    public static final Integer GENDER_OTHER = 3;

    public static final Integer PROVINCE_KIND_PROVINCE = 1;
    public static final Integer PROVINCE_KIND_DISTRICT = 2;
    public static final Integer PROVINCE_KIND_COMMUNE = 3;

    public static final Integer SETTING_KIND_ON_OFF = 1;
    public static final Integer SETTING_KIND_TEXT = 2;
    public static final Integer SETTING_KIND_DATE = 3;
    public static final Integer SETTING_KIND_TIME = 4;
    public static final Integer SETTING_KIND_TIMESTAMP = 5;
    public static final Integer SETTING_KIND_UPLOAD = 6;

    public static final Integer SETTING_GROUP_ID_ADMIN = 1;
    public static final Integer SETTING_GROUP_ID_CUSTOMER = 2;

    public static final Integer ORDERS_STATE_CREATED = 0;
    public static final Integer ORDERS_STATE_ACCEPTED = 1;
    public static final Integer ORDERS_STATE_SHIPPING = 2;
    public static final Integer ORDERS_STATE_DONE = 3;
    public static final Integer ORDERS_STATE_CANCEL = 4;

    public static final Integer VAT = 10;

    public static final Integer COLLABORATOR_PRODUCT_KIND_MONEY_VALUE = 1;
    public static final Integer COLLABORATOR_PRODUCT_KIND_PERCENT_VALUE = 2;

    public static final Integer SALARY_PERIOD_STATE_PENDING = 1;
    public static final Integer SALARY_PERIOD_STATE_CALCULATED = 2;
    public static final Integer SALARY_PERIOD_STATE_DONE = 3;

    private LandingISConstant(){
        throw new IllegalStateException("Utility class");
    }

}
