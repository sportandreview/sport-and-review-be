package it.sportandreview.enums;

import lombok.Getter;

@Getter
public enum PhysicalStructure {
    ROBUSTO("Robusto"),
    ASCIUTTO("Asciutto"),
    ATLETICO("Atletico"),
    PALESTRATO("Palestrato");

    private final String description;

    PhysicalStructure(String description) {
        this.description = description;
    }
}
