package it.sportandreview.repository;

import it.sportandreview.entity.Sport;
import it.sportandreview.entity.User;
import it.sportandreview.entity.SportAssessment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SportAssessmentRepository extends JpaRepository<SportAssessment, Long> {
    List<SportAssessment> findByUser(User user);
    Optional<SportAssessment> findByUserAndSport(User user, Sport sport);

    @Query("SELECT sa FROM SportAssessment sa " +
            "JOIN FETCH sa.user u " +
            "JOIN FETCH sa.sport s " +
            "WHERE u.id = :userId")
    List<SportAssessment> findByUserIdWithDetails(@Param("userId") Long userId);

}
