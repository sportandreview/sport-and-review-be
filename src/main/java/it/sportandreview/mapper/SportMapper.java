package it.sportandreview.mapper;

import it.sportandreview.dto.response.SportResponseDTO;
import it.sportandreview.entity.Sport;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SportMapper {

    @Mapping(source = "sportType.description", target = "description")
    SportResponseDTO toDto(Sport sport);

    @Mapping(target = "id", ignore = true)
    Sport toEntity(SportResponseDTO sportDto);
}
