package it.sportandreview.player_review;

import it.sportandreview.exception.NotFoundException;
import it.sportandreview.game_match.GameMatch;
import it.sportandreview.game_match.GameMatchMapper;
import it.sportandreview.game_match.GameMatchRepository;
import it.sportandreview.join_request_state.JoinRequestState;
import it.sportandreview.player_user.PlayerUserMapper;
import it.sportandreview.sport.Sport;
import it.sportandreview.sport.SportMapper;
import it.sportandreview.sport.SportRepository;
import it.sportandreview.user.User;
import it.sportandreview.user.UserRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", uses={PlayerReview.class, PlayerUserMapper.class, GameMatchMapper.class, SportMapper.class})
public abstract class PlayerReviewMapper {
    @Autowired
    private GameMatchRepository gameMatchRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SportRepository sportRepository;

    @Mapping(target = "playerUserId", source = "playerUser.id")
    @Mapping(target = "gameMatchId", source = "gameMatch.id")
    @Mapping(target = "sportId", source = "sport.id")
    @Mapping(target = "madeById", source = "madeBy.id")
    public abstract PlayerReviewDTO toDto(PlayerReview playerReview);
    @Mapping(source = "playerUserId", target = "playerUser", qualifiedByName = "playerUserIdToPlayerUser")
    @Mapping(source = "gameMatchId", target = "gameMatch", qualifiedByName = "gameMatchIdToGameMatch")
    @Mapping(source = "sportId", target = "sport", qualifiedByName = "SportIdToSport")
    @Mapping(source = "madeById", target = "madeBy", qualifiedByName = "madeByIdToMadeBy")
    public abstract PlayerReview toEntity(PlayerReviewDTO PlayerReviewDto);

    @Named("gameMatchIdToGameMatch")
    public GameMatch gameMatchIdToGameMatch(Long gameMatchId) {
        GameMatch gameMatch = gameMatchRepository.findById(gameMatchId).
                orElseThrow(() -> new NotFoundException("gameMatch", "GameMatch" + " not exists into database"));
        return gameMatch;
    }
    @Named("playerUserIdToPlayerUser")
    public User playerUserIdToPlayerUser(Long playerUserId) {
        User playerUser = userRepository.findById(playerUserId).
                orElseThrow(() -> new NotFoundException("playerUser", "PlayerUser" + " not exists into database"));
        return playerUser;
    }
    @Named("SportIdToSport")
    public Sport SportIdToSport(Long sportId) {
        Sport sport = sportRepository.findById(sportId).
                orElseThrow(() -> new NotFoundException("sport", "Sport" + " not exists into database"));
        return sport;
    }
    @Named("madeByIdToMadeBy")
    public User madeByIdToMadeBy(Long madeById) {
        User playerUser = userRepository.findById(madeById).
                orElseThrow(() -> new NotFoundException("playerUser", "PlayerUser" + " not exists into database"));
        return playerUser;
    }

}
