package it.sportandreview.match_state;


import java.util.List;

public interface MatchStateService {


    List<MatchStateDTO> findAll();

    MatchState findById(Long matchStateId);

}
