package it.sportandreview.service_type;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses={ServicesType.class})
public interface ServicesTypeMapper {

    ServicesTypeDTO toDto(ServicesType serviceType);
    ServicesType toEntity(ServicesTypeDTO serviceTypeDto);

}
