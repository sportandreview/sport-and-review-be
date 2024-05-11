package it.sportandreview.game_level;


import java.util.List;

public interface GameLevelService {
    List<GameLevelDTO> findAll();

    GameLevelDTO findById(Long gameLevelId);

}
