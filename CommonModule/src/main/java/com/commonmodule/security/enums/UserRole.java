package com.commonmodule.security.enums;

public enum UserRole {
    ROLE_ADMIN("ROLE_ADMIN"), ROLE_USER("ROLE_USER");
    private String value;

    UserRole(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
