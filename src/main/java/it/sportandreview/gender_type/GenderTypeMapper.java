package it.sportandreview.gender_type;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses={GenderType.class})
public interface GenderTypeMapper {

    GenderTypeDTO toDto(GenderType genderType);
    GenderType toEntity(GenderTypeDTO genderTypeDto);

}
