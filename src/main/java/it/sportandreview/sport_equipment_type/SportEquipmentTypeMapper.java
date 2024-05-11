package it.sportandreview.sport_equipment_type;

import org.mapstruct.Mapper;



@Mapper(componentModel = "spring", uses={SportEquipmentType.class})
public interface SportEquipmentTypeMapper {



    SportEquipmentTypeDTO toDto(SportEquipmentType sportEquipmentType);

    SportEquipmentType toEntity(SportEquipmentTypeDTO sportEquipmentTypeDTO);
}
