package it.sportandreview.ground_type;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses={GroundType.class})
public interface GroundTypeMapper {

    GroundTypeDTO toDto(GroundType groundType);
    GroundType toEntity(GroundTypeDTO groundTypeDto);

}
