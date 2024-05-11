package it.sportandreview.club;

import it.sportandreview.opening_day.OpeningDayMapper;
import it.sportandreview.owner.OwnerMapper;
import it.sportandreview.services.ServicesMapper;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses={Club.class, OwnerMapper.class, ServicesMapper.class, OpeningDayMapper.class})
public interface ClubMapper {

    ClubDTO toDto(Club club);
    Club toEntity(ClubDTO clubDto);
   List<ClubDTO> toDto(List<Club> clubs);
   List<Club> toEntity(List<ClubDTO> clubDtos);


}
