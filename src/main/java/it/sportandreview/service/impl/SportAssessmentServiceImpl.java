package it.sportandreview.service.impl;

import it.sportandreview.dto.request.SportAssessmentListRequestDTO;
import it.sportandreview.dto.request.SportAssessmentRequestDTO;
import it.sportandreview.dto.response.SportAssessmentResponseDTO;
import it.sportandreview.entity.Sport;
import it.sportandreview.entity.SportAssessment;
import it.sportandreview.entity.User;
import it.sportandreview.enums.SkillLevel;
import it.sportandreview.enums.TrainingFrequency;
import it.sportandreview.exception.EntityNotFoundException;
import it.sportandreview.exception.ValidationException;
import it.sportandreview.repository.SportAssessmentRepository;
import it.sportandreview.repository.SportRepository;
import it.sportandreview.repository.UserRepository;
import it.sportandreview.service.SportAssessmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class SportAssessmentServiceImpl implements SportAssessmentService {

    private final UserRepository userRepository;
    private final SportRepository sportRepository;
    private final SportAssessmentRepository sportAssessmentRepository;
    private final MessageSource messageSource;

    @Override
    @Transactional
    public void createOrUpdateSportAssessmentList(Long userId, SportAssessmentListRequestDTO requestDTO) {
        log.info("Creating or updating sport assessments for user with ID: {}", userId);
        User user = getUserById(userId);

        for (SportAssessmentRequestDTO assessmentDTO : requestDTO.getSportAssessmentList()) {
            Sport sport = getSportById(assessmentDTO.getSportId());
            SportAssessment sportAssessment = getOrCreateSportAssessment(user, sport);

            List<SkillLevel> availableSkillLevels = getAvailableSkillLevels(
                    assessmentDTO.getPlayedCompetitively(),
                    assessmentDTO.getTrainingFrequency()
            );

            validateSkillLevel(assessmentDTO.getSkillLevel(), availableSkillLevels);

            updateSportAssessment(sportAssessment, assessmentDTO);
            sportAssessmentRepository.save(sportAssessment);
        }
        log.info("Sport assessments created or updated successfully for user with ID: {}", userId);
    }

    @Override
    @Transactional(readOnly = true)
    public SportAssessmentResponseDTO getSportAssessmentsByUserId(Long userId) {
        log.info("Fetching sport assessments for user with ID: {}", userId);
        List<SportAssessment> sportAssessments = sportAssessmentRepository.findByUserIdWithDetails(userId);
        if (sportAssessments.isEmpty()) {
            throw new EntityNotFoundException(getLocalizedMessage("user.not.found", userId));
        }

        Long userIdFromAssessments = sportAssessments.get(0).getUser().getId();
        return new SportAssessmentResponseDTO(userIdFromAssessments, new HashSet<>(sportAssessments));
    }

    private User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException(getLocalizedMessage("user.not.found", userId)));
    }

    private Sport getSportById(Long sportId) {
        return sportRepository.findById(sportId)
                .orElseThrow(() -> new EntityNotFoundException(getLocalizedMessage("sport.not.found", sportId)));
    }

    private SportAssessment getOrCreateSportAssessment(User user, Sport sport) {
        return sportAssessmentRepository.findByUserAndSport(user, sport)
                .orElseGet(() -> SportAssessment.builder()
                        .user(user)
                        .sport(sport)
                        .build());
    }

    private List<SkillLevel> getAvailableSkillLevels(Boolean playedCompetitive, TrainingFrequency trainingFrequency) {
        if (Boolean.TRUE.equals(playedCompetitive)) {
            if (trainingFrequency != TrainingFrequency.NEVER) {
                return Arrays.asList(SkillLevel.PROFESSIONAL, SkillLevel.ADVANCED);
            } else {
                return Arrays.asList(SkillLevel.ADVANCED, SkillLevel.INTERMEDIATE);
            }
        } else {
            if (trainingFrequency == TrainingFrequency.THREE_TO_FIVE) {
                return Arrays.asList(SkillLevel.ADVANCED, SkillLevel.INTERMEDIATE);
            } else {
                return Arrays.asList(SkillLevel.INTERMEDIATE, SkillLevel.BASIC);
            }
        }
    }

    private void validateSkillLevel(SkillLevel providedSkillLevel, List<SkillLevel> availableSkillLevels) {
        if (!availableSkillLevels.contains(providedSkillLevel)) {
            String availableLevels = availableSkillLevels.stream()
                    .map(SkillLevel::name)
                    .collect(Collectors.joining(", "));
            throw new ValidationException(String.format("Il livello di abilità fornito %s non è consentito. I livelli disponibili sono: %s",
                    providedSkillLevel, availableLevels));
        }
    }

    private void updateSportAssessment(SportAssessment sportAssessment, SportAssessmentRequestDTO assessmentDTO) {
        sportAssessment.setPlayedCompetitively(assessmentDTO.getPlayedCompetitively());
        sportAssessment.setTrainingFrequency(assessmentDTO.getTrainingFrequency());
        sportAssessment.setSkillLevel(assessmentDTO.getSkillLevel());
    }

    private String getLocalizedMessage(String key, Object... args) {
        return messageSource.getMessage(key, args, LocaleContextHolder.getLocale());
    }
}