package it.sportandreview.join_request;

import it.sportandreview.exception.NotFoundException;
import it.sportandreview.game_match.GameMatch;
import it.sportandreview.game_match.GameMatchMapper;
import it.sportandreview.game_match.GameMatchRepository;
import it.sportandreview.join_request_state.JoinRequestState;
import it.sportandreview.join_request_state.JoinRequestStateMapper;
import it.sportandreview.join_request_state.JoinRequestStateRepository;
import it.sportandreview.player_user.PlayerUserMapper;
import it.sportandreview.user.User;
import it.sportandreview.user.UserRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring", uses={JoinRequest.class, PlayerUserMapper.class, GameMatchMapper.class,
        JoinRequestStateMapper.class})
public abstract class JoinRequestMapper {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private GameMatchRepository gameMatchRepository;
    @Autowired
    private JoinRequestStateRepository joinRequestStateRepository;
    @Mapping(target = "playerUserId", source = "playerUser.id")
    @Mapping(target = "gameMatchId", source = "gameMatch.id")
    @Mapping(target = "joinRequestStateId", source = "joinRequestState.id")
    public abstract JoinRequestDTO toDto(JoinRequest joinRequest);
    @Mapping(source = "playerUserId", target = "playerUser", qualifiedByName = "playerUserIdToPlayerUser")
    @Mapping(source = "gameMatchId", target = "gameMatch", qualifiedByName = "gameMatchIdToGameMatch")
    @Mapping(source = "joinRequestStateId", target = "joinRequestState", qualifiedByName = "joinRequestStateIdToJoinRequestState")
    public abstract JoinRequest toEntity(JoinRequestDTO joinRequestDto);
    @Mapping(target = "playerUserId", source = "playerUser.id")
    @Mapping(target = "gameMatchId", source = "gameMatch.id")
    @Mapping(target = "joinRequestStateId", source = "joinRequestState.id")
    public abstract List<JoinRequestDTO> toDto(List<JoinRequest> joinRequests);
    @Mapping(source = "playerUserId", target = "playerUser", qualifiedByName = "playerUserIdToPlayerUser")
    @Mapping(source = "gameMatchId", target = "gameMatch", qualifiedByName = "gameMatchIdToGameMatch")
    @Mapping(source = "joinRequestStateId", target = "joinRequestState", qualifiedByName = "joinRequestStateIdToJoinRequestState")
    public abstract List<JoinRequest> toEntity(List<JoinRequestDTO> joinRequestsDto);


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
    @Named("joinRequestStateIdToJoinRequestState")
    public JoinRequestState joinRequestStateIdToJoinRequestState(Long joinRequestStateId) {
        JoinRequestState joinRequestState = joinRequestStateRepository.findById(joinRequestStateId).
                orElseThrow(() -> new NotFoundException("joinRequestState", "JoinRequestState" + " not exists into database"));
        return joinRequestState;
    }

}
