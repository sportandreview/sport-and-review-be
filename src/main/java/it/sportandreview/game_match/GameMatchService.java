package it.sportandreview.game_match;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface GameMatchService {

    Long create(GameMatchDTO gameMatchDto);

    GameMatchDTO update(GameMatchDTO gameMatchDto);

    void deleteById(Long gameMatchId, Long playerUserId);

   PagingGameMatchDTO findByMatchStateIdPaging(Long matchStateId, Integer pageSize, Integer pageNumber);

   GameMatchDTO findById(Long gameMatchId);

   Long countByMatchStateId(Long stateId);

   List<GameMatchDTO> findByPlayerUserId(Long playerUserId);

    Long createBooking(BookingGameMatchDTO bookingGameMatchDTO);

    List<ReducedGameMatchDTO> findAll(String clubCity, String clubName, LocalDate date, LocalTime time, Long sportId, Long genderTypeId,
                                      Long gameLevelId, Long playerUserId);

    void checkTimeForGameMatch(Long gameMatchId);

}
