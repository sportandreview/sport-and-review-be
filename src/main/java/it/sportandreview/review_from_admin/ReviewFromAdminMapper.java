package it.sportandreview.review_from_admin;

import it.sportandreview.exception.NotFoundException;
import it.sportandreview.game_match.GameMatch;
import it.sportandreview.game_match.GameMatchMapper;
import it.sportandreview.game_match.GameMatchRepository;
import it.sportandreview.user.User;
import it.sportandreview.user.UserMapperOld;
import it.sportandreview.user.UserRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", uses={ReviewFromAdmin.class, UserMapperOld.class, GameMatchMapper.class})
public abstract class ReviewFromAdminMapper {

    @Autowired
    UserRepository userRepository;
    @Autowired
    GameMatchRepository gameMatchRepository;

    @Mapping(target = "playerUserId", source = "playerUser.id")
    @Mapping(target = "gameMatchId", source = "gameMatch.id")
    @Mapping(target = "adminUserId", source = "adminUser.id")
    public abstract ReviewFromAdminDTO toDto(ReviewFromAdmin reviewFromAdmin);
    @Mapping(source = "playerUserId", target = "playerUser", qualifiedByName = "playerUserIdToPlayerUser")
    @Mapping(source = "gameMatchId", target = "gameMatch", qualifiedByName = "gameMatchIdToGameMatch")
    @Mapping(source = "adminUserId", target = "adminUser", qualifiedByName = "AdminUserIdToAdminUser")
    public abstract ReviewFromAdmin toEntity(ReviewFromAdminDTO reviewFromAdminDTO);

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
    @Named("AdminUserIdToAdminUser")
    public User AdminUserIdToAdminUser(Long adminUserId) {
        User user = userRepository.findById(adminUserId).
                orElseThrow(() -> new NotFoundException("user", "User" + " not exists into database"));
        return user;
    }


}
