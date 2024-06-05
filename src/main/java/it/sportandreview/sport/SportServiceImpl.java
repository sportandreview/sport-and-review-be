package it.sportandreview.sport;

import it.sportandreview.exception.CreateEntityException;
import it.sportandreview.exception.NotFoundException;
import it.sportandreview.util.Sha256Utils;
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
public class SportServiceImpl implements SportService{

    private final SportRepository sportRepository;
    private final SportMapper sportMapper;

    @Autowired
    public SportServiceImpl(SportRepository sportRepository, SportMapper sportMapper) {
        this.sportRepository = sportRepository;
        this.sportMapper = sportMapper;
    }

    @Override
    @Transactional
    public Long create(SportDTO sportDto) {
        // Setting Sport UUID
        String uuid = StringUtils.isBlank(sportDto.getUuid()) ? Sha256Utils.getEncodedRandomUUID() : sportDto.getUuid();
        sportDto.setUuid(uuid);

        if (Objects.nonNull(sportDto.getId()) && sportRepository.existsById(sportDto.getId())){
            throw new CreateEntityException("Sport", "Sport" + " exists into database");
        }
        Sport sport = sportRepository.save(sportMapper.toEntity(sportDto));
        log.debug("Sport created");
        return sport.getId();
    }

    @Override
    @Transactional
    public SportDTO update(SportDTO sportDto) {
        if (Objects.isNull(sportDto.getId()) || !sportRepository.existsById(sportDto.getId())) {
            throw new NotFoundException("Sport", "Sport" + " not found");
        }

        Sport saved = sportRepository.save(sportMapper.toEntity(sportDto));
        log.debug("Sport updated");

        SportDTO savedDTO = sportMapper.toDto(saved);
        return savedDTO;
    }

    @Override
    @Transactional
    public List<SportDTO> findAll() {
        return sportRepository.findAll().stream().map(sportMapper::toDto).collect(Collectors.toList());
    }
    
}
