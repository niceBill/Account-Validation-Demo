package com.example.accountvalidate.util;

public class ServiceConstants {
    public static  final String ACCOUNT_VALIDATE_SERVICE = "Account Validate Service";
    public static final String VALIDATE_URI = "/validate";
    public static final String PROVIDER1_URI = "/v1/api/account/validate";
    public static final String PROVIDER2_URI = "/v2/api/account/validate";
    public static final String PROVIDER1_NAME = "provider1";
    public static final String PROVIDER2_NAME = "provider2";
    public static final String NUMERIC_REGEX = "^[0-9]+$";
    public static final String SUCCESS_MESSAGE = "Successfully validated the given account";
    public static final int FAILURE = 1;
    public static final int INVALID_INPUT = 41;
    public static final String INVALID_PROVIDER_MESSAGE = "The given provider is not found";
}
