package it.sportandreview.gender_type;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class GenderTypeServiceImpl implements GenderTypeService {

    private final GenderTypeRepository repository;
    private final GenderTypeMapper mapper;

    @Autowired
    public GenderTypeServiceImpl(GenderTypeMapper mapper, GenderTypeRepository repository) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public List<GenderTypeDTO> findAll() {
        return repository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }


}
