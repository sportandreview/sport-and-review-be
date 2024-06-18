package it.sportandreview.enums;

public enum SportType {
    PADEL("Padel"),
    TENNIS("Tennis"),
    CALCIO("Calcio"),
    BASKET("Basket"),
    CALCIOA5("Calcio a 5"),
    CALCETTO("Calcetto"),
    CALCIOTTO("Calciotto");

    private final String description;

    SportType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
