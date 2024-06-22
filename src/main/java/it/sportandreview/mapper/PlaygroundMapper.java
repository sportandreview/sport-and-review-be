package it.sportandreview.mapper;

import it.sportandreview.dto.request.PlaygroundRequestDTO;
import it.sportandreview.dto.response.PlaygroundResponseDTO;
import it.sportandreview.entity.Playground;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {TimeSlotMapper.class})
public interface PlaygroundMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "sportFacility", ignore = true)
    @Mapping(target = "timeSlots", ignore = true)
    Playground toEntity(PlaygroundRequestDTO dto);

    PlaygroundResponseDTO toDto(Playground playground);

    void updateEntityFromDto(PlaygroundRequestDTO dto, @MappingTarget Playground entity);
}
