package it.sportandreview.game_level;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses={GameLevel.class})
public interface GameLevelMapper {

    GameLevelDTO toDto(GameLevel gameLevel);
    GameLevel toEntity(GameLevelDTO gameLevelDto);

}
