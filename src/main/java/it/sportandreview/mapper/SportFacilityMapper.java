package it.sportandreview.mapper;

import it.sportandreview.dto.request.AddressRequestDTO;
import it.sportandreview.dto.request.SportFacilityRequestDTO;
import it.sportandreview.dto.response.AddressResponseDTO;
import it.sportandreview.dto.response.SportFacilityResponseDTO;
import it.sportandreview.entity.Address;
import it.sportandreview.entity.SportFacility;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.time.DayOfWeek;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface SportFacilityMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "address", target = "address")
    @Mapping(target = "owner", ignore = true)
    @Mapping(source = "openDays", target = "openDays", qualifiedByName = "stringToDayOfWeek")
    SportFacility toEntity(SportFacilityRequestDTO dto);

    SportFacilityResponseDTO toDto(SportFacility sportFacility);

    Address toEntity(AddressRequestDTO dto);

    AddressResponseDTO toDto(Address address);

    @Named("stringToDayOfWeek")
    default Set<DayOfWeek> stringToDayOfWeek(Set<String> days) {
        return days.stream()
                .map(day -> DayOfWeek.valueOf(day.toUpperCase()))
                .collect(Collectors.toSet());
    }

    void updateEntityFromDto(SportFacilityRequestDTO dto, @MappingTarget SportFacility entity);
}
