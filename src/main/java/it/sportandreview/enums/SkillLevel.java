package it.sportandreview.enums;

import lombok.Getter;

@Getter
public enum SkillLevel {
    PROFESSIONISTA("Professionista"),
    AVANZATO("Avanzato"),
    INTERMEDIO("Intermedio"),
    BASE("Base");

    private final String description;

    SkillLevel(String description) {
        this.description = description;
    }
}
