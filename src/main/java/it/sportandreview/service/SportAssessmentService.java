// SportAssessmentService.java
package it.sportandreview.service;

import it.sportandreview.dto.request.SportAssessmentListRequestDTO;
import it.sportandreview.dto.response.SportAssessmentWrapperResponseDTO;

public interface SportAssessmentService {
    SportAssessmentWrapperResponseDTO getSportAssessmentsByUserId(Long userId);
    void createOrUpdateSportAssessmentList(Long userId, SportAssessmentListRequestDTO requestDTO);
}