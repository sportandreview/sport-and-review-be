package it.sportandreview.club_review;

import it.sportandreview.club.Club;
import it.sportandreview.club.ClubMapper;
import it.sportandreview.club.ClubRepository;
import it.sportandreview.exception.NotFoundException;
import it.sportandreview.game_match.GameMatch;
import it.sportandreview.game_match.GameMatchMapper;
import it.sportandreview.game_match.GameMatchRepository;
import it.sportandreview.player_user.PlayerUserMapper;
import it.sportandreview.user.User;
import it.sportandreview.user.UserRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", uses={ClubReview.class, ClubMapper.class, GameMatchMapper.class, PlayerUserMapper.class})
public abstract class ClubReviewMapper {
    @Autowired
    private GameMatchRepository gameMatchRepository;
    @Autowired
    private ClubRepository clubRepository;
    @Autowired
    private UserRepository playerUserRepository;

    @Mapping(source = "gameMatch.id", target = "gameMatchId")
    @Mapping(source = "club.id", target = "clubId")
    @Mapping(source = "playerUser.id", target = "playerUserId")
   public abstract ClubReviewDTO toDto(ClubReview clubReview);
    @Mapping(source = "gameMatchId", target = "gameMatch", qualifiedByName = "gameMatchIdToGameMatch")
    @Mapping(source = "clubId", target = "club", qualifiedByName = "clubIdToClub")
    @Mapping(source = "playerUserId", target = "playerUser", qualifiedByName = "playerUserIdToUser")
    public abstract ClubReview toEntity(ClubReviewDTO clubReviewDto);

    @Named("gameMatchIdToGameMatch")
    public GameMatch gameMatchIdToGameMatch(Long gameMatchId) {
        GameMatch gameMatch = gameMatchRepository.findById(gameMatchId).
                orElseThrow(() -> new NotFoundException("gameMatch", "GameMatch" + " not exists into database"));
        return gameMatch;
    }

    @Named("clubIdToClub")
    public Club clubIdToClub(Long ClubId) {
        Club club = clubRepository.findById(ClubId).
                orElseThrow(() -> new NotFoundException("club", "Club" + " not exists into database"));
        return club;
    }
    @Named("playerUserIdToUser")
    public User playerUserIdToUser(Long playerUserId) {
        User playerUser = playerUserRepository.findById(playerUserId).
                orElseThrow(() -> new NotFoundException("playerUser", "User" + " not exists into database"));
        return playerUser;
    }


}
