package it.sportandreview.game_match;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

@Repository
public interface
GameMatchRepository extends JpaRepository<GameMatch, Long>, JpaSpecificationExecutor<GameMatch> {
    Page<GameMatch> findByStateId(Long stateId, Pageable pageable);
    Long countByStateId(Long stateId);

    @Query(value = "SELECT * FROM app_game_match as gm " +
            "LEFT JOIN app_game_match_team as gmt ON gm.id = gmt.game_match_id " +
            "LEFT JOIN app_team_player_user as tpu ON gmt.team_id = tpu.team_id " +
            "WHERE gm.organizer_id = :playerUserId OR tpu.player_user_id = :playerUserId", nativeQuery = true)
    List<GameMatch> findByPlayerUserIdOrganizerOrPlayer(@Param("playerUserId") Long playerUserId);
}
