package it.sportandreview.auto_evaluation;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutoEvaluationRepository extends JpaRepository<AutoEvaluation, Long> {

    List<AutoEvaluation> findByPlayerUserId(Long playerUserId);

}
