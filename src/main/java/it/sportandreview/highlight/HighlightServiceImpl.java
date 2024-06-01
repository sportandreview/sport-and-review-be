package it.sportandreview.highlight;

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
public class HighlightServiceImpl implements HighlightService {

    private final HighlightRepository highlightRepository;
    private final HighlightMapper highlightMapper;

    @Autowired
    public HighlightServiceImpl(HighlightRepository highlightRepository, HighlightMapper highlightMapper) {
        this.highlightRepository = highlightRepository;
        this.highlightMapper = highlightMapper;
    }

    @Override
    public Long create(HighlightDTO highlightDto) {
        if (Objects.nonNull(highlightDto.getId()) && highlightRepository.existsById(highlightDto.getId())){
            throw new CreateEntityException("Highlight", "Highlight" + " exists into database");
        }
        // Setting Highlight UUID
        String uuid = StringUtils.isBlank(highlightDto.getUuid()) ? Sha256Utils.getEncodedRandomUUID() : highlightDto.getUuid();
        highlightDto.setUuid(uuid);
        Highlight highlight = highlightRepository.save(highlightMapper.toEntity(highlightDto));
        log.debug("Highlight created");
        return highlight.getId();
    }


    @Override
    public HighlightDTO update(HighlightDTO highlightDto) {
        if (Objects.isNull(highlightDto.getId()) || !highlightRepository.existsById(highlightDto.getId())) {
            throw new NotFoundException("Highlight", "Highlight" + " not found");
        }

        Highlight highlight = highlightRepository.save(highlightMapper.toEntity(highlightDto));
        log.debug("Highlight updated");

        HighlightDTO savedDTO = highlightMapper.toDto(highlight);
        return savedDTO;
    }

    @Override
    public List<HighlightDTO> findAll() {
        return highlightRepository.findAll().stream().map(highlightMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public HighlightDTO findById(Long id) {
        return highlightRepository.findById(id).map(highlightMapper::toDto).
                orElseThrow(() -> new NotFoundException("highlight", "Highlight" + "not exists into database"));
    }
}
