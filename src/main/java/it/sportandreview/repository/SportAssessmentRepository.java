package it.sportandreview.repository;

import it.sportandreview.dto.response.SportAssessmentResponseDTO;
import it.sportandreview.entity.SportAssessment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SportAssessmentRepository extends JpaRepository<SportAssessment, Long> {

    @Query("SELECT new it.sportandreview.dto.response.SportAssessmentResponseDTO(sa.id, s.sportType, sa.skillLevel, sa.trainingFrequency, sa.playedCompetitively) " +
            "FROM SportAssessment sa JOIN sa.sport s WHERE sa.user.id = :userId")
    List<SportAssessmentResponseDTO> findSportAssessmentsByUserId(@Param("userId") Long userId);

    Optional<SportAssessment> findByUserIdAndSportId(Long userId, Long sportId);
}