package it.sportandreview.field_size;

import it.sportandreview.exception.CreateEntityException;
import it.sportandreview.exception.NotFoundException;
import it.sportandreview.utils.Sha256Utils;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Service
@Log4j2
public class FieldSizeServiceImpl implements FieldSizeService {

    private final FieldSizeRepository fieldSizeRepository;
    private final FieldSizeMapper FieldSizeMapper;

    @Autowired
    public FieldSizeServiceImpl(FieldSizeRepository fieldSizeRepository,
                                FieldSizeMapper FieldSizeMapper) {
        this.fieldSizeRepository = fieldSizeRepository;
        this.FieldSizeMapper = FieldSizeMapper;
    }

    @Override
    public Long create(FieldSizeDTO fieldSizeDTO) {
        if (Objects.nonNull(fieldSizeDTO.getId()) && fieldSizeRepository.existsById(fieldSizeDTO.getId())){
            throw new CreateEntityException("FieldSize", "FieldSize" + " exists into database");
        }
        // Setting FieldSize UUID
        String uuid = StringUtils.isBlank(fieldSizeDTO.getUuid()) ? Sha256Utils.getEncodedRandomUUID() : fieldSizeDTO.getUuid();
        fieldSizeDTO.setUuid(uuid);
        FieldSize fieldSize = fieldSizeRepository.save(FieldSizeMapper.toEntity(fieldSizeDTO));
        log.debug("FieldSize created");
        return fieldSize.getId();
    }

    @Override
    public FieldSizeDTO update(FieldSizeDTO fieldSizeDTO) {
        if (Objects.isNull(fieldSizeDTO.getId()) || !fieldSizeRepository.existsById(fieldSizeDTO.getId())) {
            throw new NotFoundException("FieldSize", "FieldSize" + " not found");
        }
        FieldSize saved = fieldSizeRepository.save(FieldSizeMapper.toEntity(fieldSizeDTO));
        log.debug("FieldSize updated");
        FieldSizeDTO savedDTO = FieldSizeMapper.toDto(saved);
        return savedDTO;
    }
    @Override
    public List<FieldSizeDTO> findAll() {
        return fieldSizeRepository.findAll().stream().map(FieldSizeMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public FieldSizeDTO findById(Long fieldSizeId) {
        return fieldSizeRepository.findById(fieldSizeId).map(FieldSizeMapper::toDto).
                orElseThrow(() -> new NotFoundException("fieldSize", "FieldSize" + "not exists into database"));
    }

}






