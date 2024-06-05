package it.sportandreview.field_review;

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
public class FieldReviewServiceImpl implements FieldReviewService {

    private final FieldReviewRepository fieldReviewRepository;
    private final FieldReviewMapper fieldReviewMapper;

    @Autowired
    public FieldReviewServiceImpl(FieldReviewRepository fieldReviewRepository, FieldReviewMapper fieldReviewMapper) {
        this.fieldReviewRepository = fieldReviewRepository;
        this.fieldReviewMapper = fieldReviewMapper;
    }


    @Override
    public Long create(FieldReviewDTO fieldReviewDTO) {
        if (Objects.nonNull(fieldReviewDTO.getId()) && fieldReviewRepository.existsById(fieldReviewDTO.getId())){
            throw new CreateEntityException("FieldReview", "FieldReview" + " exists into database");
        }
        // Setting FieldReview UUID
        String uuid = StringUtils.isBlank(fieldReviewDTO.getUuid()) ? Sha256Utils.getEncodedRandomUUID() : fieldReviewDTO.getUuid();
        fieldReviewDTO.setUuid(uuid);
        FieldReview fieldReview = fieldReviewRepository.save(fieldReviewMapper.toEntity(fieldReviewDTO));
        log.debug("FieldReview created");
        return fieldReview.getId();
    }

    @Override
    public FieldReviewDTO update(FieldReviewDTO fieldReviewDTO) {
        if (Objects.isNull(fieldReviewDTO.getId()) || !fieldReviewRepository.existsById(fieldReviewDTO.getId())) {
            throw new NotFoundException("FieldReview", "FieldReview" + " not found");
        }
        FieldReview saved = fieldReviewRepository.save(fieldReviewMapper.toEntity(fieldReviewDTO));
        log.debug("FieldReview updated");
        FieldReviewDTO savedDTO = fieldReviewMapper.toDto(saved);
        return savedDTO;
    }

    @Override
    public List<FieldReviewDTO> findAll() {
        return fieldReviewRepository.findAll().stream().map(fieldReviewMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public FieldReviewDTO findById(Long fieldReviewId) {
        return fieldReviewRepository.findById(fieldReviewId).map(fieldReviewMapper::toDto).
                orElseThrow(() -> new NotFoundException("FieldReview", "FieldReview" + "not exists into database"));
    }
}
