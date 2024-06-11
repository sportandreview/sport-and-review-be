package it.sportandreview.game_match;

import it.sportandreview.field.FieldMapper;
import it.sportandreview.game_level.GameLevelMapper;
import it.sportandreview.match_state.MatchStateMapper;
import it.sportandreview.player_user.PlayerUserMapper;
import it.sportandreview.services.Services;
import it.sportandreview.team.TeamMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring", uses={GameMatch.class, PlayerUserMapper.class, FieldMapper.class,
        MatchStateMapper.class, GameLevelMapper.class, TeamMapper.class})
public abstract class GameMatchMapper {

    public abstract GameMatchDTO toDto(GameMatch gameMatch);
    public abstract GameMatch toEntity(GameMatchDTO gameMatchDto);
    public abstract List<GameMatchDTO> toDto(List<GameMatch> gameMatches);
    public abstract List<GameMatch> toEntity(List<GameMatchDTO> gameMatchesDto);
    @Mapping(target = "fieldId", source = "field.id")
    @Mapping(source = "services", target = "servicesClub", qualifiedByName = "servicesToId")
    public abstract ReducedGameMatchDTO toDtoReduced(GameMatch gameMatch);
    public abstract GameMatch toEntityReduced(ReducedGameMatchDTO reducedGameMatchDTO);


    @Named("servicesToId")
    public static Long servicesToId(Services s) {
        return s.getId();
    }
}
