package it.sportandreview.service;

import it.sportandreview.dto.request.UserEvaluationRequestDTO;
import it.sportandreview.dto.response.UserEvaluationResponseDTO;

import java.util.List;

public interface UserEvaluationService {
    UserEvaluationResponseDTO evaluateUser(Long userId, UserEvaluationRequestDTO requestDTO);
    List<UserEvaluationResponseDTO> getUserEvaluations(Long userId);
}
