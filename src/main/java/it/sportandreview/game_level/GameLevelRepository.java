package it.sportandreview.game_level;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameLevelRepository extends JpaRepository<GameLevel, Long> {

}
