package it.sportandreview.services;

import it.sportandreview.field_size.FieldSizeMapper;
import it.sportandreview.service_type.ServicesTypeMapper;
import it.sportandreview.sport_equipment.SportEquipmentMapper;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring", uses={Services.class, ServicesTypeMapper.class, SportEquipmentMapper.class, FieldSizeMapper.class})
public interface ServicesMapper {

    ServicesDTO toDto(Services service);
    Services toEntity(ServicesDTO serviceDto);
    Set<Services> toEntity(Set<ServicesDTO> serviceDto);
    Set<ServicesDTO> toDto(Set<Services> service);
}
