package it.sportandreview.field;

import it.sportandreview.club.ClubMapper;
import it.sportandreview.ground_type.GroundTypeMapper;
import it.sportandreview.services.ServicesMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses={Field.class, GroundTypeMapper.class, ClubMapper.class, ServicesMapper.class})
public interface FieldMapper {

    FieldDTO toDto(Field field);
    Field toEntity(FieldDTO fieldDto);
    FieldReducedDTO toDtoReduced(Field field);
    Field toEntityReduced(FieldReducedDTO fieldDto);

}
