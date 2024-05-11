package it.sportandreview.match_state;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses={MatchState.class})
public interface MatchStateMapper {

    MatchStateDTO toDto(MatchState matchState);
    MatchState toEntity(MatchStateDTO MatchStateDto);

}
