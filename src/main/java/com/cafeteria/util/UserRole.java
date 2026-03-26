package com.cafeteria.util;

/**
 * 用户角色枚举
 * User role enumeration
 */
public enum UserRole {
    CUSTOMER("CUSTOMER", "顾客"),
    ADMIN("ADMIN", "管理员");

    private final String code;
    private final String description;

    UserRole(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static UserRole fromCode(String code) {
        for (UserRole role : UserRole.values()) {
            if (role.code.equals(code)) {
                return role;
            }
        }
        throw new IllegalArgumentException("Unknown user role code: " + code);
    }
}
