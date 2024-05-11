package it.sportandreview.auto_evaluation;


import java.util.List;

public interface AutoEvaluationService {

    Long create(AutoEvaluationDTO autoevaluationDto);

    List<AutoEvaluationDTO> createAll(List<AutoEvaluationDTO> autoEvaluations);

    AutoEvaluationDTO update(AutoEvaluationDTO autoevaluationDto);

    List<AutoEvaluationDTO> findByPlayerUserId(Long playerUserId);

}
