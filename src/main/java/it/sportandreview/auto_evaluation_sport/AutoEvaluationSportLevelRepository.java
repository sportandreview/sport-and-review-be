package it.sportandreview.auto_evaluation_sport;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AutoEvaluationSportLevelRepository extends JpaRepository<AutoEvaluationSportLevel, Long> {
    List<AutoEvaluationSportLevel> findByAutoEvaluationId(Long autoEvaluationId);
    @Query("SELECT ae FROM AutoEvaluationSportLevel ae WHERE " +
            "ae.autoEvaluation.playerUser.id = :playerUserId " +
            "AND ae.id IN " +
            "(SELECT MAX(ae2.id) FROM AutoEvaluationSportLevel ae2 " +
            "WHERE ae2.autoEvaluation.playerUser.id = :playerUserId GROUP BY ae2.sport.id) " +
            "ORDER BY ae.level DESC")
    List<AutoEvaluationSportLevel> findByAutoEvaluationPlayerUserIdOrderByLevelDesc(Long playerUserId);

}
