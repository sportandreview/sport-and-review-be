package it.sportandreview.auto_evaluation;

import it.sportandreview.auto_evaluation_sport.AutoEvaluationSportLevel;
import it.sportandreview.auto_evaluation_sport.AutoEvaluationSportLevelDTO;
import it.sportandreview.auto_evaluation_sport.AutoEvaluationSportLevelMapper;
import it.sportandreview.auto_evaluation_sport.AutoEvaluationSportLevelRepository;
import it.sportandreview.exception.CreateEntityException;
import it.sportandreview.exception.NotFoundException;
import it.sportandreview.util.Sha256Utils;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@Log4j2
public class AutoEvaluationServiceImpl implements AutoEvaluationService {

    private final AutoEvaluationRepository autoEvaluationRepository;
    private final AutoEvaluationMapper autoEvaluationMapper;
    private final AutoEvaluationSportLevelRepository autoEvaluationSportLevelRepository;
    private final AutoEvaluationSportLevelMapper autoEvaluationSportLevelMapper;

    @Autowired
    public AutoEvaluationServiceImpl(AutoEvaluationRepository autoEvaluationRepository, AutoEvaluationMapper autoEvaluationMapper,
                                     AutoEvaluationSportLevelRepository autoEvaluationSportLevelRepository, AutoEvaluationSportLevelMapper autoEvaluationSportLevelMapper) {
        this.autoEvaluationRepository = autoEvaluationRepository;
        this.autoEvaluationMapper = autoEvaluationMapper;
        this.autoEvaluationSportLevelRepository = autoEvaluationSportLevelRepository;
        this.autoEvaluationSportLevelMapper = autoEvaluationSportLevelMapper;
    }

    @Override
    @Transactional
    public Long create(AutoEvaluationDTO autoEvaluationDto) {
        settingUuid(autoEvaluationDto);
        if (Objects.nonNull(autoEvaluationDto.getId()) && autoEvaluationRepository.existsById(autoEvaluationDto.getId())){
            throw new CreateEntityException("AutoEvaluation", "AutoEvaluation" + " exists into database");
        }
        autoEvaluationDto.setBmi(calculateBMI(autoEvaluationDto.getWeight(), autoEvaluationDto.getHeight()));
        AutoEvaluation autoEvaluation = autoEvaluationRepository.save(autoEvaluationMapper.toEntity(autoEvaluationDto));
        createAutoEvaluationSportLevels(autoEvaluationDto, autoEvaluation.getId(), Boolean.FALSE);
        log.debug("AutoEvaluation created");
        return autoEvaluation.getId();
    }

    @Override
    public List<AutoEvaluationDTO> createAll(List<AutoEvaluationDTO> autoEvaluationsDto) {
        autoEvaluationsDto.forEach(autoEvaluationDto -> {
            settingUuid(autoEvaluationDto);
        });
        if (!CollectionUtils.isEmpty(autoEvaluationsDto)) {
           List<AutoEvaluation> list = autoEvaluationMapper.toEntity(autoEvaluationsDto).stream().collect(Collectors.toList());
           list.forEach(autoEvaluation -> {
               autoEvaluation.setBmi(calculateBMI(autoEvaluation.getWeight(), autoEvaluation.getHeight()));
           });
           List<AutoEvaluation> autoEvaluations = autoEvaluationRepository.saveAll(list);
           List<AutoEvaluationDTO> autoEvaluationList = new ArrayList<>();
           autoEvaluations.forEach(autoEvaluation -> {
               AutoEvaluationDTO autoEvaluationDTO = autoEvaluationMapper.toDto(autoEvaluation);
               autoEvaluationList.add(autoEvaluationDTO);
               createAutoEvaluationSportLevels(autoEvaluationDTO, autoEvaluation.getId(), Boolean.FALSE);
           });
           return autoEvaluationList;
        }
        return new ArrayList<>();
    }

    @Override
    @Transactional
    public AutoEvaluationDTO update(AutoEvaluationDTO autoEvaluationDto) {
        if (Objects.isNull(autoEvaluationDto.getId()) || !autoEvaluationRepository.existsById(autoEvaluationDto.getId())) {
            throw new NotFoundException("AutoEvaluation", "AutoEvaluation" + " not found");
        }
        autoEvaluationDto.setBmi(calculateBMI(autoEvaluationDto.getWeight(), autoEvaluationDto.getHeight()));
        AutoEvaluation saved = autoEvaluationRepository.save(autoEvaluationMapper.toEntity(autoEvaluationDto));
        createAutoEvaluationSportLevels(autoEvaluationDto, saved.getId(), Boolean.TRUE);
        log.debug("AutoEvaluation updated");
        AutoEvaluationDTO savedDTO = autoEvaluationMapper.toDto(saved);
        return savedDTO;
    }

    @Override
    @Transactional
    public List<AutoEvaluationDTO> findByPlayerUserId(Long playerUserId) {
        return autoEvaluationRepository.findByPlayerUserId(playerUserId).stream().map(autoEvaluationMapper::toDto).collect(Collectors.toList());
    }

    private void settingUuid(AutoEvaluationDTO autoEvaluationDto){
        // Setting AutoEvaluation UUID
        String uuid = StringUtils.isBlank(autoEvaluationDto.getUuid()) ? Sha256Utils.getEncodedRandomUUID() : autoEvaluationDto.getUuid();
        autoEvaluationDto.setUuid(uuid);
    }

    private void createAutoEvaluationSportLevels(AutoEvaluationDTO autoEvaluationDTO, Long autoEvaluationId, Boolean update) {
        List<Long> autoEvaluationSportLevelsId = new ArrayList<>();
        Set<AutoEvaluationSportLevelDTO> autoEvaluationSportLevelSet = autoEvaluationDTO.getSportLevels();
        autoEvaluationSportLevelSet.forEach(autoEvaluationSportLevel -> {
            Long autoEvaluation = Objects.isNull(autoEvaluationSportLevel.getAutoEvaluationId()) ? autoEvaluationId : autoEvaluationSportLevel.getAutoEvaluationId();
            autoEvaluationSportLevel.setAutoEvaluationId(autoEvaluation);
            // Setting AutoEvaluationSportLevel UUID
            String uuid = StringUtils.isBlank(autoEvaluationSportLevel.getUuid()) ? Sha256Utils.getEncodedRandomUUID() : autoEvaluationSportLevel.getUuid();
            autoEvaluationSportLevel.setUuid(uuid);
            AutoEvaluationSportLevel saved = autoEvaluationSportLevelRepository.save(autoEvaluationSportLevelMapper.toEntity(autoEvaluationSportLevel));
            autoEvaluationSportLevelsId.add(saved.getId());
        });
        if (Boolean.TRUE.equals(update)) {
           List<AutoEvaluationSportLevel> list = autoEvaluationSportLevelRepository.findByAutoEvaluationId(autoEvaluationId);
           list.forEach(autoEvaluationSportLevel -> {
               if (!autoEvaluationSportLevelsId.contains(autoEvaluationSportLevel.getId())) {
                   autoEvaluationSportLevelRepository.deleteById(autoEvaluationSportLevel.getId());
               }
           });
        }
    }

    private Double calculateBMI(Double weight, Double height) {
        if (Objects.isNull(weight) || Objects.isNull(height)) {
            return null;
        }
        double bmi = weight / (height * height);
        bmi = Math.round(bmi * 100.0) / 100.0;

        return bmi;
    }

}






