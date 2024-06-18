package it.sportandreview.service.impl;

import it.sportandreview.dto.request.UserEvaluationRequestDTO;
import it.sportandreview.dto.response.UserEvaluationResponseDTO;
import it.sportandreview.entity.Sport;
import it.sportandreview.entity.User;
import it.sportandreview.entity.UserEvaluation;
import it.sportandreview.enums.SkillLevel;
import it.sportandreview.enums.TrainingFrequency;
import it.sportandreview.mapper.UserEvaluationMapper;
import it.sportandreview.repository.SportRepository;
import it.sportandreview.repository.UserEvaluationRepository;
import it.sportandreview.repository.UserRepository;
import it.sportandreview.service.UserEvaluationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserEvaluationServiceImpl implements UserEvaluationService {

    private final UserRepository userRepository;
    private final SportRepository sportRepository;
    private final UserEvaluationRepository userEvaluationRepository;
    private final UserEvaluationMapper userEvaluationMapper;

    @Override
    @Transactional
    public UserEvaluationResponseDTO evaluateUser(Long userId, UserEvaluationRequestDTO requestDTO) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Sport sport = sportRepository.findById(requestDTO.getSportId())
                .orElseThrow(() -> new RuntimeException("Sport not found"));

        UserEvaluation userEvaluation = userEvaluationRepository.findByUserAndSport(user, sport)
                .orElseGet(() -> new UserEvaluation());

        List<SkillLevel> skillLevels = determineSkillLevels(
                requestDTO.getPlayedCompetitively(),
                requestDTO.getTrainingFrequency()
        );

        SkillLevel selectedSkillLevel = SkillLevel.valueOf(requestDTO.getSkillLevel().toUpperCase().replace(" ", "_"));
        if (!skillLevels.contains(selectedSkillLevel)) {
            throw new IllegalArgumentException("Skill level not allowed for the provided responses");
        }

        userEvaluation = userEvaluationMapper.toEntity(requestDTO);
        userEvaluation.setUser(user);
        userEvaluation.setSport(sport);

        userEvaluationRepository.save(userEvaluation);

        if (!user.getSports().contains(sport)) {
            user.getSports().add(sport);
            userRepository.save(user);
        }

        return userEvaluationMapper.toDto(userEvaluation);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserEvaluationResponseDTO> getUserEvaluations(Long userId) {
        return userEvaluationRepository.findByUserId(userId).stream()
                .map(userEvaluationMapper::toDto)
                .collect(Collectors.toList());
    }

    private List<SkillLevel> determineSkillLevels(boolean playedCompetitively, String trainingFrequency) {
        TrainingFrequency frequency = TrainingFrequency.valueOf(trainingFrequency.toUpperCase().replace("-", "_").replace(" ", ""));
        if (playedCompetitively) {
            if (!frequency.equals(TrainingFrequency.NEVER)) {
                return List.of(SkillLevel.PROFESSIONISTA, SkillLevel.AVANZATO);
            } else {
                return List.of(SkillLevel.AVANZATO, SkillLevel.INTERMEDIO);
            }
        } else {
            if (frequency.equals(TrainingFrequency.THREE_TO_FIVE)) {
                return List.of(SkillLevel.AVANZATO, SkillLevel.INTERMEDIO);
            } else {
                return List.of(SkillLevel.INTERMEDIO, SkillLevel.BASE);
            }
        }
    }
}
