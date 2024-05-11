package it.sportandreview.auto_evaluation_sport;

import it.sportandreview.exception.NotFoundException;
import it.sportandreview.auto_evaluation.AutoEvaluationRepository;
import it.sportandreview.auto_evaluation.AutoEvaluation;
import it.sportandreview.game_level.GameLevelMapper;
import it.sportandreview.game_level.GameLevelRepository;
import it.sportandreview.game_level.GameLevel;
import it.sportandreview.sport.SportMapper;
import it.sportandreview.sport.SportRepository;
import it.sportandreview.sport.Sport;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@Mapper(componentModel = "spring", uses={AutoEvaluationSportLevel.class, GameLevelMapper.class, SportMapper.class})
public abstract class AutoEvaluationSportLevelMapper {

    @Autowired
    private AutoEvaluationRepository autoEvaluationRepository;
    @Autowired
    private GameLevelRepository gameLevelRepository;
    @Autowired
    private SportRepository sportRepository;


    @Mapping(source = "autoEvaluation", target = "autoEvaluationId", qualifiedByName = "autoEvaluationToId")
    @Mapping(source = "gameLevel", target = "gameLevelId", qualifiedByName = "gameLevelToId")
    @Mapping(source = "sport", target = "sportId", qualifiedByName = "sportToId")
    public abstract AutoEvaluationSportLevelDTO toDto(AutoEvaluationSportLevel autoEvaluationSportLevel);

    @Mapping(source = "autoEvaluationId", target = "autoEvaluation", qualifiedByName = "autoEvaluationIdToAutoEvaluation")
    @Mapping(source = "gameLevelId", target = "gameLevel", qualifiedByName = "gameLevelIdToGameLevel")
    @Mapping(source = "sportId", target = "sport", qualifiedByName = "sportIdToSport")
    public abstract AutoEvaluationSportLevel toEntity(AutoEvaluationSportLevelDTO autoEvaluationSportLevelDto);

    @Mapping(source = "autoEvaluation", target = "autoEvaluationId", qualifiedByName = "autoEvaluationToId")
    @Mapping(source = "gameLevel", target = "gameLevelId", qualifiedByName = "gameLevelToId")
    @Mapping(source = "sport", target = "sportId", qualifiedByName = "sportToId")
    public abstract List<AutoEvaluationSportLevelDTO> toDto(List<AutoEvaluationSportLevel> autoEvaluationSportLevels);

    @Mapping(source = "autoEvaluationId", target = "autoEvaluation", qualifiedByName = "autoEvaluationIdToAutoEvaluation")
    @Mapping(source = "gameLevelId", target = "gameLevel", qualifiedByName = "gameLevelIdToGameLevel")
    @Mapping(source = "sportId", target = "sport", qualifiedByName = "sportIdToSport")
    public abstract List<AutoEvaluationSportLevel> toEntity(List<AutoEvaluationSportLevelDTO> autoEvaluationSportLevelsDto);


    @Named("autoEvaluationToId")
    public Long autoEvaluationToId(AutoEvaluation a) {
        return a.getId();
    }

    @Named("autoEvaluationIdToAutoEvaluation")
    public AutoEvaluation autoEvaluationIdToAutoEvaluation(Long autoEvaluationId) {
        AutoEvaluation autoEvaluation = autoEvaluationRepository.findById(autoEvaluationId).
                orElseThrow(() -> new NotFoundException("autoEvaluation", "AutoEvaluation" + " not exists into database"));
        return autoEvaluation;
    }

    @Named("gameLevelToId")
    public Long gameLevelToId(GameLevel g) {
        return g.getId();
    }

    @Named("gameLevelIdToGameLevel")
    public GameLevel gameLevelIdToGameLevel(Long gameLevelId) {
        GameLevel gameLevel = gameLevelRepository.findById(gameLevelId).
                orElseThrow(() -> new NotFoundException("gameLevel", "GameLevel" + " not exists into database"));
        return gameLevel;
    }

    @Named("sportToId")
    public Long sportToId(Sport s) {
        return s.getId();
    }

    @Named("sportIdToSport")
    public Sport sportIdToSport(Long sportId) {
        Sport sport = sportRepository.findById(sportId).
                orElseThrow(() -> new NotFoundException("sport", "Sport" + " not exists into database"));
        return sport;
    }
}
