package it.sportandreview.enums;

public enum GenderType {
    MALE("Maschio"),
    FEMALE("Femmina"),
    UNSPECIFIED("Non specificato");

    private final String description;

    GenderType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
