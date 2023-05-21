package com.project.enums;

public enum RoleEnum {
    RUNNER(Constants.CUSTOMER_VALUE),
    RESTAURANT_OWNER(Constants.RESTAURANT_OWNER_VALUE),
    CUSTOMER(Constants.RUNNER_VALUE);

    RoleEnum(String roleString) {
    }

    public static class Constants {
        public static final String RUNNER_VALUE = "RUNNER";
        public static final String RESTAURANT_OWNER_VALUE = "RESTAURANT_OWNER";
        public static final String CUSTOMER_VALUE = "CUSTOMER";
    }
}
