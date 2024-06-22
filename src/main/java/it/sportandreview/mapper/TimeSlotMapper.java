package it.sportandreview.mapper;

import it.sportandreview.dto.request.TimeSlotRequestDTO;
import it.sportandreview.dto.response.TimeSlotResponseDTO;
import it.sportandreview.entity.TimeSlot;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TimeSlotMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "playground", ignore = true)
    TimeSlot toEntity(TimeSlotRequestDTO dto);

    TimeSlotResponseDTO toDto(TimeSlot timeSlot);

    void updateEntityFromDto(TimeSlotRequestDTO dto, @MappingTarget TimeSlot entity);
}
