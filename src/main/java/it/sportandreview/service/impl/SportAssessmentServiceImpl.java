package it.sportandreview.service.impl;

import it.sportandreview.dto.request.SportAssessmentListRequestDTO;
import it.sportandreview.dto.request.SportAssessmentRequestDTO;
import it.sportandreview.dto.response.SportAssessmentResponseDTO;
import it.sportandreview.entity.Sport;
import it.sportandreview.entity.SportAssessment;
import it.sportandreview.entity.User;
import it.sportandreview.enums.SkillLevel;
import it.sportandreview.enums.TrainingFrequency;
import it.sportandreview.exception.SportNotFoundException;
import it.sportandreview.exception.UserNotFoundException;
import it.sportandreview.repository.SportAssessmentRepository;
import it.sportandreview.repository.SportRepository;
import it.sportandreview.repository.UserRepository;
import it.sportandreview.service.SportAssessmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class SportAssessmentServiceImpl implements SportAssessmentService {

    private final UserRepository userRepository;
    private final SportRepository sportRepository;
    private final SportAssessmentRepository sportAssessmentRepository;

    @Override
    @Transactional
    public void createOrUpdateSportAssessmentList(Long userId, SportAssessmentListRequestDTO requestDTO) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        for (SportAssessmentRequestDTO assessmentDTO : requestDTO.getSportAssessmentList()) {
            Sport sport = sportRepository.findById(assessmentDTO.getSportId())
                    .orElseThrow(() -> new SportNotFoundException(assessmentDTO.getSportId()));

            Optional<SportAssessment> existingAssessment = sportAssessmentRepository.findByUserAndSport(user, sport);

            SportAssessment sportAssessment = existingAssessment.orElseGet(() -> SportAssessment.builder()
                    .user(user)
                    .sport(sport)
                    .build());

            SkillLevel skillLevel = determineSkillLevel(
                    assessmentDTO.getPlayedCompetitively(),
                    assessmentDTO.getTrainingFrequency()
            );

            if (!skillLevel.equals(assessmentDTO.getSkillLevel())) {
                throw new IllegalArgumentException("Skill level not allowed for the provided responses");
            }

            sportAssessment.setPlayedCompetitively(assessmentDTO.getPlayedCompetitively());
            sportAssessment.setTrainingFrequency(assessmentDTO.getTrainingFrequency());
            sportAssessment.setSkillLevel(skillLevel);

            sportAssessmentRepository.save(sportAssessment);
        }
    }


    @Override
    @Transactional(readOnly = true)
    public SportAssessmentResponseDTO getSportAssessmentsByUserId(Long userId) {
        List<SportAssessment> sportAssessments = sportAssessmentRepository.findByUserIdWithDetails(userId);
        if (sportAssessments.isEmpty()) {
            throw new UserNotFoundException(userId);
        }

        Long userIdFromAssessments = sportAssessments.get(0).getUser().getId();
        return new SportAssessmentResponseDTO(userIdFromAssessments, new HashSet<>(sportAssessments));
    }

    private SkillLevel determineSkillLevel(Boolean playedCompetitive, TrainingFrequency trainingFrequency) {
        if (Boolean.TRUE.equals(playedCompetitive)) {
            if (trainingFrequency == TrainingFrequency.ONE_TO_TWO || trainingFrequency == TrainingFrequency.THREE_TO_FIVE) {
                return SkillLevel.PROFESSIONISTA;
            } else if (trainingFrequency == TrainingFrequency.NEVER) {
                return SkillLevel.AVANZATO;
            }
        } else {
            if (trainingFrequency == TrainingFrequency.THREE_TO_FIVE) {
                return SkillLevel.AVANZATO;
            } else {
                return SkillLevel.INTERMEDIO;
            }
        }
        return SkillLevel.BASE;
    }
}
