package it.sportandreview.enums;

public enum RoleType {
    ROLE_USER("User"),
    ROLE_ADMIN("Administrator"),
    ROLE_SUPER_ADMIN("Super Administrator");


    private final String description;

    RoleType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
