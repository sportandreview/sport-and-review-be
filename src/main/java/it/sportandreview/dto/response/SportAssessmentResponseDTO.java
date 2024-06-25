package it.sportandreview.dto.response;

import it.sportandreview.enums.SkillLevel;
import it.sportandreview.enums.SportType;
import it.sportandreview.enums.TrainingFrequency;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SportAssessmentResponseDTO {
    private Long id;
    private SportType sportType;
    private SkillLevel skillLevel;
    private TrainingFrequency trainingFrequency;
    private Boolean playedCompetitively;
}