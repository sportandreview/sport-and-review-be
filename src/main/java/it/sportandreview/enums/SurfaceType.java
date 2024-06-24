package it.sportandreview.enums;

import lombok.Getter;

@Getter
public enum SurfaceType {
    GRASS("Erba"),
    CLAY("Terra battuta"),
    HARD("Cemento"),
    SYNTHETIC("Sintetico");

    private final String description;

    SurfaceType(String description) {
        this.description = description;
    }
}
