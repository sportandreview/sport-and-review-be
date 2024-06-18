package it.sportandreview.service;

import it.sportandreview.dto.request.SportAssessmentListRequestDTO;
import it.sportandreview.dto.request.SportAssessmentRequestDTO;
import it.sportandreview.dto.response.SportAssessmentResponseDTO;
import it.sportandreview.entity.SportAssessment;

import java.util.List;

public interface SportAssessmentService {
    void createOrUpdateSportAssessmentList(Long userId, SportAssessmentListRequestDTO requestDTO);
    SportAssessmentResponseDTO getSportAssessmentsByUserId(Long userId);
}
