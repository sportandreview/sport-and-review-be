package it.sportandreview.enums;

import lombok.Getter;

@Getter
public enum SkillLevel {
    PROFESSIONAL("Professionista"),
    ADVANCED("Avanzato"),
    INTERMEDIATE("Intermedio"),
    BASIC("Base");

    private final String description;

    SkillLevel(String description) {
        this.description = description;
    }
}
