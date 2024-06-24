package it.sportandreview.enums;

import lombok.Getter;

@Getter
public enum PhysicalStructure {
    ROBUST("Robusto"),
    LEAN("Asciutto"),
    ATHLETIC("Atletico"),
    MUSCULAR("Palestrato");

    private final String description;

    PhysicalStructure(String description) {
        this.description = description;
    }
}
