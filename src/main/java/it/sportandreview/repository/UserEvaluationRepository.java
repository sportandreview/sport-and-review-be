package it.sportandreview.repository;

import it.sportandreview.entity.Sport;
import it.sportandreview.entity.User;
import it.sportandreview.entity.UserEvaluation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserEvaluationRepository extends JpaRepository<UserEvaluation, Long> {
    List<UserEvaluation> findByUserId(Long userId);
    Optional<UserEvaluation> findByUserAndSport(User user, Sport sport);
}
