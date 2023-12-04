package com.authservice.utils;

import com.commonmodule.security.CustomUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserUtil {

    public static CustomUserDetails getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if( authentication == null || !authentication.isAuthenticated()) {
            return null;
        }
        Object principal = authentication.getPrincipal();
        if (principal instanceof CustomUserDetails) {
            return (CustomUserDetails) principal;
        }
        return null;
    }

    public static String getCurrentUsername() {
        CustomUserDetails userDetails = getCurrentUser();
        return (userDetails.getUsername() != null) ? userDetails.getUsername() : null;
    }

    public static Long getCurrentLoggedInUserId() {
        CustomUserDetails userDetails = getCurrentUser();
        return userDetails.getId();
    }
}
