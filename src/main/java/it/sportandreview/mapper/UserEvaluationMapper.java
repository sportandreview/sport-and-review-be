package it.sportandreview.mapper;

import it.sportandreview.dto.request.UserEvaluationRequestDTO;
import it.sportandreview.dto.response.UserEvaluationResponseDTO;
import it.sportandreview.entity.UserEvaluation;
import it.sportandreview.enums.SkillLevel;
import it.sportandreview.enums.TrainingFrequency;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = {SportMapperHelper.class})
public interface UserEvaluationMapper {

    @Mapping(source = "sportId", target = "sport")
    @Mapping(source = "trainingFrequency", target = "trainingFrequency", qualifiedByName = "stringToTrainingFrequency")
    @Mapping(source = "skillLevel", target = "skillLevel", qualifiedByName = "stringToSkillLevel")
    UserEvaluation toEntity(UserEvaluationRequestDTO dto);

    @Mapping(source = "sport.id", target = "sportId")
    @Mapping(source = "trainingFrequency", target = "trainingFrequency", qualifiedByName = "trainingFrequencyToString")
    @Mapping(source = "skillLevel", target = "skillLevel", qualifiedByName = "skillLevelToString")
    UserEvaluationResponseDTO toDto(UserEvaluation entity);

    @Named("stringToTrainingFrequency")
    default TrainingFrequency stringToTrainingFrequency(String trainingFrequency) {
        return TrainingFrequency.valueOf(trainingFrequency.toUpperCase().replace("-", "_").replace(" ", ""));
    }

    @Named("trainingFrequencyToString")
    default String trainingFrequencyToString(TrainingFrequency trainingFrequency) {
        return trainingFrequency.getDescription();
    }

    @Named("stringToSkillLevel")
    default SkillLevel stringToSkillLevel(String skillLevel) {
        return SkillLevel.valueOf(skillLevel.toUpperCase().replace(" ", "_"));
    }

    @Named("skillLevelToString")
    default String skillLevelToString(SkillLevel skillLevel) {
        return skillLevel.getDescription();
    }
}
