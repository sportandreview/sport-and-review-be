package it.sportandreview.dto.request;

import it.sportandreview.enums.SkillLevel;
import it.sportandreview.enums.TrainingFrequency;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SportAssessmentRequestDTO {
    @NotNull
    private Long sportId;

    @NotNull
    private Boolean playedCompetitively;

    @NotNull
    private TrainingFrequency trainingFrequency;

    @NotNull
    private SkillLevel skillLevel;
}
