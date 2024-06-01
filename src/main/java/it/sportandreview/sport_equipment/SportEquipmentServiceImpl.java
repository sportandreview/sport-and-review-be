package it.sportandreview.sport_equipment;

import it.sportandreview.exception.CreateEntityException;
import it.sportandreview.exception.NotFoundException;
import it.sportandreview.util.Sha256Utils;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Service
@Log4j2
public class SportEquipmentServiceImpl implements SportEquipmentService {

    private final SportEquipmentRepository sportEquipmentRepository;
    private final SportEquipmentMapper sportEquipmentMapper;

    @Autowired
    public SportEquipmentServiceImpl(SportEquipmentRepository sportEquipmentRepository, SportEquipmentMapper sportEquipmentMapper) {

        this.sportEquipmentRepository = sportEquipmentRepository;
        this.sportEquipmentMapper = sportEquipmentMapper;
    }

    @Override
    public Long create(SportEquipmentDTO sportEquipmentDto) {
        if (Objects.nonNull(sportEquipmentDto.getId()) && sportEquipmentRepository.existsById(sportEquipmentDto.getId())){
            throw new CreateEntityException("SportEquipment", "SportEquipment" + " exists into database");
        }
        // Setting SportEquipment UUID
        String uuid = StringUtils.isBlank(sportEquipmentDto.getUuid()) ? Sha256Utils.getEncodedRandomUUID() : sportEquipmentDto.getUuid();
        sportEquipmentDto.setUuid(uuid);
        SportEquipment sportEquipment = sportEquipmentRepository.save(sportEquipmentMapper.toEntity(sportEquipmentDto));
        log.debug("SportEquipment created");
        return sportEquipment.getId();
    }

    @Override
    public SportEquipmentDTO update(SportEquipmentDTO sportEquipmentDto) {
        if (Objects.isNull(sportEquipmentDto.getId()) || !sportEquipmentRepository.existsById(sportEquipmentDto.getId())) {
            throw new NotFoundException("SportEquipment", "SportEquipment" + " not found");
        }
        SportEquipment saved = sportEquipmentRepository.save(sportEquipmentMapper.toEntity(sportEquipmentDto));
        log.debug("SportEquipment updated");
        SportEquipmentDTO savedDTO = sportEquipmentMapper.toDto(saved);
        return savedDTO;
    }

    @Override
    public List<SportEquipmentDTO> findAll() {
        return sportEquipmentRepository.findAll().stream().map(sportEquipmentMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public SportEquipmentDTO findById(Long sportEquipmentId) {
        return sportEquipmentRepository.findById(sportEquipmentId).map(sportEquipmentMapper::toDto).
                orElseThrow(() -> new NotFoundException("sportEquipment", "SportEquipment" + "not exists into database"));
    }
}






