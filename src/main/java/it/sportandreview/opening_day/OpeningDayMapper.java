package it.sportandreview.opening_day;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses={OpeningDay.class})
public interface OpeningDayMapper {

    OpeningDayDTO toDto(OpeningDay openingDay);
    OpeningDay toEntity(OpeningDayDTO openingDayDto);

}
