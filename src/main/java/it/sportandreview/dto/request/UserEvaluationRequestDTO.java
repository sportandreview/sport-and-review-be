package it.sportandreview.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserEvaluationRequestDTO {
    @NotNull
    private Long sportId;

    @NotNull
    private Boolean playedCompetitively;

    @NotNull
    private String trainingFrequency;

    @NotNull
    private String skillLevel;
}
