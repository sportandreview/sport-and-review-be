package it.sportandreview.dto.response;

import lombok.Data;

@Data
public class UserEvaluationResponseDTO {
    private Long id;
    private Long sportId;
    private String skillLevel;
    private String trainingFrequency;
    private Boolean playedCompetitively;
}
