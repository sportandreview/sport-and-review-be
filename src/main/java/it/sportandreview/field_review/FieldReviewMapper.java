package it.sportandreview.field_review;

import it.sportandreview.exception.NotFoundException;
import it.sportandreview.field.Field;
import it.sportandreview.field.FieldMapper;
import it.sportandreview.field.FieldRepository;
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

@Mapper(componentModel = "spring", uses={FieldReview.class, PlayerUserMapper.class, GameMatchMapper.class, FieldMapper.class})
public abstract class FieldReviewMapper {

    @Autowired
    private GameMatchRepository gameMatchRepository;
    @Autowired
    private FieldRepository fieldRepository;
    @Autowired
    UserRepository playerUserRepository;

    @Mapping(source = "gameMatch.id", target = "gameMatchId")
    @Mapping(source = "field.id", target = "fieldId")
    @Mapping(source = "playerUser.id", target = "playerUserId")
    public abstract FieldReviewDTO toDto(FieldReview fieldReview);
    @Mapping(source = "gameMatchId", target = "gameMatch", qualifiedByName = "gameMatchIdToGameMatch")
    @Mapping(source = "fieldId", target = "field", qualifiedByName = "fieldIdToField")
    @Mapping(source = "playerUserId", target = "playerUser", qualifiedByName = "playerUserIdToUser")
    public abstract FieldReview toEntity(FieldReviewDTO fieldReviewDto);

    @Named("gameMatchIdToGameMatch")
    public GameMatch gameMatchIdToGameMatch(Long gameMatchId) {
        GameMatch gameMatch = gameMatchRepository.findById(gameMatchId).
                orElseThrow(() -> new NotFoundException("gameMatch", "GameMatch" + " not exists into database"));
        return gameMatch;
    }

    @Named("fieldIdToField")
    public Field fieldIdToField(Long fieldId) {
        Field field = fieldRepository.findById(fieldId).
                orElseThrow(() -> new NotFoundException("field", "Field" + " not exists into database"));
        return field;
    }
    @Named("playerUserIdToUser")
    public User playerUserIdToUser(Long playerUserId) {
        User playerUser = playerUserRepository.findById(playerUserId).
                orElseThrow(() -> new NotFoundException("playerUser", "User" + " not exists into database"));
        return playerUser;
    }


}
