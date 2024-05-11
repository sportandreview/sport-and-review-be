package it.sportandreview.sport_equipment_type;

import it.sportandreview.exception.CreateEntityException;
import it.sportandreview.exception.NotFoundException;
import it.sportandreview.sport_equipment.*;
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
public class SportEquipmentTypeServiceImpl implements SportEquipmentTypeService {

    private final SportEquipmentTypeRepository sportEquipmentTypeRepository;
    private final SportEquipmentTypeMapper sportEquipmentTypeMapper;

    @Autowired
    public SportEquipmentTypeServiceImpl(SportEquipmentTypeRepository sportEquipmentTypeRepository, SportEquipmentTypeMapper sportEquipmentTypeMapper) {

        this.sportEquipmentTypeRepository = sportEquipmentTypeRepository;
        this.sportEquipmentTypeMapper = sportEquipmentTypeMapper;
    }

}




