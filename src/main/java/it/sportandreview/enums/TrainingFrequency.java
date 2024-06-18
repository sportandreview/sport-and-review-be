package it.sportandreview.enums;

import lombok.Getter;

@Getter
public enum TrainingFrequency {
    ONE_TO_TWO("1-2"),
    THREE_TO_FIVE("3-5"),
    NEVER("Mai");

    private final String description;

    TrainingFrequency(String description) {
        this.description = description;
    }
}
