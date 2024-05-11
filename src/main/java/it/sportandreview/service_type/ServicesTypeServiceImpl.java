package it.sportandreview.service_type;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Log4j2
public class ServicesTypeServiceImpl implements ServicesTypeService {

    private final ServicesTypeRepository servicesTypeRepository;
    private final ServicesTypeMapper servicesTypeMapper;

    @Autowired
    public ServicesTypeServiceImpl(ServicesTypeRepository servicesTypeRepository, ServicesTypeMapper servicesTypeMapper) {
        this.servicesTypeRepository = servicesTypeRepository;
        this.servicesTypeMapper = servicesTypeMapper;
    }

    @Override
    @Transactional
    public List<ServicesTypeDTO> findAll() {
        return servicesTypeRepository.findAll().stream().map(servicesTypeMapper::toDto).collect(Collectors.toList());
    }
}
