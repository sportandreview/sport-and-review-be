package it.sportandreview.sport_equipment_type;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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




