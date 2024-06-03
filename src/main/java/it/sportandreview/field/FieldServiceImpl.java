package it.sportandreview.field;

import it.sportandreview.exception.CreateEntityException;
import it.sportandreview.exception.NotFoundException;
import it.sportandreview.util.Sha256Utils;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@Log4j2
public class FieldServiceImpl implements FieldService {

    private final FieldRepository fieldRepository;
    private final FieldMapper fieldMapper;

    @Autowired
    public FieldServiceImpl(FieldRepository fieldRepository, FieldMapper fieldMapper) {
        this.fieldRepository = fieldRepository;
        this.fieldMapper = fieldMapper;
    }


    @Override
    public long create(FieldDTO fieldDTO) {
        if (Objects.nonNull(fieldDTO.getId()) && fieldRepository.existsById(fieldDTO.getId())){
            throw new CreateEntityException("Field", "Field" + " exists into database");
        }
        // Setting Field UUID
        String uuid = StringUtils.isBlank(fieldDTO.getUuid()) ? Sha256Utils.getEncodedRandomUUID() : fieldDTO.getUuid();
        fieldDTO.setUuid(uuid);
        Field field = fieldRepository.save(fieldMapper.toEntity(fieldDTO));
        log.debug("Field created");
        return field.getId();
    }

    @Override
    public FieldDTO update(FieldDTO fieldDTO) {
        if (Objects.isNull(fieldDTO.getId()) || !fieldRepository.existsById(fieldDTO.getId())) {
            throw new NotFoundException("Field", "Field" + " not found");
        }
        Field saved = fieldRepository.save(fieldMapper.toEntity(fieldDTO));
        log.debug("Field updated");
        FieldDTO savedDTO = fieldMapper.toDto(saved);
        return savedDTO;
    }

    @Override
    public FieldDTO findById(Long id) {
        return fieldRepository.findById(id).map(fieldMapper::toDto).
                orElseThrow(() -> new NotFoundException("field", "Field" + "not exists into database"));
    }

    @Override
    public List<FieldDTO> findByClubId(Long clubId) {
        return fieldRepository.findByClubId(clubId).stream().map(fieldMapper::toDto).collect(Collectors.toList());

    }

    @Override
    public Set<FieldDTO> findByClubIdOrderByRatingDesc(Long clubId) {
       return fieldRepository.findTop5ByClubIdOrderByRatingDesc(clubId).stream().
               map(fieldMapper::toDto).collect(Collectors.toSet());
    }

    @Override
    public PagingFieldDTO findAll(Integer pageSize, Integer pageNumber) {
            Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("id"));
            Page<Field> page = fieldRepository.findAll(pageable);
        List<Field> fields = page.getContent();
        return PagingFieldDTO.builder().pageSize(pageSize).
                fields(fields.stream().map(fieldMapper::toDto).collect(Collectors.toList())).build();
        }
    }





