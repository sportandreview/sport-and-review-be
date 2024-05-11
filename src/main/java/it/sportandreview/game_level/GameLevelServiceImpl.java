package it.sportandreview.game_level;

import it.sportandreview.exception.NotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Log4j2
public class GameLevelServiceImpl implements GameLevelService {

    private final GameLevelRepository gameLevelRepository;
    private final GameLevelMapper gameLevelMapper;

    @Autowired
    public GameLevelServiceImpl(GameLevelRepository gameLevelRepository, GameLevelMapper gameLevelMapper) {
        this.gameLevelRepository = gameLevelRepository;
        this.gameLevelMapper = gameLevelMapper;
    }

    @Override
    public List<GameLevelDTO> findAll() {
        return gameLevelRepository.findAll().stream().map(gameLevelMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public GameLevelDTO findById(Long gameLevelId) {
        return gameLevelRepository.findById(gameLevelId).map(gameLevelMapper::toDto).
                orElseThrow(() -> new NotFoundException("game level", "GameLevel" + "not exists into database"));
    }
}
