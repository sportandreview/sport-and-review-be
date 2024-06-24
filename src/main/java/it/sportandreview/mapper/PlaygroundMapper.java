package it.sportandreview.mapper;

import it.sportandreview.dto.request.PlaygroundRequestDTO;
import it.sportandreview.dto.response.PlaygroundResponseDTO;
import it.sportandreview.entity.Playground;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.DayOfWeek;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface PlaygroundMapper {

    @Mapping(target = "openDays", expression = "java(convertOpenDays(playgroundRequestDTO.getOpenDays()))")
    Playground toEntity(PlaygroundRequestDTO playgroundRequestDTO);

    PlaygroundResponseDTO toDto(Playground playground);

    default Set<DayOfWeek> convertOpenDays(Set<String> openDays) {
        return openDays.stream()
                .map(DayOfWeek::valueOf)
                .collect(Collectors.toSet());
    }
}
