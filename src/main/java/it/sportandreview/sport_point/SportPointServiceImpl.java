package it.sportandreview.sport_point;

import it.sportandreview.exception.CreateEntityException;
import it.sportandreview.exception.NotFoundException;
import it.sportandreview.utils.Sha256Utils;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Service
@Log4j2
public class SportPointServiceImpl implements SportPointService{

    private final SportPointRepository sportPointRepository;
    private final SportPointMapper sportPointMapper;

    @Autowired
    public SportPointServiceImpl(SportPointRepository sportPointRepository, SportPointMapper sportPointMapper) {
        this.sportPointRepository = sportPointRepository;
        this.sportPointMapper = sportPointMapper;
    }

    @Override
    @Transactional
    public Long create(SportPointDTO sportPointDto) {
        // Setting SportPoint UUID
        String uuid = StringUtils.isBlank(sportPointDto.getUuid()) ? Sha256Utils.getEncodedRandomUUID() : sportPointDto.getUuid();
        sportPointDto.setUuid(uuid);

        if (Objects.nonNull(sportPointDto.getId()) && sportPointRepository.existsById(sportPointDto.getId())){
            throw new CreateEntityException("SportPoint", "SportPoint" + " exists into database");
        }
        SportPoint sportPoint = sportPointRepository.save(sportPointMapper.toEntity(sportPointDto));
        log.debug("SportPoint created");
        return sportPoint.getId();
    }

    @Override
    @Transactional
    public SportPointDTO update(SportPointDTO sportPointDto) {
        if (Objects.isNull(sportPointDto.getId()) || !sportPointRepository.existsById(sportPointDto.getId())) {
            throw new NotFoundException("SportPoint", "SportPoint" + " not found");
        }

        SportPoint saved = sportPointRepository.save(sportPointMapper.toEntity(sportPointDto));
        log.debug("SportPoint updated");

        SportPointDTO savedDTO = sportPointMapper.toDto(saved);
        return savedDTO;
    }

    @Override
    @Transactional
    public List<SportPointDTO> findAll() {
        return sportPointRepository.findAll().stream().map(sportPointMapper::toDto).collect(Collectors.toList());
    }
    
}
