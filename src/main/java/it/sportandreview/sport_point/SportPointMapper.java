package it.sportandreview.sport_point;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses={SportPoint.class})
public interface SportPointMapper {

    SportPointDTO toDto(SportPoint sportPoint);
    SportPoint toEntity(SportPointDTO sportPointDto);

}
