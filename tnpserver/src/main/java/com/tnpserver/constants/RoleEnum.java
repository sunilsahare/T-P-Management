package com.tnpserver.constants;

import java.util.Arrays;

public enum RoleEnum {
    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_HOD("ROLE_HOD"),
    ROLE_STUDENT("ROLE_STUDENT"),
    ROLE_TNP_OFFICER("ROLE_TNP_OFFICER"),
    ROLE_ALUMNI("ROLE_ALUMNI"),
    ROLE_PARTNER("ROLE_PARTNER"),
    ROLE_EMPLOYER("ROLE_EMPLOYER");

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

