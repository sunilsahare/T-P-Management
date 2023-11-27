package com.tnpserver.constants;

import java.util.Arrays;

public enum RoleEnum {
    ADMIN("admin"),
    STUDENT("student"),
    EMPLOYER("employer"),
    ALUMNI("alumni"),
    PARTNER("partner");

    private final String role;

    RoleEnum(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    // Method to check if the role is present in the enum
    public static RoleEnum fromString(String role) {
        return Arrays.stream(RoleEnum.values())
                .filter(userRole -> userRole.role.equalsIgnoreCase(role))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Role not found: " + role));
    }

}

