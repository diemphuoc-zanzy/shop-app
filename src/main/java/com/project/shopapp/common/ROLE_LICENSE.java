package com.project.shopapp.common;

import lombok.Getter;

@Getter
public enum ROLE_LICENSE {
    ADMIN("Administrator", "Full access to the system, including user and product management."),
    SELLER("Seller", "Manage their own products and orders."),
    CUSTOMER("Customer", "Purchase products and access customer features."),
    MODERATOR("Moderator", "Manage user-generated content such as reviews or comments."),
    GUEST("Guest", "Limited access, mostly browsing features.");

    private final String displayName;
    private final String description;

    ROLE_LICENSE(String displayName, String description) {
        this.displayName = displayName;
        this.description = description;
    }

    public static ROLE_LICENSE fromDisplayName(String displayName) {
        for (ROLE_LICENSE role : ROLE_LICENSE.values()) {
            if (role.getDisplayName().equalsIgnoreCase(displayName)) {
                return role;
            }
        }
        throw new IllegalArgumentException("No role found with display name: " + displayName);
    }

    public static boolean isNotAdmin(String roleName) {
        return !ROLE_LICENSE.ADMIN.name().equalsIgnoreCase(roleName);
    }
}
