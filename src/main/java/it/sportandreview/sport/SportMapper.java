package it.sportandreview.sport;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses={Sport.class})
public interface SportMapper {

    SportDTO toDto(Sport sport);
    Sport toEntity(SportDTO sportDto);

}
