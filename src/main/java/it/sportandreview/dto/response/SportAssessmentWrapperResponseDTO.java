package it.sportandreview.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SportAssessmentWrapperResponseDTO {
    private Long userId;
    private List<SportAssessmentResponseDTO> sportAssessments;
}
