package it.sportandreview.dto.response;

import it.sportandreview.entity.SportAssessment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SportAssessmentResponseDTO {
    private Long userId;
    private Set<SportAssessment> sportAssessmentList;
}
