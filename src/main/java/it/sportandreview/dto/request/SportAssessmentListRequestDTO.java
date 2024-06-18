package it.sportandreview.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class SportAssessmentListRequestDTO {
    @NotNull
    private List<SportAssessmentRequestDTO> sportAssessmentList;
}
