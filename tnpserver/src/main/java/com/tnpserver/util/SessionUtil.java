package com.tnpserver.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import java.util.List;
import java.util.stream.Collectors;

public class SessionUtil {

    public static User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && authentication.getPrincipal() instanceof org.springframework.security.core.userdetails.User) {
            return (org.springframework.security.core.userdetails.User) authentication.getPrincipal();
        }
        return null;
    }

    public static String getCurrentUsername() {
        User currentUser = getCurrentUser();
        return currentUser != null ? currentUser.getUsername() : null;
    }

    public static String getCurrentUserRole() {
        User currentUser = getCurrentUser();
        return currentUser != null
                ? currentUser.getAuthorities().stream()
                .findFirst()
                .map(GrantedAuthority::getAuthority)
                .orElse(null)
                : null;
    }

    public static List<String> getCurrentUserRoles() {
        User currentUser = getCurrentUser();
        return currentUser != null
                ? currentUser.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList())
                : null;
    }


}
