package it.sportandreview.auto_evaluation;

import it.sportandreview.auto_evaluation_sport.AutoEvaluationSportLevel;
import it.sportandreview.auto_evaluation_sport.AutoEvaluationSportLevelDTO;
import it.sportandreview.auto_evaluation_sport.AutoEvaluationSportLevelMapper;
import it.sportandreview.auto_evaluation_sport.AutoEvaluationSportLevelRepository;
import it.sportandreview.player_user.PlayerUserMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses={AutoEvaluation.class, PlayerUserMapper.class, AutoEvaluationSportLevelMapper.class})
public abstract class AutoEvaluationMapper {

    @Autowired
    private AutoEvaluationSportLevelRepository autoEvaluationSportLevelRepository;
    @Autowired
    private AutoEvaluationSportLevelMapper autoEvaluationSportLevelMapper;

    @Mapping(target = "playerUserId", source = "playerUser.id")
    @Mapping(source = "autoEvaluation", target = "sportLevels", qualifiedByName = "autoEvaluationToSportLevels")
    public abstract AutoEvaluationDTO toDto(AutoEvaluation autoEvaluation);

    @Mapping(target = "playerUser.id", source = "playerUserId")
    public abstract AutoEvaluation toEntity(AutoEvaluationDTO autoEvaluationDto);

    @Mapping(target = "playerUserId", source = "playerUser.id")
    @Mapping(source = "autoEvaluation", target = "sportLevels", qualifiedByName = "autoEvaluationToSportLevels")
    public abstract List<AutoEvaluationDTO> toDto(List<AutoEvaluation> autoEvaluation);

    @Mapping(target = "playerUser.id", source = "playerUserId")
    public abstract List<AutoEvaluation> toEntity(List<AutoEvaluationDTO> autoEvaluationDto);


    @Named("autoEvaluationToSportLevels")
    public Set<AutoEvaluationSportLevelDTO> autoEvaluationToSportLevels(AutoEvaluation autoEvaluation) {
        Set<AutoEvaluationSportLevelDTO> autoEvaluationSportLevels = autoEvaluationSportLevelRepository.
                findByAutoEvaluationId(autoEvaluation.getId()).stream()
                .map(autoEvaluationSportLevel -> autoEvaluationSportLevelMapper.toDto(autoEvaluationSportLevel))
                .collect(Collectors.toSet());
        return autoEvaluationSportLevels;
    }
}
