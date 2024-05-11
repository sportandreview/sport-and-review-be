package it.sportandreview.team;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses={Team.class})
public interface TeamMapper {

    TeamDTO toDto(Team team);
    Team toEntity(TeamDTO teamDto);

}
